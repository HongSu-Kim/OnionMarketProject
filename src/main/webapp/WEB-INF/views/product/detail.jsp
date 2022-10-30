<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
		dl { }
		dt { float: left; margin-right: 10px;}
		dd { float: left; margin-right: 10px;}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품 정보</title>
</head>
<body>
<!-- Product Details Section Begin -->
<form action="/product/bid/${productId}" method="get" enctype="multipart/form-data" id="productForm">
<section class="product-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
<%--							<img class="product__details__pic__item--large" src="img/product/${productFindDTO.representativeImage}" alt=""/>--%>
						<ul>
							<li class="large-image"><img alt="ttt"></li>
						<c:forEach var="imageList" items="${productFindDTO.productImageDTOList}">
							<li style="display: flex"><img src="/img/product/${imageList.productImageName}" alt=""/></li>
						</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3>${productFindDTO.subject}</h3>
					<div class="product__details__rating">
						<c:choose>
							<c:when test="${reviewAvg ne null}">
								리뷰 평점: ${reviewAvg}
							</c:when>
							<c:otherwise>
								<p>판매자의 등록된 리뷰가 아직 없습니다.</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						<div class="product__details__price">
						<c:choose>
							<c:when test="${!empty bid}"><fmt:formatNumber maxFractionDigits="3" value="${bid}"/>원</c:when>
							<c:otherwise><fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>원</c:otherwise>
						</c:choose>
						<input type="hidden" id="nowPrice" value="${productFindDTO.price}">
						</div>
						<c:choose>
							<c:when test="${not empty productFindDTO.auctionDeadline}">
								<fmt:parseDate var="uploadDate" value="${productFindDTO.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<fmt:parseDate var="deadline" value="${productFindDTO.auctionDeadline}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<input type="hidden" id="dead" value="${productFindDTO.auctionDeadline}"/>
								<p class="time-title">경매 마감까지 남은 시간</p>
								<div class="time" >
									<span id="d-day-hour"></span>
									<span class="col">:</span>
									<span id="d-day-min"></span>
									<span class="col">:</span>
									<span id="d-day-sec"></span>
								</div>
								<div>
									<div>
										현재가:
										<c:choose>
											<c:when test="${!empty bid}"><fmt:formatNumber maxFractionDigits="3" value="${bid}"/>원</c:when>
											<c:otherwise><fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>원</c:otherwise>
										</c:choose>
									</div>
									<div>경매 시작가: <fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>원</div>
									<div>
										경매 입찰기간
										<span>
										<fmt:formatDate value="${uploadDate}" pattern="yyyy/MM/dd HH:mm"/>
										~ <fmt:formatDate value="${deadline}" pattern="yyyy/MM/dd HH:mm"/>${dead}
										</span>

										<div>
											최소 입찰가:
											<c:choose>
												<c:when test="${not empty bid}">
													<fmt:formatNumber maxFractionDigits="3" value="${bid}"/>(원)
												</c:when>
												<c:otherwise>
													<fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>(원)
												</c:otherwise>
											</c:choose>
										</div>
