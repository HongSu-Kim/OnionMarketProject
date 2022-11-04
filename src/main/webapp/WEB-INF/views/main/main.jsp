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

</head>
<body>
<section class="spad">

	<!-- 광고 -->
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="hero__item set-bg" data-setbg="/template/img/hero/banner.jpg">
						<div class="hero__text">
							<span>FRUIT FRESH</span>
							<h2>Vegetable <br />100% Organic</h2>
							<p>Free Pickup and Delivery Available</p>
							<a href="#" class="primary-btn">SHOP NOW</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- /광고 -->

	<!-- Categories Section Begin -->
	<%--<section class="categories">
		<div class="container">
			<div class="row">
				<div class="categories__slider owl-carousel">
					<c:forEach var="productListDTO" items="${hotProductList}">
						<div class="col-lg-3">
							<div class="categories__item set-bg" data-setbg="/img/product/${productListDTO.productImageName}" onclick="location.href='/product/detail/${productListDTO.productId}';">
								<h5><a href="/product/detail/${productListDTO.productId}">${productListDTO.subject}</a></h5>
							</div>
						</div>
					</c:forEach>
					<div class="col-lg-3">
						<div class="categories__item set-bg" data-setbg="/template/img/categories/cat-1.jpg">
							<h5><a href="#">Fresh Fruit</a></h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>--%>
	<!-- Categories Section End -->

	<!-- Featured Section Begin -->
	<section class="featured spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<h2>Featured Product</h2>
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
					<div class="col-lg-3 col-md-4 col-sm-6" style="padding: 15px;">
						<div class="featured__item">
							<div class="featured__item__pic set-bg" data-setbg="/img/product/${productDTO.productImageName}"
									 onclick="location.href='/product/detail/${productDTO.productId}';">
								<div style="margin-top:93%;">
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
									<li><a href="#"><i class="fa fa-solid fa-comment" onclick="createChatroom(${productDTO.productId})"></i></a></li>
									<li><a href="/order/payment/${productDTO.productId}"><i class="fa fa-shopping-cart"></i></a></li>
								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Featured Section End -->

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

	<!-- Latest Product Section Begin -->
	<section class="latest-product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>Latest Products</h4>
						<div class="latest-product__slider owl-carousel">
							<div class="latest-prdouct__slider__item">
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-1.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-2.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-3.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
							</div>
							<div class="latest-prdouct__slider__item">
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-1.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-2.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-3.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>Top Rated Products</h4>
						<div class="latest-product__slider owl-carousel">
							<div class="latest-prdouct__slider__item">
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-1.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-2.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-3.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
							</div>
							<div class="latest-prdouct__slider__item">
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-1.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-2.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-3.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="latest-product__text">
						<h4>Review Products</h4>
						<div class="latest-product__slider owl-carousel">
							<div class="latest-prdouct__slider__item">
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-1.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-2.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-3.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
							</div>
							<div class="latest-prdouct__slider__item">
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-1.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-2.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
								<a href="#" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/template/img/latest-product/lp-3.jpg" alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>Crab Pool Security</h6>
										<span>$30.00</span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Latest Product Section End -->

	<!-- Blog Section Begin -->
	<section class="from-blog spad">
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
	</section>
	<!-- Blog Section End -->

	<section class="from-blog spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title from-blog__title">
					<a href="/search/search">   <h2>중고거래 인기 검색어</h2></a>
				</div>
			</div>
		</div>

		<div class="featured__controls">
			<ul>
				<li >
					<a href="top_keywords" style="color: black">All</a>
				</li>
				<li >
					<a href="top_keywords" style="color: black">All</a>
				</li>

				<li >
					<a href="top_keywords" style="color: black">All</a>
				</li>

				<li >
					<a href="top_keywords" style="color: black">All</a>
				</li>

				<li >
					<a href="top_keywords" style="color: black">All</a>
				</li>
			</ul>
		</div>

	</div>
</section>







</body>
</html>
