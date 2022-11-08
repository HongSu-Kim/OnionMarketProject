<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                    <a href="/"><img src="/template/img/logo2.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li><a href="/product/wishRangeList">상품</a>
                            <ul class="header__menu__dropdown">
                                <li><a href="/product/wishRangeList">동네 상품</a></li>
                                <li><a href="/product/auctionList">경매 상품</a></li>
                                <li><a href="/product/add">상품 등록</a></li>
                            </ul>
                        </li>
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="/order/buyList">거래내역</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="/order/buyList">구매 목록</a></li>
                                    <li><a href="/order/sellList">판매 목록</a></li>
                                    <li><a href="/wish/list">찜 목록</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <li><a href="#">공지사항</a>
                            <ul class="header__menu__dropdown">
                                <li><a href="/notice/list">Notice List</a></li>
                                <li><a href="/inquiry/list">Inquiry List</a></li>
                            </ul>
                        </li>
                        <sec:authorize access="isAnonymous()">
                            <li><a href="#">로그인</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="/member/login">로그인</a></li>
                                    <li><a href="/member/join">회원가입</a></li>

                                </ul>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href="/member/mypage">마이페이지</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="/member/mypage">마이페이지</a></li>
                                    <li><a href="/member/preModify">회원정보 수정</a></li>
                                    <li><a href="/member/cash/<sec:authentication property="principal.sessionDTO.id"/>">양파페이 충전</a></li>
                                    <li><a href="/town/town">동네 설정</a></li>
                                    <li><a href="/keyword/keyword">키워드 설정</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="#">관리자페이지</a>
                                <ul class="header__menu__dropdown">
                                    <li><a onclick="confirm('크롤링') ? location.href='/crawling' : false">crawling</a>
                                    </li>
                                    <li><a href="/member/manageMember">회원 관리</a></li>
                                    <li><a href="/prohibitionkeyword/prohibitionkeyword">금지어 관리</a></li>
                                    <li><a href="/category/category"> 카테고리 관리 </a></li>
                                    <li><a href="/complain/list"> 신고 관리 </a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <sec:authorize access="isAuthenticated()">
                        <ul>
                            <li><a href="/wish/list"><i class="fa fa-heart"></i> <span>${wishCount}</span></a></li>
                        </ul>
                        <nav class="header__menu profile">
                            <ul>
                                <li><a href="/member/mypage" class="header__nickname"><img src="/img/member/${memberImageName}" class="header-img profile">&nbsp;<sec:authentication property="principal.sessionDTO.nickname"/> 님</a>
                                    <ul class="header__menu__dropdown nickname">
                                        <li><a href="/member/mypage">마이페이지</a></li>
                                        <sec:authorize access="hasRole('USER')">
                                            <li><a href="/member/cash/<sec:authentication property="principal.sessionDTO.id"/>">양파페이 충전</a></li>
                                            <li><a href="/wish/list">위시리스트</a></li>
                                            <li><a href="/review/mylist/<sec:authentication property="principal.sessionDTO.id"/>">나의 후기</a></li>
                                            <li><a href="/inquiry/myList/<sec:authentication property="principal.sessionDTO.id"/>">나의 문의</a></li>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <li><a href="/member/manageMember/">회원관리</a></li>
                                        </sec:authorize>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('USER') and !hasRole('ADMIN')">
                        <div class="header__cart__price profile"><a href="/member/join"
                                                                    class="header__cart__price profile">회원가입</a></div>
                        <div class="header__cart__price profile"><a href="/member/login"
                                                                    class="header__cart__price profile">로그인</a></div>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <div class="header__profile"><a href="/member/logout" class="header__cart__price logout">로그아웃</a></div>
                        <div class="header__cash">양파페이 잔액 : <a href="/member/cash/<sec:authentication property="principal.sessionDTO.id"/>">${cash}</a></div>
                    </sec:authorize>
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
                    <ul><c:forEach var="topCategory" items="${topCategory}">
                        <li><a href="/product/category?categoryId=${topCategory.categoryId}">${topCategory.categoryName}</a>
                        </li>

                    </c:forEach>


                    </ul>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="hero__search">
                    <div class="hero__search__form">
                        <form:form action="/search/list" method="get">

                            <input type="text" name="searchName" placeholder="원하는 상품을 검색하세요">
                            <button type="submit" class="site-btn">SEARCH</button>
                        </form:form>
                    </div>
                    <sec:authorize access="!hasRole('USER') and !hasRole('ADMIN') and !hasRole('WITHDRAWAL')">
                        <button onclick="location.href='/member/login'" class="site-btn mypage">ONION MARKET 로그인
                        </button>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <button onclick="location.href='/member/mypage'" class="site-btn mypage">마이페이지</button>
                    </sec:authorize>
                    <%--					<div class="hero__search__phone">
                                            <div class="hero__search__phone__icon">
                                                <i class="fa fa-phone"></i>
                                            </div>
                                            <div class="hero__search__phone__text">
                                                <h5>+65 11.188.888</h5>
                                                <span>support 24/7 time</span>
                                            </div>
                                        </div>--%>
                </div>
            </div>
        </div>
    </div>

</header>
<!-- Header Section End -->

<!-- Breadcrumb Section Begin -->
<c:if test="${pageName != 'main'}">
    <section class="breadcrumb-section set-bg" data-setbg="/template/img/slogan.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>&nbsp;</h2>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>
<!-- Breadcrumb Section End -->