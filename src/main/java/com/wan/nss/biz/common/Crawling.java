package com.wan.nss.biz.common;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	// ★★★★★ 각자 알맞게 경로 수정!!!
	private final String WEB_DRIVER_PATH = "C:/Users/dlwjd/OneDrive/Desktop/국비/resource/chromedriver_win32/chromedriver.exe"; // 드라이버
	private final int MAX = 3;

	public void downloadFile(URL url, String fileName) throws Exception {
		try (InputStream in = url.openStream()) {
			Files.copy(in, Paths.get(fileName));
		}
	}

	public void sample() {
		List<String> urlDatas = urlDatas(); // 반환받은 url 배열리스트
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-popup-blocking"); // 팝업 안 띄움
		options.addArguments("headless"); // 브라우저 안 띄움
		options.addArguments("--disable-gpu"); // gpu 비활성화
		options.addArguments("--blink-settings=imagesEnabled=false"); // 이미지 다운 안 받음

		// WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);

		for (int i = 0; i < urlDatas.size(); i++) { // url 배열 크기만큼 반복
			try {

				driver.get(urlDatas.get(i)); // 크롤링 할 링크 연결
				// 상품 이름 크롤링
				String name = driver.findElements(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/h2")).get(0).getText();
				// 상품 가격 크롤링
				String price = driver.findElements(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/div/div/s")).get(0).getText();

				// 이미지 크롤링
				// 이미지 주소는 파일 다운로드를 위해 URL 객체로 만들기
				String url = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/div/div/div/div/picture/img"))
						.getAttribute("src");
				String url2 = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/picture/img")).getAttribute("src");
				URL imgUrl = new URL(url);
				URL imgUrl2 = new URL(url2);
				// ----------------

				// 상품 간단 설명 크롤링
				String info = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/button/div/div")).getText();
				// ----------------

				// 상품 할인율 생성
				int dcPercent;
				dcPercent = (new Random().nextInt(6)) * 5;
				// ----------------

				// 크롤링 데이터 확인 부분
				System.out.println(i + 100 + ". 상품이름 :" + name);
				System.out.println(i + 100 + ". 상품가격 :" + price);
				System.out.println(i + 100 + ". 상품할인율 :" + dcPercent);
				System.out.println(i + 100 + ". imgUrl: " + url);
				System.out.println(i + 100 + ". imgUrl2: " + url2);
				System.out.println(i + 100 + ". 상품설명: " + info);
				// ----------------

				// IMAGE 테이블에 추가
				// img 멤버변수 세팅
				ImageVO ivo = new ImageVO();
				// targetNum = pNum = i+1
				ivo.setTargetNum(i + 100);

				// typeNum = 101
				ivo.setTypeNum(101);

				// imageName = fileName;
				String fileName = url.substring(url.lastIndexOf('/') + 1, url.length()); // 소스에서 파일명 가져오기
				System.out.println(i + 100 + ". 파일네임1: " + fileName); // 파일 이름 확인
				ivo.setImageName(fileName);

				// ivo insert into IMAGE
				imageDAO.insert(ivo);

				// ★★★★★ 각자 알맞게 경로 수정!!!
				// 폴더가 없으면 생성하기
				File dir = new File("C:/Users/dlwjd/OneDrive/Desktop/국비/팀플/NYANGSINSA2/src/main/webapp/img/101");
				if (!dir.exists()) { // 위의 경로에 파일이 존재하니??
					System.out.println(i + 100 + ". 폴더 생성 시작");
					dir.mkdir();
				}

				// 폴더 생성 시간 확보하기
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

				// ★★★★★ 각자 알맞게 경로 수정!!!
				// 이미지 파일 저장하기
				// images/productImages/번호/파일명
				downloadFile(imgUrl, "C:/Users/dlwjd/OneDrive/Desktop/국비/팀플/NYANGSINSA2/src/main/webapp/img/101/" + (i + 100) + ".jpg"); // 파일 다운로드하기

				// IMAGE 테이블에 추가
				// img2 멤버변수 세팅
				// targetNum = pNum = i+1
				ivo.setTargetNum(i + 100);
				// typeNum = 102
				ivo.setTypeNum(102);
				// imageName = fileName;
				String fileName2 = url2.substring(url2.lastIndexOf('/') + 1, url2.length()); // 파일명 가져오기
				System.out.println(i + 100 + ". 파일네임2: " + fileName2);
				ivo.setImageName(fileName2);

				// ivo insert into IMAGE
				imageDAO.insert(ivo);

				// ★★★★★ 각자 알맞게 경로 수정!!!
				// 폴더가 없으면 생성하기
				File dir2 = new File("C:/Users/dlwjd/OneDrive/Desktop/국비/팀플/NYANGSINSA2/src/main/webapp/img/102");
				if (!dir2.exists()) {
					System.out.println(i + 100 + ". 폴더생성시작");
					dir2.mkdir();
				}

				// ★★★★★ 각자 알맞게 경로 수정!!!
				// 이미지 파일 저장하기
				downloadFile(imgUrl2, "C:/Users/dlwjd/OneDrive/Desktop/국비/팀플/NYANGSINSA2/src/main/webapp/img/102/" + (i + 100) + ".jpg"); // 파일 다운로드하기

				// PRODUCT 테이블에 추가
				ProductVO pvo = new ProductVO();
				// pvo 멤버변수 세팅
				pvo.setpName(name);
				// 카테고리 구분
				if (i >= 0 && i <= 2) {
					pvo.setCategory("사료");
				} else if (i >= 3 && i <= 5) {
					pvo.setCategory("간식");
				} else if (i >= 6 && i <= 8) {
					pvo.setCategory("모래");
				}
				// 가격데이터에서 화표 자리수 기호 제거
				String priceNumOnly = price.replaceAll(",", "");
				priceNumOnly = priceNumOnly.replaceAll("원", "");
				pvo.setPrice(Integer.parseInt(priceNumOnly));

				// 수량은 기본 10으로 고정
				pvo.setpAmt(10);

				// 할인율데이터에서 % 제거
				pvo.setpDcPercent(dcPercent); // 할인율

				pvo.setpDetail(info); // 상품 간단 설명
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

// ---------------------------------------------------------------------------------------

	public List<String> urlDatas() { // 카테고리별 url 가져오기

		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();

//		options.addArguments("headless");

		// WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);

		List<String> category = new ArrayList<String>(); // 카테고리 배열리스트
		List<String> urlDatas = new ArrayList<String>(); // url 배열 리스트
		List<String> image = new ArrayList<String>(); // url 배열 리스트
		category.add("https://catpre.com/category/053"); // 사료
		category.add("https://catpre.com/category/054"); // 간식
		category.add("https://catpre.com/category/056"); // 모래
		int imgNum = 265;
		for (int a = 0; a < category.size(); a++) { // 카테고리 수만큼 반복
			imgNum += a * 2;
			driver.get(category.get(a)); // 순차적으로 url 크롤링
			List<WebElement> el3 = driver.findElements(By.className("MuiCardActionArea-root")); // 상품마다 url 불러옴!!!
			System.out.println("div > a > div.jss" + imgNum + "> div > picture > img");
			List<WebElement> el4 = driver.findElements(By.cssSelector("div > a > div.jss" + imgNum + "> div > picture > img")); // 상품마다 url 불러옴!!!

			System.out.println(el3.size());
			System.out.println(el4.size());
			for (int i = 0; i < MAX; i++) { // 불러오고 싶은 상품 개수만큼 반복
				System.out.println("[카테고리 " + a + "]" + el3.get(i).getAttribute("href"));
				System.out.println("[카테고리 " + a + " 사진]" + el4.get(i).getAttribute("src"));

				urlDatas.add(el3.get(i).getAttribute("href")); // 배열리스트에 url 데이터 추가
				image.add(el4.get(i).getAttribute("src"));
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
		return urlDatas; // url 담은 배열리스트 반환
	}

	public static void main(String[] args) {
		Crawling craw = new Crawling();
		craw.sample();
	}
}