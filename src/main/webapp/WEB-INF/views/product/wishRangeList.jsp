<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!-- Featured Section Begin -->
<section class="featured spad">
	<div class="container">
		<div class="row featured__filter">
			<!-- 주문없음 -->
			<c:if test="${empty distancePagelist}">
				<div class="col-lg-12">
					<div class="contact__form__title">
						<h3>등록된 상품이 없습니다.</h3>
						<button type="button" class="site-btn mt-5" onclick="location.href='/product/list'">상품 보러가기</button>
					</div>
				</div>
			</c:if>
			<c:forEach var="productDTO" items="${distancePagelist}">
				<div class="col-lg-3 col-md-4 col-sm-6" style="padding: 15px;">
					<div class="featured__item">
						<div class="product__item__pic set-bg" data-setbg="/img/product/${productDTO.productImageName}"
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
	<!-- List Paging -->
	<div class="row">
		<div class="col-lg-12">

			<!-- 페이징 -->
			<c:if test="${!empty distancePage.content && distancePage.totalPages != 1}">
				<input type="hidden" id="pageNumber" value="${distancePage.number + 1}"/>
				<div class="product__pagination text-center">
					<c:set var="size" value="${distancePage.pageable.pageSize}"/><%-- 12 --%>
					<fmt:parseNumber var="pageNumber" integerOnly="true" value="${distancePage.number / size}"/><%-- 현재페이지 : 0 ~ --%>
					<c:set var="startNumber" value="${pageNumber * size}"/><%-- 0 * size ~ --%>
					<c:set var="endNumber" value="${distancePage.totalPages > (pageNumber + 1) * size ? (pageNumber + 1) * size - 1 : distancePage.totalPages - 1}"/>

					<c:if test="${distancePage.totalPages > size && distancePage.number + 1 > size}">
						<a href="?page=0"><<</a>
						<a href="?page=${startNumber - 1}"><</a>
					</c:if>
					<c:forEach var="currentNumber" begin="${startNumber}" end="${endNumber}">
						<a href="?page=${currentNumber}">${currentNumber + 1}</a>
					</c:forEach>
					<c:if test="${distancePage.totalPages - 1 > endNumber}">
						<a href="?page=${endNumber + 1}">></a>
						<a href="?page=${distancePage.totalPages - 1}">>></a>
					</c:if>
				</div>
			</c:if>

		</div>
	</div>

</section>