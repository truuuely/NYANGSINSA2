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
<title>냥신사 | 홈</title>


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
<link rel="stylesheet" href="css/blog.css" type="text/css">
</head>

<body>

	<jsp:include page="layer_popup.jsp" />

		<nss:header />



	<div style="margin-bottom: 50px;" class="boast">🎉 이달의 냥냥 🎊</div>
	<div>
		<div style="height: 450px; border-radius: 50px; background-color: #6667ab29; padding-top: 20px;" class="container">
			<div style="align-items: center;" class="row">
				<div class="month2">
					<img class="crown2" src="img/wangg2.png">
					<br>
					<img class="crowncat2" src="img/balbadak.jpg">
					<div style="font-size: 25px; text-align: center; font-weight: bold;">발바닥(4세)</div>
				</div>
				<div class="month1">
					<img class="crown1" src="img/wangg1.png">
					<br>
					<img class="crowncat1" src="img/heehee.jpg">
					<div style="font-size: 25px; text-align: center; font-weight: bold;">곰돌이(?세)</div>
				</div>
				<div class="month2">
					<img class="crown2" src="img/wangg3.png">
					<br>
					<img class="crowncat2" src="img/hoo.jpg">
					<div style="font-size: 25px; text-align: center; font-weight: bold;">후추(2세)</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>

	<hr style="width: 80%;">

	<!-- Blog Section Begin -->
	<!-- 자랑해 냥냥 시작 -->
	<section style="padding-top: 60px;" class="blog spad">
		<!-- <a style="font-size: 20px; border: 2px solid; float: right; margin-right: 20%; border-radius: 15px; background-color: #6667AB; border-color: none; color: white; padding: 8px;" href=""> 글 쓰러 가기</a> } -->
		<div class="boast">🐾 자랑해 냥냥 🐱</div>


		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-7">
					<div class="row">
						<nss:list sort="community" />
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="product__pagination blog__pagination">
				<a id="load" style="width: 90px; height: 30px; font-size: 18px; border: none;" href="#">더보기 ▼</a>
			</div>
		</div>
	</section>
	<!-- Blog Section End -->
	<!-- 자랑해 냥냥 끝 -->

	<nss:footer />

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script>
		$(function() {
			$(".show-board").slice(0, 3).show(); // 초기갯수
			$("#load").click(function(e) { // 클릭시 more
				e.preventDefault();
				$(".show-board:hidden").slice(0, 3).show(); // 클릭시 more 갯수 지정
				if ($(".show-board:hidden").length < 0) { // 컨텐츠 남아있는지 확인
					alert("게시물의 끝입니다."); // 컨텐츠 없을시 alert 창 띄우기 
				}
			});
		});
	</script>
	<!-- <script type="text/javascript">
		function updateLike(bNum) {
			console.log(bNum);
			var heart = document.getElementsByClassName("heartImg");
			for (var i = 0; i < heart.length; i++) {
				heart[i].addEventListener('click', function() {
					if (this.getAttribute("src") == "img/fullheart.png") {
						this.src = "img/heart.png";
						location.herf = "updateLike.do?boardNum=" + bNum;
					} else {
						console.log(i);
						this.src = "img/fullheart.png";
					}
				})
			}
		}
	</script> -->

	<!-- TOP 버튼 -->
	<div style="width: 120px; position: fixed; bottom: 80px; right: 100px; z-index: 1;">
		<a href="#" class="write_board">글 쓰기</a>
	</div>

	<script type="text/javascript">
		function updateLike(bNum, upOrDown) {
			$.ajax({ // ajax로 데이터 가져오기
				type : 'POST',
				url : 'getHeartCnt.do',
				data : {upOrDown:upOrDown}
				success : function(data) {
					console.log("좋아유 수 "+data)
					$('#bNum').text(data);
					if(upOrDown=="down"){
						$(this).attr("src", "img/heart.png");
						/* $(this).children('img').attr("src", "img/heart.png"); */
					}else{
						$(this).attr("src", "img/fullheart.png");
					}				
				},
				error : function() {
					alert('error');
				}
			})
		})
	</script>


</body>

</html>