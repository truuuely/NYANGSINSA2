<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="nss" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<li><a href="shoping_cart.do">
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
						<li><a href="shoping_cart.do">
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
				<div class="hero__search">
					<div class="hero__search__form">
						<form action="search.do">
							<!-- <input type="hidden" name="searchCondition" value="pName">  -->
							<input type="text" name="searchContent" placeholder="필요한 거 있냥?" required>
							<button type="submit" class="site-btn">검색</button>
						</form>
					</div>
					<div class="hero__search__phone">
						<div class="hero__search__phone__icon">
							<i class="fa fa-phone"></i>
						</div>
						<div class="hero__search__phone__text">
							<h5>02-0202-0202</h5>

							<span>상담 가능 시간<br>평일 10:00~18:00
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Hero Section End -->
