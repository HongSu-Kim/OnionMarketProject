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
							<input type="hidden" id="wishId" value="1"/>
							<input type="hidden" id="producyId" value="1"/>
							<li><a href="#"><i class="fa fa-weixin"></i></a></li>3<li></li>
							<li id="wishBtn" class="true"><a href="#"><i class="fa fa-heart"></i></a></li>1<li></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- view test end -->

			<!-- 찜 리스트 -->
			<c:forEach var="productDTO" items="${list.content}">
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg pointer" data-setbg="/img/product/${productDTO.productImageName}"
								 onclick="location.href='/product/detail?productId=${productDTO.productId}';">
							<ul class="product__item__pic__hover">
								<li><a href="#"><i class="fa fa-heart"></i></a></li>
								<li><a href="#"><i class="fa fa-heart-o"></i></a></li>
							</ul>
						</div>
						<div class="product__item__text">
							<span>${productDTO.memberNickname} / ${productDTO.townName}</span>
							<h6><a href="/product/detail?productId=${productDTO.productId}">${productDTO.subject}</a></h6>
							<h5><fmt:formatNumber type="number" maxFractionDigits="3" value="${productDTO.price}"/>원</h5>
							<ul class="product__item__pic__hover">
								<c:if test="${productDTO.chatroomListSize > 0}">
									<li><a href="/chatroom/..."><i class="fa fa-weixin"></i></a></li>${productDTO.chatroomListSize}<li></li>
								</c:if>
								<c:if test="${productDTO.wishListSize > 0}">
									<li id="wishBtn" class="true"><a href="#"><i class="fa fa-heart"></i></a></li><span id="size">${productDTO.wishListSize}</span><li></li>
								</c:if>
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