<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />

	<title>main</title>

	<style>
		.header__menu.profile {
			display: inline-block;
			padding: 0;
			text-align: center;
		}

		.header__cart__price.profile {
			margin-left: 15px;
			font-weight: bold;
			color: black;
			text-align: center;
		}

		.site-btn.mypage {
			height: 50px;
			width: 210px;
			margin-left: 25px;
		}

		.header__nickname {
			text-align: center;
			display: inline-block;
		}

		.header-img.profile {
			width: 32px;
			height: 32px;
			border-radius: 50%;
		}

		.header__logo {
			text-align: center;
		}

		.header__profile {
			display: inline-block;
		}

		.header__cash {
			text-align: right;
			font-weight: normal;
			font-size: 13px;
		}


		@import url(https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css);
		.normal     { font-weight: 400 }
		.bold       { font-weight: 700 }
		.bolder     { font-weight: 800 }
		.light      { font-weight: 300 }


		#wrap {
			margin:0px;
			padding:0px;
			width:100%;
		}


		h1 {
			color:#525252;
			font-size:40px;
			font-weight:100;
			text-align:center;
			margin:0px;
			margin-top:193px;
		}
		h1 > b {
			font-weight:900;
		}

		#best_search {
			width:740px;
			margin:0 auto;
			text-align:center;
		}
		#best_search li,dd {
			display:inline-block;
			vertical-align:middle;
		}

		#best_search li p {
			color:#393939;
			font-size:17px;
			font-weight:bold;
			margin:0px;
			padding-right:30px;
			text-align: center;
		}
		dd {
			margin:0px;
		}
		dd  a.t{
			cursor:pointer;
			margin:0px;
			color:#4b4b4b;
			text-overflow:ellipsis;
			overflow:hidden;
			width:94px;
			white-space:nowrap;
			display:inline-block;
			font-size:17px;
			text-align:left;
			padding-right:13px;
			font-weight:700;
			vertical-align:middle;
		}
		dd .num {

		}
		.best_add {
			border:1px solid #bebebe;
			color:#4b4b4b;
			padding:0px 4px;
			margin-left:10px;
		}


	</style>

	<script>

		function view(arg){
			$(".time1, .time2, .time3, .time4, .ad1, .ad2, .ad3, .ad4").css("display","none");
			if(arg=="0") {
				$(".time2, .ad2").css("display","block");
				viewcount = 1;
			}
			else if(arg=="1") {
				$(".time3, .ad3").css("display","block");
				viewcount = 2;
			}
			else if(arg=="2") {
				$(".time4, .ad4").css("display","block");
				viewcount = 3;
			}
			else if(arg=="3") {
				$(".time1, .ad1").css("display","block");
				viewcount = 0;
			}
		}
		var viewcount = 0;
		var rtcarousel = setInterval(function(){ view(viewcount) },1000);

		$("#best_search").mouseenter(function() {
			clearInterval(rtcarousel);
		});

		$("#best_search").mouseleave(function() {
			rtcarousel = setInterval(function(){ view(viewcount) },1000);
		});


	</script>

</head>
<body>
<section class="spad">

	<!-- 광고 -->
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="hero__item set-bg" data-setbg="/template/img/hero/banner2.png"></div>
				</div>
			</div>
		</div>
	</section>
	<!-- /광고 -->

	<%--<section class="from-blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title from-blog__title">
						<a href="/search/search">   <h2>중고거래 인기 검색어</h2></a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div style="text-align: center; font-size: 20px; font-weight:700;">실시간 인기검색어</div>
	<div id="wrap">
		<div class="container">
			<ul id="best_search">


				<li>
					<dl class="time1" style="display:none">

						<c:forEach var="searchRank" items="${searchRank}">
							<dd><a class="t" href="#"><div class="num"></div>${searchRank.searchName}</a></dd>
							</c:forEach>
					</dl>

				</li>

				<li>
&lt;%&ndash;					<a class="best_add ad1" style="cursor:pointer" onClick="javascript:view('0')">&#62;</a>&ndash;%&gt;
&lt;%&ndash;					<a class="best_add ad2" onClick="javascript:view('1')" style="display:none;cursor:pointer" >&#62;</a>&ndash;%&gt;
&lt;%&ndash;					<a class="best_add ad3" onClick="javascript:view('2')" style="display:none;cursor:pointer" >&#62;</a>&ndash;%&gt;
&lt;%&ndash;					<a class="best_add ad4" onClick="javascript:view('3')" style="display:none;cursor:pointer" >&#60;</a>&ndash;%&gt;
				</li>
			</ul>
		</div>
	</div>--%>

	<!-- Featured Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<h2>새 상품</h2>
					</div>
					<%--<div class="featured__controls">
						<ul>
							<li class="active" data-filter="*">All</li>
							<li data-filter=".oranges">Oranges</li>
							<li data-filter=".fresh-meat">Fresh Meat</li>
							<li data-filter=".vegetables">Vegetables</li>
							<li data-filter=".fastfood">Fastfood</li>
						</ul>
					</div>--%>
				</div>
			</div>
			<div class="row">
				<c:forEach var="productDTO" items="${newProductList}">
					<div class="col-lg-3 col-md-4 col-sm-6">
						<div class="featured__item">
							<div class="product__item__pic radius set-bg" data-setbg="/img/product/${productDTO.productImageName}"
									 onclick="location.href='/product/detail/${productDTO.productId}';">
								<div class="pay__auction__image">
									<c:if test="${productDTO.payStatus eq true}"><img src="/template/img/product/pay.png"></c:if>
									<c:if test="${productDTO.auctionDeadline ne null}"><img src="/template/img/product/auction.png"></c:if>
								</div>
							</div>
							<div class="product__item__text">
								<h6><a href="/product/detail/${productDTO.productId}">${productDTO.subject}</a></h6>
								<fmt:parseDate var="uploadDate" value="${productDTO.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<div>
									<dl class="product__list">
										<dd class="product__list__price"><fmt:formatNumber maxFractionDigits="3" value="${productDTO.price}"/> 원</dd>
										<dd class="product__list__upload"><fmt:formatDate value="${uploadDate}" pattern="MM/dd"/></dd>
									</dl>
								</div>
								<ul class="product__wish__item">
									<input type="hidden" id="productId" value="${productDTO.productId}"/>
									<c:if test="${productDTO.wishCheck eq true}">
										<li class="wishBtn true"><a><i class="fa fa-heart"></i></a></li>
									</c:if>
									<c:if test="${productDTO.wishCheck ne true}">
										<li class="wishBtn"><a><i class="fa fa-heart"></i></a></li>
									</c:if>
									<li><a><i class="fa fa-solid fa-comment" onclick="createChatroom(${productDTO.productId})"></i></a></li>
									<li><a href="/order/payment/${productDTO.productId}"><i class="fa fa-shopping-cart"></i></a></li>
								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="col-12 text-align-center">
					<button type="button" class="site-btn" onclick="location.href='/product/wishRangeList?page=1'">상품 더보기</button>
				</div>
			</div>
		</div>
	</section>
	<!-- Featured Section End -->

