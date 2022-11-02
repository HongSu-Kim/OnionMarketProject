<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">



<!-- Featured Section Begin -->
<section class="featured spad">
	<div class="container">
		<div class="product__townList">
			<c:if test="${townList ne null}">


			</c:if>
		</div>
		<br/><br/>
		<div class="row featured__filter">
			<c:forEach var="list" items="${distancePagelist}">
				<div class="col-lg-3 col-md-4 col-sm-6" style="padding: 15px;">
					<div class="featured__item">
						<div class="featured__item__pic set-bg" data-setbg="/img/product/${list.productImageName}">
							<ul class="featured__item__pic__hover">
								<li><a href="/wish/addWish/${list.productId}"><i class="fa fa-heart"></i></a></li>
								<li><a href="#"><i class="fa fa-retweet"></i></a></li>
								<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
							</ul>
							<div style="margin-top:93%;"><c:if test="${list.payStatus eq true}"><img src="/template/img/product/pay.png"></c:if></div>
						</div>
						<div class="featured__item__text">
							<h6><a href="/product/detail/${list.productId}">${list.subject}</a></h6>
							<c:if test="${list.auctionDeadline ne null}">
								<p style="color: #47cd65;">
									<경매 진행 중인 상품>
								</p>
							</c:if>
							<fmt:parseDate var="uploadDate" value="${list.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
							<h5 style="text-align: left"><fmt:formatNumber maxFractionDigits="3" value="${list.price}"/></h5>
							<h5 style="text-align: right"><fmt:formatDate value="${uploadDate}" pattern="MM/dd"/></h5>
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
					<c:set var="startNumber" value="${distancePage * size}"/><%-- 0 * size ~ --%>
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
	<!-- List Paging End -->
</section>