<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<section class="spad">
	<div class="container">
		<div class="wishList">

			<div class="row">
				<!-- 주문없음 -->
				<c:if test="${empty page.content}">
					<div class="col-lg-12">
						<div class="contact__form__title">
							<h3>찜한 상품이 없습니다.</h3>
							<button type="button" class="site-btn mt-5" onclick="location.href='/product/list'">상품 보러가기</button>
						</div>
					</div>
				</c:if>

				<!-- 찜 리스트 -->
				<c:forEach var="wishListDTO" items="${page.content}">
					<div class="col-lg-4 col-md-6 col-sm-6">
						<div class="product__item">
							<div class="product__item__pic set-bg pointer" data-setbg="/img/product/${wishListDTO.representativeImage}"
									 onclick="location.href='/product/detail/${wishListDTO.productId}';">
							</div>
							<div class="product__item__text">
								<h6><a href="/product/detail/${wishListDTO.productId}">${wishListDTO.subject}</a></h6>
								<h5><fmt:formatNumber type="number" maxFractionDigits="3" value="${wishListDTO.price}"/>원</h5>
								<h6 class="grey">${wishListDTO.memberNickname} / ${wishListDTO.townName}</h6>
								<ul class="product__wish__item">
									<input type="hidden" id="productId" value="${wishListDTO.productId}"/>
									<c:if test="${wishListDTO.chatroomListSize > 0}">
										<li><a href="/chatroom/..."><i class="fa fa-weixin"></i></a></li>
										<span id="chatroomListSize">${wishListDTO.chatroomListSize}</span><li></li>
									</c:if>
									<li class="wishBtn true"><a href="#"><i class="fa fa-heart"></i></a></li>
									<span id="wishListSize">${wishListDTO.wishListSize}</span><li></li>
								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<!-- List Paging -->
			<div class="row">
				<div class="col-lg-12">

					<!-- 페이징 -->
					<c:if test="${!empty page.content && page.totalPages != 1}">
						<div class="product__pagination text-center">
							<c:set var="size" value="${page.pageable.pageSize}"/>
							<fmt:parseNumber var="pages" integerOnly="true" value="${page.number / size}"/>
							<c:set var="startNumber" value="${pages * size + 1}"/>
							<c:set var="endNumber" value="${page.totalPages > (pages + 1) * size ? (pages + 1) * size : page.totalPages}"/>
							<c:if test="${page.totalPages > size && page.number + 1 > size}">
								<a href="?page=1"><<</a>
								<a href="?page=${startNumber - 1}"><</a>
							</c:if>
							<c:forEach var="currentNumber" begin="${startNumber}" end="${endNumber}">
								<a href="?page=${currentNumber}">${currentNumber}</a>
							</c:forEach>
							<c:if test="${page.totalPages > endNumber}">
								<a href="?page=${endNumber + 1}">></a>
								<a href="?page=${page.totalPages}">>></a>
							</c:if>
						</div>
					</c:if>

				</div>
			</div>
			<!-- List Paging End -->

		</div>
	</div>
</section>

<script>
</script>