<%--										<div id="limit_price_desc" style="margin-top:-8px; margin-left:0px; padding:10px; display:none; position:absolute; border:2px solid #3baecb; background:#f6f7f8; text-align:left;line-height:1.4; z-index:10">--%>
<%--											<strong>최소입찰가</strong>는 <strong>현재가 금액 단위별로 일정금액</strong>이 증가됩니다. <br><strong>최소입찰가 이상</strong>으로 입찰해 주세요.<br/><br/>--%>
<%--											<div>--%>
<%--												<dl height="17">--%>
<%--													<dt bgcolor="#fff9f3" style="text-align:center"><strong>입찰금액 범위</strong></dt>--%>
<%--													<dt bgcolor="#fff9f3" style="text-align:center"><strong>최소입찰 단위</strong></dt>--%>
<%--													<br/>--%>
<%--												</dl>--%>
<%--												<dl>--%>
<%--													<dt bgcolor="#ffffff" style="text-align:center">시작가  ~  10,000원 미만</dt>--%>
<%--													<dt bgcolor="#ffffff" style="text-align:center">1,000원</dt>--%>
<%--												</dl>--%>
<%--												<dl>--%>
<%--													<dt bgcolor="#ffffff" style="text-align:center">10,000원  ~  100,000원 미만</dt>--%>
<%--													<dt bgcolor="#ffffff" style="text-align:center">5,000원</dt>--%>
<%--												</dl>--%>
<%--												<dl>--%>
<%--													<dt bgcolor="#ffffff" style="text-align:center">100,000원  ~  500,000원 미만</dt>--%>
<%--													<dd bgcolor="#ffffff" style="text-align:center">10,000원</dd>--%>
<%--												</dl>--%>
<%--												<dl>--%>
<%--													<dt bgcolor="#ffffff" style="text-align:center">500,000원이상 ~</dt>--%>
<%--													<dd bgcolor="#ffffff" style="text-align:center">50,000원</dd>--%>
<%--												</dl>--%>
<%--											</div>--%>
<%--										</div>--%>
										<div>
											<dl>
												<dt>입찰자</dt>
												<dt>금액</dt>
												<dt>입찰시간</dt>
												<hr/>
												<br/>
											</dl>
											<c:choose>
												<c:when test="${not empty biddingList}">
													<c:forEach var="biddingList" items="${biddingList}">
														<dl>
															<dd>${biddingList.userId}</dd>
															<dd>${biddingList.bid}</dd>
															<dd>
																<fmt:parseDate var="biddingTime" value="${biddingList.biddingTime}" pattern="yyyy-MM-dd'T'HH:mm"/>
																<fmt:formatDate value="${biddingTime}" pattern="yyyy/MM/dd HH:mm"/>
															</dd>
															<br/>
														</dl>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<dd>등록된 입찰내역이 없습니다.</dd><br/>
												</c:otherwise>
											</c:choose>

										</div>
									</div>
								</div>
							</c:when>
							<c:when test="${productFindDTO.updateDate ne productFindDTO.uploadDate}">
								<fmt:parseDate var="updateDate" value="${productFindDTO.updateDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<p><fmt:formatDate value="${updateDate}" pattern="yyyy/MM/dd HH:mm"/></p>
							</c:when>
							<c:otherwise>
								<fmt:parseDate var="uploadDate" value="${productFindDTO.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<p><fmt:formatDate value="${uploadDate}" pattern="yyyy-MM-dd HH:mm"/></p>
							</c:otherwise>
						</c:choose>
					</div>
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
										<p style="text-align: left">
											입찰가(원): <input type="text" id="nowBid" name="bid" placeholder="가격을 입력하세요"/>
											<input type="hidden" id="exBid" value="${bid}"/>
										</p>
											<input type="submit" class="primary-btn" value="입찰하기" style="border: 0px"></a>
										</c:when>
										<c:otherwise>
											<a href="/order/payment/${productId}" class="primary-btn">구매하기</a>
										</c:otherwise>
									</c:choose>
									<a href="/complain/created" class="primary-btn">신고하기</a>
									<button type="button" class="primary-btn" onclick="createChatroom(${productId})">채팅하기</button>
									<a href="/wish/addWish/${productId}"><div class="primary-btn"><i class="fa fa-heart wishBtn"></i>찜하기</div></a>
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
</form>

<!-- Product Details Section End -->

<!-- Related Product Section Begin -->
<section class="related-product">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title related__product__title">
					<h2>연 관  상 품</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<c:forEach var="categoryDTO" items="${categoryDTO}" end="3">
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg" data-setbg="/img/product/${categoryDTO.representativeImage}">
						<ul class="product__item__pic__hover">
							<li><a href="/wish/addWish/${productId}"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6><a href="/product/detail/${categoryDTO.productId}">${categoryDTO.subject}</a></h6>
						<h5><fmt:formatNumber maxFractionDigits="3" value="${categoryDTO.price}"/>원</h5>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</section>
<!-- Related Product Section End -->
</body>
</html>