# NYANGSINSA2
### 냥신사 : 고양이 용품 쇼핑몰 &amp; 커뮤니티 (ver.2)
- Spring MVC 패턴을 사용한 반응형 웹 프로젝트
- 반려 고양이 용품을 쇼핑하고, 고양이에 관한 이야기를 나눌 수 있는 웹 사이트
- 기존 JSP 웹 프로젝트 <a href="https://github.com/truuuely/Nyangsinsa">냥신사 쇼핑몰</a>을 확장한 사이트
- Selenium을 사용해 데이터 크롤링 및 처리

### 담당
MVC 중 Model 담당
- 확장 기능(커뮤니티 및 이미지) DB 설계
- 회원, 상품, 주문 상세 테이블 등 기존 Oracle DB를 MySQL로 이관 작업
- 게시판, 좋아요, 신고, 댓글(대댓글), 이미지 관련 기능 작업
- MyBatis Framework 적용
- 사용자 비밀번호 암호화 처리
- 검색어 영한 변환 작업


## 기술 스택
<img width="871" alt="image" src="https://user-images.githubusercontent.com/68685790/233834575-970bb164-8063-4069-9013-e15bd449ded5.png">


## 기능
### 사용자 측면
- 회원가입 및 로그인 기능 (SNS 로그인 및 회원가입 가능)
- 인기순, 가격순 등 상품 정렬 기능
- 장바구니 및 주문 기능
- 구매 후 리뷰 작성하기
- 회원 정보 변경 및 리뷰, 주문 내역 등을 확인할 수 있는 마이페이지
- 가격대를 지정할 수 있는 가격 슬라이더 및 상품 검색 기능(검색어 영한 변환)
- 언어 선택(페이지 다국어 처리) 기능
- 커뮤니티(게시판)에서 이미지 포함 게시글 작성
- 게시글 좋아요 및 SNS 공유 기능
- 좋아요 수, 조회수, 댓글 수 기준으로 인기글 상위 3순위 소개
- 게시글, 댓글/대댓글 삭제 및 신고 기능
- 글 제목/내용/작성자 검색 기능

### 관리자 측면
- 연도별, 월별, 카테고리별, 결제 방법별 판매량 통계를 확인할 수 있는 관리자 페이지
- 상품 정보 수정 및 회원/상품/주문/리뷰 확인 및 관리
- 게시글, 댓글/대댓글 신고에 따른 상태 처리(삭제 혹은 철회)
- 신고 처리에 따른 글 상태 변경 및 회원 등급 변경


## ERD
<img width="456" alt="image" src="https://user-images.githubusercontent.com/68685790/233834790-fc918ae0-e804-4f8e-889f-9420a40ff4f0.png">


## 화면

![image](https://user-images.githubusercontent.com/68685790/233835178-7821d9c7-61c9-4561-9133-0d3c4fb08fbf.png)
![image](https://user-images.githubusercontent.com/68685790/233835184-303d4dc6-d6a3-495c-9500-01c7531d82b1.png)
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/68685790/233835842-1edea22d-b1e1-47ce-a21f-5009a2fd8fa3.png">
![image](https://user-images.githubusercontent.com/68685790/233835411-af36dfa9-3654-4df5-8066-06cd7e558b48.png)

