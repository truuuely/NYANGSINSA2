package com.wan.nss.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Selenium {

   final static String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
   final static String WEB_DRIVER_PATH = "C:/Dev/kotddari/resource/chromedriver.exe"; // 드라이버
   final static int MAX = 15;

   public static void crawling() {
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
 
         String name = driver
                .findElements(By.cssSelector("div > div > div.MuiContainer-root.jss9.MuiContainer-disableGutters > div.jss16 > div.jss17 > div.jss21 > div.jss23 > div.jss24 > div.jss57 > h2")).get(0).getText();
         String price = driver.findElements(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/div/div/div/strong")).get(0).getText();
         String img = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/div/div/div/div/picture/img")).getAttribute("src");
         String img2 = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/picture/img")).getAttribute("src");
         String info = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/div/div/button/div/div")).getText();
          
         System.out.println(i+" 상품이름 "+name);
         System.out.println(i+" 상품가격 "+price);
         System.out.println(i+" img "+img);
         System.out.println(i+" img2 "+img2);
         System.out.println(i+" 상품설명 "+info);
      } catch (Exception e) {
         System.out.println("크롤링 에러 발생!");
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

   public static List<String> urlDatas() {
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

   public static void main(String[] args) {
//      urlDatas();
      crawling();
   }

}