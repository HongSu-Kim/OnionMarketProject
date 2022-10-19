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

</head>
<body>
<section class="spad">
	<div class="container">
		<div class="checkout__form">
			<form:form action="" method="post" modelAttribute="orderAddDTO">
<%--				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
				<input type="hidden" id="orderNum" name="orderNum" value="${orderAddDTO.orderNum}"/>
				<input type="hidden" id="memberId" name="memberId" value="${memberDTO.id}"/>
				<input type="hidden" id="productId" name="productId" value="${productDTO.productId}"/>
				<input type="hidden" id="subject" name="subject" value="${productDTO.subject}">

				<!-- product -->
				<div class="row mb-6">
					<div class="col-lg-12">
						<h4>상품 정보</h4>
						<div class="owl-item cloned" style="width: 360px;">
							<div class="latest-prdouct__slider__item">
								<a href="/product/detail?productId=${productDTO.productId}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="/img/product/${productDTO.representativeImage}" alt="${productDTO.subject}">
									</div>
									<div class="latest-product__item__text">
										<h6>${productDTO.subject}</h6>
										<span>${productDTO.price}원</span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>

				<h4>배송 정보</h4>
				<div class="row">

					<!-- 배송 정보 -->
					<div class="col-8 col-md-6">
						<%--<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>Fist Name<span>*</span></p>
									<input type="text">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>Last Name<span>*</span></p>
									<input type="text">
								</div>
							</div>
						</div>--%>
						<div class="checkout__input">
							<p>주문자명<span class="comment">(변경불가)</span></p>
							<input type="text" id="name" name="name" value="${memberDTO.name}" readonly/>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>연락처<span class="comment">(변경불가)</span></p>
									<input type="text" id="tel" name="tel" value="${memberDTO.tel}" readonly/>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>이메일<span class="comment">(변경불가)</span></p>
									<input type="text" id="email" name="email" value="${memberDTO.email}" readonly/>
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>
								배송 주소<span>*</span>
								<a type="button" class="primary-btn cart-btn" style="padding: 8px 15px 7px" onclick="sample6_execDaumPostcode()">우편번호 찾기</a>
							</p>
							<input type="text" class="checkout__input__add" id="postcode" name="postcode" value="${memberDTO.postcode}" placeholder="우편번호">
							<form:errors path="postcode"/>
							<input type="text" class="checkout__input__add" id="address" name="address" value="${memberDTO.address}" placeholder="주소">
							<form:errors path="address"/>
							<input type="text" class="checkout__input__add" id="detailAddress" name="detailAddress" value="${memberDTO.detailAddress}" placeholder="상세주소">
							<input type="text" id="extraAddress" name="extraAddress" value="${memberDTO.extraAddress}" placeholder="참고항목">
							<form:errors path="extraAddress"/>
						</div>
						<div class="checkout__input">
							<p>요청사항</p>
							<input type="text" id="request" name="request" value="${orderAddDTO.request}" placeholder="요청사항"/>
						</div>
					</div>

					<!-- 결제 정보 -->
					<div class="col-4 col-md-6">
						<div class="checkout__order">
							<h4>결제 정보</h4>
							<input type="hidden" id="productPrice" name="productPrice" value="${productDTO.price}">
							<input type="hidden" id="deliveryCost" name="deliveryCost" value="${orderAddDTO.deliveryCost}">
							<input type="hidden" id="orderPayment" name="orderPayment" value="${productDTO.price + orderAddDTO.deliveryCost}">
							<div class="checkout__order__products">Products <span>Total</span></div>
							<ul>
								<li>${productDTO.subject} <span>${productDTO.price}</span></li>
								<li>배송비 <span>${orderAddDTO.deliveryCost}</span></li>
							</ul>
							<div class="checkout__order__total">결제금액 <span>${productDTO.price + orderAddDTO.deliveryCost}</span></div>
							<%--<div class="checkout__input__checkbox">
								<label for="acc-or">
									Create an account?
									<input type="checkbox" id="acc-or">
									<span class="checkmark"></span>
								</label>
							</div>--%>
							<button type="button" class="site-btn" id="payment">결제하기</button>
						</div>
					</div>

				</div>
			</form:form>
		</div>
	</div>
</section>
</body>
</html>
