<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품 정보</title>
</head>
<body>
<!-- Product Details Section Begin -->
<section class="product-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
						<div>
							<img class="product__details__pic__item--large"
								 src="img/product/${productFindDTO.representativeImage}" alt=""/>
						</div>
						<c:forEach var="imageList" items="${productFindDTO.productImageDTOList}">
							<img src="/img/product/${imageList.productImageName}" alt=""/>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3>${productFindDTO.subject}</h3>
					<div class="product__details__rating">
						<i class="fa fa-star"></i>
						<i class="fa fa-star"></i>
						<i class="fa fa-star"></i>
						<i class="fa fa-star"></i>
						<i class="fa fa-star-half-o"></i>
						<span>(18 reviews)</span>
					</div>
					<div class="product__details__price"><fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>원</div>
					<div>
						<c:choose>
							<c:when test="${productFindDTO.updateDate ne productFindDTO.uploadDate}">
								<fmt:parseDate var="updateDate" value="${productFindDTO.updateDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<p><fmt:formatDate value="${updateDate}" pattern="yyyy-MM-dd"/></p>
							</c:when>
							<c:when test="${productFindDTO.auctionDeadline ne null}">
								<fmt:parseDate var="uploadDate" value="${productFindDTO.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<fmt:parseDate var="deadline" value="${productFindDTO.auctionDeadline}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<p class="time-title">경매 마감까지 남은 시간</p>
								<div class="time" >
									<span id="d-day-hour"></span>
									<span class="col">:</span>
									<span id="d-day-min"></span>
									<span class="col">:</span>
									<span id="d-day-sec"></span>
								</div>
								<div>
									<div>경매 시작가: <fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>원</div>
									<div>
										경매 입찰기간
										<span>
										<fmt:formatDate value="${uploadDate}" pattern="yyyy/MM/dd HH:mm"/>
										~ <fmt:formatDate value="${deadline}" pattern="yyyy/MM/dd HH:mm"/>
										</span>
										<p>
											입찰가 <input type="text" id="bid" name="bid"/>원
<%--											최소 입찰가 <fmt:formatNumber maxFractionDigits="3" value="${bidFindDTO.price}"/>원--%>
										</p>

									</div>

								</div>
								<input type="hidden" id="upload" value="${uploadDate}">
								<input type="hidden" id="auctionDeadline" value="${deadline}">
							</c:when>
							<c:otherwise>
								<fmt:parseDate var="uploadDate" value="${productFindDTO.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<p><fmt:formatDate value="${uploadDate}" pattern="yyyy-MM-dd"/></p>
							</c:otherwise>
						</c:choose>
					</div>
<%--					<p>Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam--%>
<%--						vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet--%>
<%--						quam vehicula elementum sed sit amet dui. Proin eget tortor risus.</p>--%>
					<c:choose>
						<c:when test="${productFindDTO.memberId eq userSession.id}">
							<div class="product__item">
								<div class="product__item__text">
									<input type="hidden" name="productId" value="${productId}">
									<a href="/product/update/${productId}" class="primary-btn">상품 수정</a>
									<a href="/product/delete/${productId}" class="primary-btn">삭 제</a>
									<a href="#" class="primary-btn">채팅 목록</a>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="product__item">
								<div class="product__item__text">
									<input type="hidden" id="wishId" value="1"/>
									<input type="hidden" id="productId" value="${productId}"/>
									<c:choose>
										<c:when test="${productFindDTO.auctionDeadline ne null}">

											<a href="/bid/bidding/${productId}" hr class="primary-btn">입찰하기</a>
										</c:when>
										<c:otherwise>
											<a href="/order/payment/${productId}" hr class="primary-btn">구매하기</a>
										</c:otherwise>
									</c:choose>
									<a href="/complain/created/${productId}" class="primary-btn">신고하기</a>
									<a href="#" class="primary-btn">채팅하기</a>
									<form action="/wish/addWish/${productId}" method="get">
										<i class="fa fa-heart wishBtn" type="submit"></i></a>찜 1
									</form>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<ul>
						<li><b>Availability</b> <span>${productFindDTO.productProgress}</span></li>
						<li><b>Shipping</b> <span>01 day shipping. <samp>Free pickup today</samp></span></li>
						<li><b>Share on</b>
							<div class="share">
								<a href="#"><i class="fa fa-facebook"></i></a>
								<a href="#"><i class="fa fa-twitter"></i></a>
								<a href="#"><i class="fa fa-instagram"></i></a>
								<a href="#"><i class="fa fa-pinterest"></i></a>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="product__details__tab">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item">
							<a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
							   aria-selected="true">상품 설명</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tabs-1" role="tabpanel">
							<div class="product__details__tab__desc">
								<h6>Products Infomation</h6>
								<p>
									${productFindDTO.content}
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Product Details Section End -->

<!-- Related Product Section Begin -->
<section class="related-product">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title related__product__title">
					<h2>Related Product</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg" data-setbg="img/product/product-1.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6><a href="#">Crab Pool Security</a></h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg" data-setbg="img/product/product-2.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6><a href="#">Crab Pool Security</a></h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg" data-setbg="img/product/product-3.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6><a href="#">Crab Pool Security</a></h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg" data-setbg="img/product/product-7.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6><a href="#">Crab Pool Security</a></h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Related Product Section End -->

</body>
</html>