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
                        <li><a href="/product/wishRangeList">??????</a>
                            <ul class="header__menu__dropdown">
                                <li><a href="/product/wishRangeList">?????? ??????</a></li>
                                <li><a href="/product/auctionList">?????? ??????</a></li>
                                <li><a href="/product/add">?????? ??????</a></li>
                            </ul>
                        </li>
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="/order/buyList">????????????</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="/order/buyList">?????? ??????</a></li>
                                    <li><a href="/order/sellList">?????? ??????</a></li>
                                    <li><a href="/wish/list">??? ??????</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <li><a href="#">????????????</a>
                            <ul class="header__menu__dropdown">
                                <li><a href="/notice/list">?????? ??????</a></li>
                                <li><a href="/inquiry/list">1:1 ??????</a></li>
                            </ul>
                        </li>
                        <sec:authorize access="isAnonymous()">
                            <li><a href="#">?????????</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="/member/login">?????????</a></li>
                                    <li><a href="/member/join">????????????</a></li>

                                </ul>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href="/member/mypage">???????????????</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="/member/mypage">???????????????</a></li>
                                    <li><a href="/member/preModify">???????????? ??????</a></li>
                                    <li><a href="/member/cash/<sec:authentication property="principal.sessionDTO.id"/>">???????????? ??????</a></li>
                                    <li><a href="/town/town">?????? ??????</a></li>
                                    <li><a href="/keyword/keyword">????????? ??????</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="#">??????????????????</a>
                                <ul class="header__menu__dropdown">
                                    <li><a onclick="confirm('?????????') ? location.href='/crawling' : false">crawling</a>
                                    </li>
                                    <li><a href="/member/manageMember">?????? ??????</a></li>
                                    <li><a href="/prohibitionkeyword/prohibitionkeyword">????????? ??????</a></li>
                                    <li><a href="/category/category"> ???????????? ?????? </a></li>
                                    <li><a href="/complain/list"> ?????? ?????? </a></li>
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
                                <li><a href="/member/mypage" class="header__nickname"><img src="/img/member/${memberImageName}" class="header-img profile">&nbsp;<sec:authentication property="principal.sessionDTO.nickname"/> ???</a>
                                    <ul class="header__menu__dropdown nickname">
                                        <li><a href="/member/mypage">???????????????</a></li>
                                        <sec:authorize access="hasRole('USER')">
                                            <li><a href="/member/cash/<sec:authentication property="principal.sessionDTO.id"/>">???????????? ??????</a></li>
                                            <li><a href="/wish/list">???????????????</a></li>
                                            <li><a href="/review/mylist/<sec:authentication property="principal.sessionDTO.id"/>">?????? ??????</a></li>
                                            <li><a href="/inquiry/myList/<sec:authentication property="principal.sessionDTO.id"/>">?????? ??????</a></li>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <li><a href="/member/manageMember/">????????????</a></li>
                                        </sec:authorize>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('USER') and !hasRole('ADMIN')">
                        <div class="header__cart__price profile"><a href="/member/join"
                                                                    class="header__cart__price profile">????????????</a></div>
                        <div class="header__cart__price profile"><a href="/member/login"
                                                                    class="header__cart__price profile">?????????</a></div>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <div class="header__profile"><a href="/member/logout" class="header__cart__price logout">????????????</a></div>
                        <div class="header__cash">???????????? ?????? : <a href="/member/cash/<sec:authentication property="principal.sessionDTO.id"/>">${cash}</a></div>
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

                            <input type="text" name="searchName" placeholder="????????? ????????? ???????????????">
                            <button type="submit" class="site-btn">SEARCH</button>
                        </form:form>
                    </div>
                    <sec:authorize access="!hasRole('USER') and !hasRole('ADMIN') and !hasRole('WITHDRAWAL')">
                        <button onclick="location.href='/member/login'" class="site-btn mypage">ONION MARKET ?????????
                        </button>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <button onclick="location.href='/member/mypage'" class="site-btn mypage">???????????????</button>
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