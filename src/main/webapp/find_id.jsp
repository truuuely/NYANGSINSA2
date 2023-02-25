<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="nss" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>냥신사 | 아이디 찾기</title>

<!-- favicon -->
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />

<!-- google Font -->
<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="stylesheet" href="css/style_login.css" type="text/css">
</head>

<body>
		<nss:header />

	<!-- ★★★★★여기에 페이지의 메인 코드를 넣어주세요★★★★★ -->

	<!--================Login Box Area =================-->
	<section class="login_box_area section-margin">
		<div class="container" align="center">

			<div class="col-lg-6">
				<div class="login_form_inner">
					<h3>아이디 찾기</h3>
					<form class="row login_form" id="contactForm">
						<div class="col-md-12 form-group">
							<input type="tel" style="width: 65%;" id="userPhoneNum" name="tel" placeholder="전화번호 입력" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
						</div>
						<br>
						<br>
						<br>
						<br>
						<div class="col-md-12 form-group">
							<!-- <button onclick="sms();" class="button button-login w-100"> 핸드폰 번호로 아이디 찾기</button> -->
							<!-- <input type="submit" value=" 핸드폰 번호로 아이디 찾기"  class="button button-login w-100" >  -->
							<input type="button" class="button button-login w-100" value="핸드폰 번호로 아이디 찾기" onclick="sms()">
							<input type="text" placeholder="인증번호를 입력하세요" id="userCheck">
							<input type="button" value="인증번호 확인" onclick="smsCheck()">
						</div>
						<br>
						<br>
						<br>
						<br>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- 인증번호 -->
	<!-- <p class="checkre" id="outputName">전화번호를 입력해주세요</p>
							<div>
								<input type="tel" placeholder="전화번호 입력" id="userPhoneNum"
									onkeyup="validationCertiNum()"
								> <input type="button" value="인증번호 받기" onclick="sms()">
							</div>
							<div id="phoneNumCheckMent"></div>
							<input type="text" placeholder="인증번호를 입력하세요" id="userCheck">
							<input type="button" value="인증번호 확인" onclick="smsCheck()"> -->
	<!--                   -->
	<!--================End Login Box Area =================-->

	<!-- Footer Section Begin -->
	<nss:footer />
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	<%--ajax 쓰기 위해 JQ연결 --%>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		var number = 0; //랜덤문자인증번호 저장할 변수	
		//  var phoneCheck=0;  //인증번호 일치, 불일치 변수
		var userPhoneNum = 0; // 유저 폰번호 스코프때문에 위로 뺐음 
		//인증번호 전송하기: sms()
		function sms() {
			console.log('로그 1 : sms()라는 JS 함수가 연결되었음');
			userPhoneNum = $("#userPhoneNum").val();
			console.log('로그2 : userPhoneNum ' + userPhoneNum);

			//Sms서블릿클래스로 이동함.
			$.ajax({
				type : 'POST',
				url : 'Sms',
				data : {
					userPhoneNum : userPhoneNum
				},
				success : function(randNum) {
					console.log("로그:인증번호[" + randNum + "]")
					if (randNum != null) {
						alert("인증번호 전송이 완료되었습니다!");
						number = randNum; //랜덤문자인증번호
					} else {
						alert("인증번호 전송이 불가합니다..");
					}

				}
			})

		}

		//인증번호 비교: sms()
		function smsCheck() {
			console.log('로그 : smsCheck()라는 JS 함수가 연결되었음');
			var checkNum = $("#userCheck").val();
			console.log('로그(사용자가 입력한 값) : checkNum ' + checkNum);

			//SmsCheck서블릿클래스로 이동
			$.ajax({
				type : 'POST',
				url : 'SmsCheck',
				data : {
					randNum : number,
					checkNum : checkNum
				},
				success : function(result) {
					console.log("로그 result:[" + result + "]")
					console.log(typeof result);
					if (result == "1") {
						alert("인증번호가 일치합니다!");
						console.log('폰번호 :' + userPhoneNum);
						var link = 'findId.do?phoneNum=' + userPhoneNum;
						location.href = link;
					} else {
						console.log('후후  : userPhoneNum ' + userPhoneNum);
						alert("인증번호가 일치하지 않습니다.다시 입력하세요!"); //인증번호 불일치
					}
				}
			})
		}
	</script>

	<!— TOP 버튼 —>
	<div style="width: 120px; position: fixed; bottom: 80px; right: 100px; z-index: 1;">
		<a href="#">
			<button type="button" class="button-top">▲ 맨위로</button>
		</a>
	</div>

</body>

</html>