package com.wan.nss.biz.common;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wan.nss.biz.image.ImageDAO;
import com.wan.nss.biz.image.ImageVO;
import com.wan.nss.biz.product.ProductDAO;
import com.wan.nss.biz.product.ProductVO;

@Component
public class Crawling {

	@Autowired
	private ProductDAO productDAO = new ProductDAO();
	@Autowired
	private ImageDAO imageDAO = new ImageDAO();

	private final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
	private final String WEB_DRIVER_PATH = "C:/Dev/kotddari/resource/chromedriver.exe"; // 드라이버
	private final int MAX = 15;

	public void downloadFile(URL url, String fileName) throws Exception {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
        }
    }
	
	public void sample() {
		List<String> urlDatas = urlDatas();
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();

		// options.addArguments("headless");
		options.addArguments("--disable-popup-blocking"); // 팝업안띄움
		options.addArguments("headless"); // 브라우저 안띄움
		options.addArguments("--disable-gpu"); // gpu 비활성화
		options.addArguments("--blink-settings=imagesEnabled=false"); // 이미지 다운 안받음

		// WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);
		for (int i = 0; i < urlDatas.size(); i++) {
			try {
				driver.get(urlDatas.get(i));
				// 상품 이름 크롤링
//				String name = driver.findElements(By.cssSelector("div > div > div.MuiContainer-root.jss9.MuiContainer-disableGutters > div.jss16 > div.jss17 > div.jss21 > div.jss23 > div.jss24 > div.jss57 > h2")).get(0).getText();
				String name = driver.findElements(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/h2")).get(0).getText();
				// 상품 가격 크롤링
				String price = driver.findElements(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/div/div/div/strong")).get(0).getText();
				// 이미지 크롤링
				// 이미지 주소는 파일 다운로드를 위해 URL 객체로 만들기
				String url = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/div/div/div/div/picture/img")).getAttribute("src");
				String url2 = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/picture/img")).getAttribute("src");
				URL imgUrl = new URL(url);
				URL imgUrl2 = new URL(url2);
				// 상품 간단 설명 크롤링
				String info = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/button/div/div")).getText();

				System.out.println(i+" 상품이름 "+name);
				System.out.println(i+" 상품가격 "+price);
				System.out.println(i+" img "+url);
				System.out.println(i+" img2 "+url2);
				System.out.println(i+" 상품설명 "+info);
				
				
				// IMAGE 테이블에 추가
				// img 멤버변수 세팅
				ImageVO ivo = new ImageVO();
				// targetNum = pNum = i+1
				ivo.setTargetNum(i+100);
				// typeNum = 101
				ivo.setTypeNum(101);
				// imageName = fileName;
				String fileName = url.substring(url.lastIndexOf('/')+1, url.length()); // 소스에서 파일명 가져오기
				System.out.println(i+100+". 파일네임1: "+fileName);
				ivo.setImageName(fileName);
				
				// ivo insert into IMAGE
				imageDAO.insert(ivo);
				
				// 폴더가 없으면 생성하기
				File dir = new File("C:/Dev/kotddari/workspace02/NYANGSINSA2/src/main/webapp/img/productImg/" + (i+100));
				if ( !dir.exists() ) {
					System.out.println(i+100+". 폴더생성시작");
					dir.mkdir();
				}
				
				// 폴더 생성 시간 확보하기
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				
				// 이미지 파일 저장하기
				// images/productImages/번호/파일명
				downloadFile(imgUrl, dir + "/" + fileName); // 파일 다운로드하기
				
				// IMAGE 테이블에 추가
				// img2 멤버변수 세팅
				// targetNum = pNum = i+1
				ivo.setTargetNum(i+100);
				// typeNum = 102
				ivo.setTypeNum(102);
				// imageName = fileName;
				String fileName2 = url2.substring(url2.lastIndexOf('/')+1, url.length()); // 파일명 가져오기
				System.out.println(i+" 파일네임2 "+fileName2);
				ivo.setImageName(fileName2);
				
				// ivo insert into IMAGE
				imageDAO.insert(ivo);
				
				// 이미지 파일 저장하기
				downloadFile(imgUrl2, dir + "/" + fileName); // 파일 다운로드하기

				// PRODUCT 테이블에 추가
				ProductVO pvo = new ProductVO();
				// pvo 멤버변수 세팅
				pvo.setpName(name);
				pvo.setPrice(Integer.parseInt(price));
				pvo.setImageName("/img/productImg/" + i+100 + fileName);
				pvo.setpDetail(info);
				productDAO.insert(pvo);
				
				
			} catch (Exception e) {
				System.out.println("크롤링 에러 발생!");
				e.printStackTrace();
			}
		}
		try {
			if (driver != null) {
				// 드라이버 연결 종료
				driver.close(); // 드라이버 연결 해제
				// 프로세스 종료
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<String> urlDatas() {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();

		//      options.addArguments("headless");

		// WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);

		List<String> category = new ArrayList<String>();
		List<String> urlDatas = new ArrayList<String>();
		category.add("https://catpre.com/category/053"); // 사료
		category.add("https://catpre.com/category/054"); // 간식
		category.add("https://catpre.com/category/056"); // 모래
		for (int a = 0; a < category.size(); a++) { // 카테고리 수만큼 반복
			driver.get(category.get(a));
			List<WebElement> el3 = driver.findElements(By.className("MuiCardActionArea-root")); // 상품마다 url 불러옴!!!
			for (int i = 0; i < MAX; i++) { // 불러오고 싶은 상품 개수만큼 반복
				System.out.println("[카테고리" + a + "]" + el3.get(i).getAttribute("href"));
				urlDatas.add(el3.get(i).getAttribute("href")); // 데이터 추가
			}
		}
		try {
			if (driver != null) {
				// 드라이버 연결 종료
				driver.close(); // 드라이버 연결 해제
				// 프로세스 종료
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return urlDatas;
	}

}