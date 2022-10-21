<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<section class="spad">
	<div class="container">
		<div class="row">

			<!-- view test -->
			<div class="col-lg-4 col-md-6 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg pointer" data-setbg="/img/product/edd75ee44b39477ef71df02dbc46e873c802479d.png"
							 onclick="location.href='/product/detail?productId=1';">
					</div>
					<div class="product__item__text">
						<h6><a href="/product/detail?productId=1">Crab Pool Security</a></h6>
						<h5><fmt:formatNumber type="number" maxFractionDigits="3" value="20000"/>원</h5>
						<h6 class="grey">hiro / 강남구 역삼동</h6>
						<ul class="product__wish__item">
							<input type="hidden" id="productId" value="1"/>
							<li><a href="#"><i class="fa fa-weixin"></i></a></li>
							<span id="chatroomListSize">3</span><li></li>
							<li class="wishBtn true"><a href="#"><i class="fa fa-heart"></i></a></li>
							<span id="wishListSize">1</span><li></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- view test end -->

			<!-- 찜 리스트 -->
			<c:forEach var="wishListDTO" items="${list.content}">
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg pointer" data-setbg="/img/product/${wishListDTO.representativeImage}"
								 onclick="location.href='/product/detail?productId=${wishListDTO.productId}';">
						</div>
						<div class="product__item__text">
							<h6><a href="/product/detail?productId=${wishListDTO.productId}">${wishListDTO.subject}</a></h6>
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
	</div>
</section>

<script>
</script>