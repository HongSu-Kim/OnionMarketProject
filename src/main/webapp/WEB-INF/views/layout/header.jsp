<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Page Preloder -->
<div id="preloder">
	<div class="loader"></div>
</div>

<!-- Humberger Begin -->
<%--
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
   <div class="humberger__menu__logo">
      <a href="#"><img src="/template/img/logo.png" alt=""></a>
   </div>
   <div class="humberger__menu__cart">
      <ul>
         <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
         <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
      </ul>
      <div class="header__cart__price">item: <span>$150.00</span></div>
   </div>
   <div class="humberger__menu__widget">
      <div class="header__top__right__language">
         <img src="/template/img/language.png" alt="">
         <div>English</div>
         <span class="arrow_carrot-down"></span>
         <ul>
            <li><a href="#">Spanis</a></li>
            <li><a href="#">English</a></li>
         </ul>
      </div>
      <div class="header__top__right__auth">
         <a href="#"><i class="fa fa-user"></i> Login</a>
      </div>
   </div>
   <nav class="humberger__menu__nav mobile-menu">
      <ul>
         <li class="active"><a href="./index.html">Home</a></li>
         <li><a href="./shop-grid.html">Shop</a></li>
         <li><a href="#">Pages</a>
            <ul class="header__menu__dropdown">
               <li><a href="./shop-details.html">Shop Details</a></li>
               <li><a href="./shoping-cart.html">Shoping Cart</a></li>
               <li><a href="./checkout.html">Check Out</a></li>
               <li><a href="./blog-details.html">Blog Details</a></li>
            </ul>
         </li>
         <li><a href="./blog.html">Blog</a></li>
         <li><a href="./contact.html">Contact</a></li>
      </ul>
   </nav>
   <div id="mobile-menu-wrap"></div>
   <div class="header__top__right__social">
      <a href="#"><i class="fa fa-facebook"></i></a>
      <a href="#"><i class="fa fa-twitter"></i></a>
      <a href="#"><i class="fa fa-linkedin"></i></a>
      <a href="#"><i class="fa fa-pinterest-p"></i></a>
   </div>
   <div class="humberger__menu__contact">
      <ul>
         <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
         <li>Free Shipping for all Order of $99</li>
      </ul>
   </div>
</div>
 --%>
<!-- Humberger End -->

