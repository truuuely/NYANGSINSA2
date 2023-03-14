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

<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Humberger Begin -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="main.do">
				<img src="img/logo.png" alt="홈으로 가기">
			</a>
		</div>
		<div class="humberger__menu__cart">
			<ul>
				<li><a href="shopingCart.do">
						<i class="fa fa-shopping-bag"></i> <span>${fn:length(cList)}</span>
					</a></li>
			</ul>
		</div>

		<div class="humberger__menu__widget">
			<!-- 로그인 -->
			<nss:login />
		</div>

		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<li class="active"><a href="main.do">홈</a></li>
				<li><a href="shop.do?category=all&sort=sellDesc">쇼핑</a></li>
				<li><a href="contact.do">Contact</a></li>
				<li><a href="boardView.do">자랑해냥</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="header__top__right__social">
			<a href="https://www.facebook.com/profile.php?id=100089405234926">
				<i class="fa fa-facebook"></i>
			</a>
			<a href="https://www.instagram.com/nyangsinsa5/">
				<i class="fa fa-instagram"></i>
			</a>
			<a href="https://twitter.com/nyangsinsa">
				<i class="fa fa-twitter"></i>
			</a>
		</div>
		<div class="humberger__menu__contact">
			<ul>
				<li><i class="fa fa-envelope"></i>nyangsinsa@gmail.com</li>
				<li>대한민국 최고의 반려묘 용품 쇼핑몰</li>
			</ul>
		</div>
	</div>
	<!-- Humberger End -->

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-6">
						<div class="header__top__left">
							<ul>
								<li><i class="fa fa-envelope"></i>nyangsinsa@gmail.com</li>
								<li>대한민국 최고의 반려묘 용품 쇼핑몰</li>
							</ul>
						</div>
					</div>
					<div class="col-lg-6 col-md-6">
						<div class="header__top__right">
							<div class="header__top__right__social">
								<a href="https://www.facebook.com/profile.php?id=100089405234926">
									<i class="fa fa-facebook"></i>
								</a>
								<a href="https://www.instagram.com/nyangsinsa5/">
									<i class="fa fa-instagram"></i>
								</a>
								<a href="https://twitter.com/nyangsinsa">
									<i class="fa fa-twitter"></i>
								</a>
							</div>

							<!-- 로그인 -->
							<nss:login />

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="main.do">
							<img src="img/logo.png" alt="홈으로 가기">
						</a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="main.do">홈</a></li>
							<li><a href="shop.do?category=all&sort=sellDesc">쇼핑</a></li>
							<li><a href="contact.do">Contact</a></li>
							<li><a href="boardView.do">자랑해냥</a></li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-3">
					<div class="header__cart">
						<ul>
							<li><a href="shopingCart.do">
									<i class="fa fa-shopping-bag"></i> <span>${fn:length(cList)}</span>
								</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->




	<!-- Hero Section Begin index는 아래줄 section class="hero" -->
	<section class="hero hero-normal">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="hero__categories">
						<div class="hero__categories__all">
							<i class="fa fa-bars"></i> <span>메뉴</span>
						</div>
						<ul>
							<li><a href="shop.do?category=all">전체</a></li>
							<li><a href="shop.do?category=food">사료</a></li>
							<li><a href="shop.do?category=treat">간식</a></li>
							<li><a href="shop.do?category=sand">모래</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div style="overflow: visible;" class="hero__search">
						<div class="hero__search__form">
							<form action="selectAllSearchBoard.do">
								<select name="searchCondition">
									<option value="TITLE">제목</option>
									<option value="WRITER">작성자</option>
									<option value="CONTENT">내용</option>
								</select>
								<!-- 글 검색 부분 -->
								<input type="text" name="searchContent" placeholder="궁금한 글 있냥?" required>
								<button type="submit" class="site-btn">검색</button>
							</form>
						</div>
						<div class="hero__search__phone">
							<div class="hero__search__phone__icon">
								<i class="fa fa-phone"></i>
							</div>
							<div class="hero__search__phone__text">
								<h5>02-0202-0202</h5>

								<span>상담 가능 시간<br>평일 09:00~18:00
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->
	<div id="board">
		<div id="boardDetail">
			<div id="title">
				${board.boardTitle}
				<br>
				<div id="writer">[작성자 : ${board.userId}]</div>
			</div>
			<ul id="boardInfo">
				<li><i class="fa fa-calendar-o"></i> ${board.boardDate}</li>
				<li><i class="fa fa-comment-o"></i> ${board.replyCnt}</li>
				<li><img style="width: 20px;" src="img/eyes.png"> ${board.boardView}</li>
				<li class="${board.boardNum}info1"><c:choose>
						<c:when test="${board.isChecked==true}">
							<img onclick="javascript:updateLike(${board.boardNum}, 'down');" class="${board.boardNum}heartImg" style="width: 20px; cursor: pointer;" src="img/fullheart.png">
							<span class="${board.boardNum}">${board.likeCnt}</span>
						</c:when>
						<c:otherwise>
							<img onclick="javascript:updateLike(${board.boardNum}, 'up');" class="${board.boardNum}heartImg" style="width: 20px; cursor: pointer;" src="img/heart.png">
							<span class="${board.boardNum}">${board.likeCnt}</span>
						</c:otherwise>
					</c:choose></li>
				<li><img class="reportBtn" style="width: 20px; cursor: pointer;" src="img/siren.png"></li>
			</ul>
			<div id="content">
				<div style="font-size: 120%; font-weight: bold; letter-spacing: 1px;">${board.boardContent}</div>
			</div>
			<c:if test="${board.userId == member.userId}">
				<div style="float: right;">
					<form action="updateBoardView.do?boardNum=${board.boardNum}">
						<input type="submit" value="수정하기" style="border: 1px solid #6667ab42; color: white; padding: 10px; border-radius: 5px; background-color: #A0A0C8; font-size: 15px; float: right; margin-left: 950px; margin-bottom: -15px;">
					</form>
				</div>
			</c:if>
		</div>
	</div>

	<div id="showReply">
		<img style="width: 20px;" src="img/replyicon.png">
		댓글 (${board.replyCnt}) ▼
	</div>
	<div id="reply">
		<nss:list sort="reply" />
	</div>

	<div id="replywrite">
		<form style="width: 100%;" action="insertReply.do" method="post">
			<textarea style="border-radius: 5px; border: 1.7px solid #6667ab6b; width: 100%;" name="reply" placeholder="댓글을 작성하세요" required></textarea>
			<br>
			<input style="border: 1px solid #6667ab42; float: right; color: white; padding: 10px; border-radius: 5px; background-color: #6667AB;" type="submit" value="댓글 작성">
		</form>
	</div>

	<nss:footer />

	<!--  신고하기 모달창  -->

	<div class="container">
		<div class="popup-wrap" id="popup">
			<div class="popup">
				<div class="popup-head">
					<span class="head-title"> 신고하기</span>
				</div>
				<div class="popup-body">
					<div class="body-content">
						<div class="body-titlebox">
							<h2 style="font-size: 25px;">신고 사유를 선택해주세요.</h2>
						</div>
						<div class="body-contentbox">
							<div style="width: 300px;">
								<form action="" class="report-box">
									<input type="radio" class="report-box2" name="radio" id="r1" value="0">
									<label for="r1">욕설/부적절한 언어 입니다.</label>
									<br>
									<input type="radio" class="report-box2" name="radio" id="r2" value="0">
									<label for="r2">스팸광고/도배글 입니다.</label>
									<br>
									<input type="radio" class="report-box2" name="radio" id="r3" value="0">
									<label for="r3">부적절한 컨텐츠 입니다.</label>
									<br>
									<input type="radio" class="report-box2" name="radio" id="r4" value="0">
									<label for="r4">음란성 게시물 입니다.</label>
									<br>
									<input type="radio" class="report-box2" name="radio" id="r5" value="1">
									<label for="r5">기타</label>
									<br>
									<input type="text" class="report-box2 report-text" name="text" disabled placeholder="사유를 작성해주세요.">
							</div>

						</div>


					</div>
				</div>
				<div class="popup-foot">
					<input type="submit" class="pop-btn2" value="신고하기" id="close">
					</form>
					<span class="pop-btn modal-close" id="close2">창 닫기</span>
				</div>
			</div>
		</div>
	</div>




	<!-- TOP 버튼 -->
	<div id="fixtop">
		<a href="#">
			<button type="button" class="button-top">▲ 맨위로</button>
		</a>
	</div>

	<div id="fixshare">
		<a href="#">
			<button type="button" onclick="kakaoShare()" style="border: 1px solid; border-radius: 50%; height: 65px; width: 65px; padding: 14px; background: none; background-color: white;">
				<img class="shareImg" style="width: 50px; height: auto; cursor: pointer;" src="img/share.png">
				<div style="margin-top: 15px;">공유</div>
			</button>
		</a>
	</div>

	<div class="${board.boardNum}info2" id="fixheart">
		<a href="#fixheart">
			<button type="button" style="border: 1px solid; border-radius: 50%; height: 65px; width: 65px; padding: 14px; background: none; background-color: white;">
				<c:choose>
					<c:when test="${board.isChecked==true}">
						<img onclick="javascript:updateLike(${board.boardNum}, 'down');" class="${board.boardNum}heartImg" style="width: 50px; height: auto; cursor: pointer;" src="img/fullheart.png">
					</c:when>
					<c:otherwise>
						<img onclick="javascript:updateLike(${board.boardNum}, 'up');" class="${board.boardNum}heartImg" style="width: 50px; height: auto; cursor: pointer;" src="img/heart.png">
					</c:otherwise>
				</c:choose>
				<div class="${board.boardNum}" style="margin-top: 15px;">${board.likeCnt}</div>
			</button>
		</a>
	</div>

	<div id="fixcomment">
		<a href="#replywrite">
			<button type="button" style="border: 1px solid; border-radius: 50%; height: 65px; width: 65px; padding: 14px; background: none; background-color: white;">
				<img style="width: 50px; height: auto; cursor: pointer;" src="img/replyicon.png">
				<div style="margin-top: 15px;">${board.replyCnt}</div>
			</button>
		</a>
	</div>

	<c:if test="${board.userNum == memberNum}">
		<div id="fixdelete">
			<a href="deleteBoard.do?boardNum=${board.boardNum}">
				<button type="button" style="border: 1px solid; border-radius: 50%; height: 65px; width: 65px; padding: 14px; background: none; background-color: white;">
					<img style="width: 50px; height: auto; cursor: pointer;" src="img/deleteBoard.png">
					<div style="margin-top: 15px;">삭제</div>
				</button>
			</a>
		</div>
		<div id="fixupdate">
			<a href="updateBoard.do?boardNum=${board.boardNum}">
				<button type="button" style="border: 1px solid; border-radius: 50%; height: 65px; width: 65px; padding: 14px; background: none; background-color: white;">
					<img style="width: 50px; height: auto; cursor: pointer;" src="img/updateBoard.png">
					<div style="margin-top: 15px;">수정</div>
				</button>
			</a>
		</div>
	</c:if>


	<!--  신고하기 모달창 Scripte  -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(function() {
			$("#confirm").click(function() {
				modalClose();
				//컨펌 이벤트 처리
			});
			$(".reportBtn").click(function() {
				console.log('dd');
				$("#popup").css('display', 'flex').hide().fadeIn();
			});
			$("#modal-close").click(function() {
				modalClose();
			});
			$("#close2").click(function() {
				modalClose();
			});
			function modalClose() {
				$("#popup").fadeOut();
			}
		});
		$(document).ready(function() {
			// 라디오버튼 클릭시 이벤트 발생
			$("input:radio[name=radio]").click(function() {
				if ($("input[name=radio]:checked").val() == "1") {
					$("input:text[name=text]").attr("disabled", false);
					// radio 버튼의 value 값이 1이라면 활성화
				} else if ($("input[name=radio]:checked").val() == "0") {
					$("input:text[name=text]").attr("disabled", true);
					// radio 버튼의 value 값이 0이라면 비활성화
				}
			});
		});
	</script>



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

	<!-- <script type="text/javascript">
		var heart = document.getElementsByClassName("heartImg");
		for (var i = 0; i < heart.length; i++) {
			heart[i].addEventListener('click', function() {
				if (this.getAttribute("src") == "img/fullheart.png") {
					this.src = "img/heart.png";
				} else {
					console.log(i);
					this.src = "img/fullheart.png";
				}
			})
		}
	</script>  -->

	<script type="text/javascript">
		function updateLike(bNum, upOrDown) {
			if ('${memberId}' == '') {
				alert('로그인 후 이용해주세요');
			} else {
			var imgClass = '.'+bNum+'heartImg';
			var cntClass = '.'+bNum+'';
			var infoClass1 = '.'+bNum+'info1';
			var infoClass2 = '.'+bNum+'info2';
			console.log('들어옴');
			$.ajax({
				type : 'POST',
				url : 'updateBlike.do',
				data : {
					upOrDown : upOrDown,
					boardNum : bNum
				},
				success : function(data) {
					$(infoClass1).load(location.href + ' ' + infoClass1);
					$(infoClass2).load(location.href + ' ' + infoClass2);
					console.log("좋아유 수 " + data)
					console.log("넘버 " +'#'+bNum)
					console.log("업다운 " +upOrDown)
					console.log("이미지 아이디 " +'.'+bNum+'heartImg')
					console.log($(imgClass).attr('src'));
					$(cntClass).text(data);
					if (upOrDown == 'down') {
						$(imgClass).attr({src:'img/heart.png'});
					} else {
						$(imgClass).attr({src:'img/fullheart.png'});
					}
				},
				error : function() {
					alert('error');
				}
			})
		}
		}
	</script>

	<!-- <script>
		$(function() {
			/* $(".reply").slice(0, 6).show(); // 초기갯수 */
			$("#showReply").click(function(e) { // 클릭시 more
				e.preventDefault();
				$(".reply:hidden").slice(0, 1).show(); // 클릭시 more 갯수 지정
				/* if ($(".dd:hidden").length < 0) { // 컨텐츠 남아있는지 확인
				   alert("게시물의 끝입니다."); // 컨텐츠 없을시 alert 창 띄우기 
				} */
			});
		});
	</script> -->

	<script type="text/javascript">
	
		$("#showReReply").click(function() {
			$("#rereplywrite").stop().slideToggle(300);
		    $('#rereplywrite').css('display','flex'); 
			$("#rereplywrite").toggleClass('on').siblings().removeClass('on');
			$("#rereplywrite").siblings("#rereplywrite").slideUp(300); 
		});
	</script>

	<script type="text/javascript">
		$("#showReply").click(function() {
			$(this).next("#reply").stop().slideToggle(300);
			$(this).toggleClass('on').siblings().removeClass('on');
			$(this).next("#reply").siblings("#reply").slideUp(300);
		});
	</script>

	<!-- kakao sdk 호출 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

	<script type="text/javascript">
		// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
		Kakao.init('603243ecdb0757012b9f08d95c495f53');
		// SDK 초기화 여부를 판단합니다.
		console.log(Kakao.isInitialized());
		function kakaoShare() {
			Kakao.Link
					.sendDefault({
						objectType : 'feed',
						content : {
							title : '안녕안녕 헬로월드 마이크테스트',// 글제목 
							description : '강문영 바보바보', // 글내용
							imageUrl : 'https://ifh.cc/g/HkTbZk.png', // 이미지 url
							link : {
								mobileWebUrl : 'https://www.naver.com/',
								webUrl : 'https://www.youtube.com/watch?v=72fDsC2kX7g',
							},
						},
						social : {
							likeCount : 286, //좋아요 수
							commentCount : 45, //댓글 수
							sharedCount : 845, //공유 수
						},
						buttons : [
								{
									title : '웹으로 보기',
									link : {
										mobileWebUrl : 'http://localhost:8088/nyangsinsa7/blog_detail.jsp', // 이동할 경로
										webUrl : 'http://localhost:8088/nyangsinsa7/blog_detail.jsp', // 이동할 경로
									},
								}, ],
						// 카카오톡 미설치 시 카카오톡 설치 경로이동
						installTalk : true,
					})
		}
	</script>
	<script type="text/javascript">
		function deletecheck(bNum) {
			var result = confirm("정말로 삭제 하시겠습니까?");
			if(result) {
				alert("예");
				location.href = "deleteReply.do?bNum="+bNum+"";
			}
			else {
				alert("아니오");
			}
		}
	</script>
</body>

</html>