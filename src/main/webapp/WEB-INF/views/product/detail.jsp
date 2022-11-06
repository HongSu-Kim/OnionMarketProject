<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!-- Product Details Section Begin -->
<form action="/product/bid" method="post" id="productForm">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<input type="hidden" name="productId" value="${productFindDTO.productId}">
	<section class="product-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
						<div id="bigImage">
							<img src="/img/product/${productFindDTO.representativeImage}" class="product__rimage" id="big">
						</div>
						<ul id="smallImage">
							<c:forEach var="imageList" items="${productFindDTO.productImageDTOList}">
								<img src="/img/product/${imageList.productImageName}" class="product__image" id="small"/>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 content">
				<div class="product__details__text">
					<h3>${productFindDTO.subject}</h3>
					<div>
						<span>판매자: <a href="/member/profile/${productFindDTO.memberId}">${productFindDTO.nickname}</a></span>
					</div>
					<div class="product__details__rating">
						<c:choose>
							<c:when test="${reviewAvg ne 0.0}">
								리뷰 평점: ${reviewAvg} / 5 점
							</c:when>
							<c:otherwise>
								<p>판매자의 등록된 리뷰가 아직 없습니다.</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty productFindDTO.auctionDeadline}">
								<fmt:parseDate var="uploadDate" value="${productFindDTO.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<fmt:parseDate var="deadline" value="${productFindDTO.auctionDeadline}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<input type="hidden" id="dead" value="${productFindDTO.auctionDeadline}"/>
								<span>경매 마감까지 남은 시간</span>
								<div class="auction__time__price">
									<div class="time">
										<span id="d-day-hour"></span>
										<span class="col">:</span>
										<span id="d-day-min"></span>
										<span class="col">:</span>
										<span id="d-day-sec"></span>
									</div>
									<div>
										현재가:
										<span class="now__price">
											<c:choose>
												<c:when test="${!empty bid}"><fmt:formatNumber maxFractionDigits="3" value="${bid}"/>원</c:when>
												<c:otherwise><fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>원</c:otherwise>
											</c:choose>
											<input type="hidden" id="nowPrice" value="${productFindDTO.price}">
										</span>
									</div>
								</div>
								<div>
									<hr/>
									<div class="auction__board">
										<table>
											<tr>
												<td>💰경매 시작가</td>
												<td><fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>원</td>
											</tr>
											<tr>
												<td>🕒경매 입찰기간</td>
												<td>
													<span>
														<fmt:formatDate value="${uploadDate}" pattern="yyyy/MM/dd HH:mm"/>
														~ <fmt:formatDate value="${deadline}" pattern="yyyy/MM/dd HH:mm"/>
													</span>
												</td>
											</tr>
											<tr>
												<td>최소 입찰가</td>
												<td>
													<c:choose>
													<c:when test="${not empty bid}">
														<fmt:formatNumber maxFractionDigits="3" value="${bid}"/>(원)
													</c:when>
													<c:otherwise>
														<fmt:formatNumber maxFractionDigits="3" value="${productFindDTO.price}"/>(원)
													</c:otherwise>
													</c:choose>
													<input type="hidden" id="exBid" value="${bid}"/>
												</td>
											</tr>
										</table>
									</div>
									<hr/>
									<div class="board">
										<dl>
											<dt>입찰자</dt><dt>금액</dt><dt>입찰시간</dt>
										</dl>
										<c:choose>
											<c:when test="${not empty biddingList}">
												<c:forEach var="bidding" items="${biddingList}">
													<hr/>
													<dl>
														<dd>${bidding.userId}</dd>
														<dd>${bidding.bid}</dd>
														<dd>
															<fmt:parseDate var="biddingTime" value="${bidding.biddingTime}" pattern="yyyy-MM-dd'T'HH:mm"/>
															<fmt:formatDate value="${biddingTime}" pattern="yyyy/MM/dd HH:mm"/>
														</dd>
													</dl>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<dd>등록된 입찰내역이 없습니다.</dd><br/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:when>
							<c:when test="${productFindDTO.updateDate ne productFindDTO.uploadDate}">
								<fmt:parseDate var="updateDate" value="${productFindDTO.updateDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<p> 등록일 : <fmt:formatDate value="${updateDate}" pattern="yyyy/MM/dd HH:mm"/></p>
							</c:when>
							<c:otherwise>
								<fmt:parseDate var="uploadDate" value="${productFindDTO.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
								<p> 등록일 :  <fmt:formatDate value="${uploadDate}" pattern="yyyy-MM-dd HH:mm"/></p>
							</c:otherwise>
						</c:choose>
					</div>
					<c:choose>
						<c:when test="${productFindDTO.memberId eq userSession.id}">
							<div class="product__item">
								<div class="product__item__text">
									<a href="#" class="primary-btn">채팅 목록</a>
									<c:if test="${empty productFindDTO.auctionDeadline}">
										<a href="/product/update/${productFindDTO.productId}" class="primary-btn">상품 수정</a>
									</c:if>
									<a href="/product/delete/${productFindDTO.productId}" class="primary-btn">삭 제</a>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="product__item">
								<div class="product__item__text">
									<input type="hidden" id="wishId" value="1"/>
									<c:choose>
										<c:when test="${productFindDTO.auctionDeadline ne null}">
										<p>
											입찰가(원): <input type="text" name="bid" placeholder="가격을 입력하세요"/>
										</p>
										<input type="submit" class="primary-btn" value="입찰하기"></a>
										</c:when>
										<c:otherwise>
											<a href="/order/payment/${productFindDTO.productId}" class="primary-btn">구매하기</a>
										</c:otherwise>
									</c:choose>
									<a href="/complain/created/${productFindDTO.productId}" class="primary-btn">신고하기</a>
									<button type="button" class="primary-btn" onclick="createChatroom(${productFindDTO.productId})">채팅하기</button>
									<a href="/wish/addWish/${productFindDTO.productId}"><div class="primary-btn"><i class="fa fa-heart wishBtn"></i>찜하기</div></a>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<ul>
						<li><b>상품상태</b> <span>${productFindDTO.productProgress}</span></li>
						<li><b>결제방법</b> <span>01 day shipping. <samp>Free pickup today</samp></span></li>
						<li><b>공유하기</b>
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
							   aria-selected="true">상품 정보</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tabs-1" role="tabpanel">
							<div class="product__details__tab__desc">
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
					<h2>연 관 상 품</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<c:forEach var="categoryDTO" items="${categoryDTO}" end="3">
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg" data-setbg="/img/product/${categoryDTO.representativeImage}" onclick="location.href='/product/detail/${categoryDTO.productId}';">
						<ul class="product__item__pic__hover">
							<li><a href="/wish/addWish/${categoryDTO.productId}"><i class="fa fa-heart"></i></a></li>
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
