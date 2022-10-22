<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />

	<title>buyList</title>

	<style>
      label.error{display:block;color:red;}
	</style>
</head>
<body>
<section class="spad">
	<div class="container">
		<div class="checkout__form">
			<form:form action="/order/payment" method="post" id="form" modelAttribute="orderAddDTO">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<input type="hidden" id="orderNum" name="orderNum" value="${orderAddDTO.orderNum}"/>
				<input type="hidden" id="memberId" name="memberId" value="${memberDTO.id}"/>
				<input type="hidden" id="productId" name="productId" value="${productDTO.productId}"/>
				<input type="hidden" id="subject" name="subject" value="${productDTO.subject}">

				<div class="row">
					<div class="col-lg-6 mx-auto">

						<!-- 상품 정보 -->
						<div class="owl-item mb-6">
							<h4>상품 정보</h4>
							<div class="latest-prdouct__slider__item">
								<a class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/img/product/${productDTO.representativeImage}" alt="${productDTO.subject}" class="list-img">
									</div>
									<div class="latest-product__item__text">
										<h6>${productDTO.subject}</h6>
										<span>${productDTO.price}원</span>
									</div>
								</a>
							</div>
						</div>

						<!-- 결제 정보 -->
						<div class="checkout__order">
							<h4>결제 정보</h4>
							<input type="hidden" id="name" name="name" value="${memberDTO.name}">
							<input type="hidden" id="tel" name="tel" value="${memberDTO.tel}">
							<input type="hidden" id="email" name="email" value="${memberDTO.email}">
							<div class="checkout__order__products">Products <span>Total</span></div>
							<ul>
								<li>${productDTO.subject} <span>${productDTO.price}</span></li>
								<input type="hidden" id="productPrice" name="productPrice" value="${productDTO.price}">
								<div id="deliveryCost-group" class="d-none">
									<li>배송비 <span>3000</span></li>
									<input type="hidden" id="deliveryCost" name="deliveryCost" value="3000">
								</div>
							</ul>
							<div class="checkout__order__total">결제금액 <span id="totalPrice">${productDTO.price}</span></div>
							<input type="hidden" id="orderPayment" name="orderPayment" value="${productDTO.price}">
								<div class="checkout__input__checkbox">
									<label for="acc-or">
										결제에 동의하시겠습니까?
										<input type="checkbox" id="acc-or">
										<span class="checkmark"></span>
									</label>
								</div>
							<button type="button" class="order-btn btn-50" id="payment">양파페이</button>
							<button type="button" class="order-btn btn-50 float-right" id="imp-payment">결제하기</button>
						</div>

					</div>
					<div class="col-lg-6">

						<input type="hidden" id="delivery" name="delivery" value="false"/>
						<!-- 배송 정보 -->
						<div id="deliveryInfo" class="d-none">
							<h4>배송 정보</h4>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>받는사람<span>*</span></p>
										<input type="text" id="recipient" name="recipient" value="${memberDTO.name}"/>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>연락처<span>*</span></p>
										<input type="text" id="deliveryTel" name="deliveryTel" value="${memberDTO.tel}"/>
									</div>
								</div>
							</div>
							<div class="checkout__input">
								<p>
									배송 주소<span>*</span>
									<span class="right">
										<a type="button" class="primary-btn cart-btn" style="padding: 8px 15px 7px" onclick="sample6_execDaumPostcode()">우편번호 찾기</a>
										<a type="button" class="primary-btn cart-btn" id="memberAddr" style="padding: 8px 15px 7px">등록된 주소</a>
									</span>
								</p>
								<input type="text" class="checkout__input__add" id="postcode" name="postcode" placeholder="우편번호">
								<input type="text" class="checkout__input__add" id="address" name="address" placeholder="주소">
								<input type="text" class="checkout__input__add" id="detailAddress" name="detailAddress" placeholder="상세주소">
								<input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목">
								<input type="hidden" id="hiddenPostcode" value="${memberDTO.postcode}">
								<input type="hidden" id="hiddenAddress" value="${memberDTO.address}">
								<input type="hidden" id="hiddenDetailAddress" value="${memberDTO.detailAddress}">
								<input type="hidden" id="hiddenExtraAddress" value="${memberDTO.extraAddress}">
							</div>
							<div class="checkout__input">
								<p>요청사항</p>
								<input type="text" id="request" name="request" placeholder="요청사항"/>
							</div>
							<button type="button" class="order-btn cart-btn" id="delivery-false">직거래</button>
						</div>
						<button type="button" class="order-btn mt-5" id="delivery-true">배송 받기</button>

					</div>
				</div>
			</form:form>
		</div>
	</div>
</section>
</body>
</html>