<!-- Header/Hero Section Begin -->
<header class="header">
	<%--
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                            <li>Free Shipping for all Order of $99</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-pinterest-p"></i></a>
                        </div>
                        <div class="header__top__right__language">
                            <img src="/template/img/language.png" alt="">
                            <div>English</div>
                            <span class="arrow_carrot-down"></span>
                            <ul>
                                <li><a href="#">Spanis</a></li>
                                <li><a href="#">English</a></li>
                            </ul>
                        </div>
                        <div class="header__top__right__auth">
                            <a href="#"><i class="fa fa-user"></i> Login</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     --%>

	<!-- nav -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="header__logo">
					<a href="/product/main"><img src="/template/img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6">
				<nav class="header__menu">
					<ul>
						<li><a href="/product/main">홈</a></li>
						<li><a href="/product/main">상품</a>
							<ul class="header__menu__dropdown">
								<li><a href="/product/main">메인 리스트</a></li>
								<li><a href="/product/add">상품 등록</a></li>
								<li><a href="/product/auctionList">경매 상품</a></li>
								<li><a href="#">Temp4</a></li>
							</ul>
						</li>
						<li><a href="#">거래내역</a>
							<ul class="header__menu__dropdown">
								<li><a href="/wish/list">Wish List</a></li>
								<li><a href="/order/buyList">Buy List</a></li>
								<li><a href="/order/sellList">Sell List</a></li>
								<li><a href="#">Temp4</a></li>
							</ul>
						</li>
						<li><a href="#">공지사항</a>
							<ul class="header__menu__dropdown">
								<li><a href="/notice/list">Notice List</a></li>
								<li><a href="/inquiry/list">Inquiry List</a></li>
								<li><a href="/review/list">Review List</a></li>
								<li><a href="#">Temp4</a></li>
							</ul>
						</li>
						<sec:authorize access="isAnonymous()">
						<li><a href="#">마이페이지</a>
							<ul class="header__menu__dropdown">

								<li><a href="/member/login">Login</a></li>
								<li><a href="/member/join">Join</a></li>

								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_USER')">
									<li><a href="#">마이페이지</a>
										<ul class="header__menu__dropdown">
											<li><a href="/member/mypage">My Page</a></li>
											<li><a href="/town/town">동네 설정</a></li>
											<li><a href="/member/logout">Logout</a></li>

										</ul>
									</li>
								</sec:authorize>

								<sec:authorize access="hasRole('ROLE_ADMIN')">

								<li><a href="#">관리자페이지</a>

									<ul class="header__menu__dropdown">


										<li><a href="/coordinate/coordinate">지역 관리</a></li>
										<li><a href="/tag/tag">태그 관리</a></li>
										<li><a href="/prohibitionkeyword/prohibitionkeyword">금지어 관리</a></li>
										<li><a href="/category/category"> 카테고리 관리 </a></li>
										<li><a href="/member/logout">Logout</a></li>


									</ul>
								</li>
							</ul>
							</sec:authorize>
				</nav>
			</div>
			<div class="col-lg-3">
				<div class="header__cart">
					<ul>
						<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
						<li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
					</ul>
					<div class="header__cart__price">item: <span>$150.00</span></div>
				</div>
			</div>
		</div>
		<div class="humberger__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>

	<!-- product search -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="hero__categories">
					<div class="hero__categories__all">
						<i class="fa fa-bars"></i>
						<span>All departments</span>
					</div>
					<ul>

						<li><a href="/product/main/category?categoryId=1">디지털/가전</a></li>
						<li><a href="/product/main/category?categoryId=9">가구/인테리어</a></li>
						<li><a href="/product/main/category?categoryId=12">생활/가공식품</a></li>
						<li><a href="/product/main/category?categoryId=17">유아동</a></li>
						<li><a href="/product/main/category?categoryId=27">남성의류</a></li>
						<li><a href="/product/main/category?categoryId=42">여성의류</a></li>
						<li><a href="/product/main/category?categoryId=57">가방/잡화</a></li>
						<li><a href="/product/main/category?categoryId=61">뷰티/미용</a></li>
						<li><a href="/product/main/category?categoryId=71">스포츠/레저</a></li>
						<li><a href="/product/main/category?categoryId=86">게임/음반</a></li>
						<li><a href="/product/main/category?categoryId=90">도서/티켓</a></li>
						<li><a href="/product/main/category?categoryId=96">반려동물용품</a></li>
						<li><a href="/product/main/category?categoryId=105">식물</a></li>
						<li><a href="/product/main/category?categoryId=114">기타</a></li>

					</ul>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="hero__search">
					<div class="hero__search__form">
						<form:form action="/search/search"  method="post" >
							<div class="hero__search__categories">
								All Categories
								<span class="arrow_carrot-down"></span>
							</div>

							<input type="text" name="searchName" placeholder="원하는 상품을 검색하세요">
							<button type="submit" class="site-btn">SEARCH</button>
						</form:form>
					</div>
					<div class="hero__search__phone">
						<div class="hero__search__phone__icon">
							<i class="fa fa-phone"></i>
						</div>
						<div class="hero__search__phone__text">
							<h5>+65 11.188.888</h5>
							<span>support 24/7 time</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</header>
<!-- Header Section End -->


<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="/template/img/breadcrumb.jpg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="breadcrumb__text">
					<h2>Shopping Cart</h2>
					<div class="breadcrumb__option">
						<a href="./index.html">Home</a>
						<span>Shopping Cart</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->