<%--
	<!-- Banner Begin -->
	<div class="banner">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="banner__pic">
						<img src="/template/img/banner/banner-1.jpg" alt="">
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="banner__pic">
						<img src="/template/img/banner/banner-2.jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Banner End -->
--%>

	<!-- Latest Product Section Begin -->
	<section class="latest-product spad">
		<div class="container">
			<div class="row">

				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>조회수</h4>
						<div class="latest-product__slider owl-carousel">
							<c:set var="i" value="0"/>
							<c:forEach var="productDTO" items="${topViewProductList}">
								<c:if test="${i % 3 == 0}">
									<div class="latest-prdouct__slider__item">
								</c:if>
								<a href="/product/detail/${productDTO.productId}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/img/product/${productDTO.productImageName}" alt="${productDTO.subject}" style="width: 100px; height: 100px">
									</div>
									<div class="latest-product__item__text">
										<h6>${productDTO.subject}</h6>
										<span><fmt:formatNumber maxFractionDigits="3" value="${productDTO.price}"/>원</span>
									</div>
								</a>
								<c:if test="${i % 3 == 2 || i == topViewProductList.size() - 1}">
									</div>
								</c:if>
								<c:set var="i" value="${i + 1}"/>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>낮은 가격</h4>
						<div class="latest-product__slider owl-carousel">
							<c:set var="i" value="0"/>
							<c:forEach var="productDTO" items="${lowPriceProductList}">
								<c:if test="${i % 3 == 0}">
									<div class="latest-prdouct__slider__item">
								</c:if>
								<a href="/product/detail/${productDTO.productId}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/img/product/${productDTO.productImageName}" alt="${productDTO.subject}" style="width: 100px; height: 100px">
									</div>
									<div class="latest-product__item__text">
										<h6>${productDTO.subject}</h6>
										<span><fmt:formatNumber maxFractionDigits="3" value="${productDTO.price}"/>원</span>
									</div>
								</a>
								<c:if test="${i % 3 == 2 || i == lowPriceProductList.size() - 1}">
									</div>
								</c:if>
								<c:set var="i" value="${i + 1}"/>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>경매 상품</h4>
						<div class="latest-product__slider owl-carousel">
							<c:set var="i" value="0"/>
							<c:forEach var="productDTO" items="${auctionProductList}">
								<c:if test="${i % 3 == 0}">
									<div class="latest-prdouct__slider__item">
								</c:if>
								<a href="/product/detail/${productDTO.productId}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/img/product/${productDTO.productImageName}" alt="${productDTO.subject}" style="width: 100px; height: 100px">
									</div>
									<div class="latest-product__item__text">
										<h6>${productDTO.subject}</h6>
										<span><fmt:formatNumber maxFractionDigits="3" value="${productDTO.price}"/>원</span>
									</div>
								</a>
								<c:if test="${i % 3 == 2 || i == auctionProductList.size() - 1}">
									</div>
								</c:if>
								<c:set var="i" value="${i + 1}"/>
							</c:forEach>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- Latest Product Section End -->

	<!-- Blog Section Begin -->
	<%--<section class="from-blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title from-blog__title">
						<h2>From The Blog</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="/template/img/blog/blog-1.jpg" alt="">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 4,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5><a href="#">Cooking tips make cooking simple</a></h5>
							<p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="/template/img/blog/blog-2.jpg" alt="">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 4,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5><a href="#">6 ways to prepare breakfast for 30</a></h5>
							<p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="/template/img/blog/blog-3.jpg" alt="">
						</div>
						<div class="blog__item__text">
							<ul>
								<li><i class="fa fa-calendar-o"></i> May 4,2019</li>
								<li><i class="fa fa-comment-o"></i> 5</li>
							</ul>
							<h5><a href="#">Visit the clean farm in the US</a></h5>
							<p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>--%>
	<!-- Blog Section End -->
</section>

</body>
</html>
