<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<c:set var = "cp" value = "<%=request.getContextPath()%>"/>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta charset="utf-8" />--%>
<%--<meta name="_csrf" content="${_csrf.token}">--%>
<%--<meta name="_csrf_header" content="${_csrf.headerName}">--%>

<%--<title>Order</title>--%>

<%--</head>--%>
<%--<body>--%>
<section class="spad">
	<div class="container">

		<div class="row">

			<!-- order nav -->
			<div class="col-lg-3">
				<jsp:include page="orderNav.jsp"/>
			</div>

			<div class="col-lg-9">
				<!-- product -->
				<div class="row">
					<div class="shoping__cart__table">
						<h4>상품 정보</h4>
						<table>
							<thead>
								<tr>
<%--									<th class="shoping__product">Products</th>--%>
<%--									<th>Price</th>--%>
<%--									<th>Quantity</th>--%>
<%--									<th>Total</th>--%>
<%--									<th></th>--%>
								</tr>
							</thead>
							<tbody>
							<!-- 상품 정보 -->
								<tr>
									<td class="shoping__cart__item">
										<img src="/template/img/cart/cart-1.jpg" alt="">
										<h5>Vegetable’s Package</h5>
									</td>
									<td class="shoping__cart__price">
										$55.00
									</td>
									<td class="shoping__cart__quantity">
										<div class="quantity">
											<div class="pro-qty">
												<input type="text" value="1">
											</div>
										</div>
									</td>
									<td class="shoping__cart__total">
										$110.00
									</td>
									<td class="shoping__cart__item__close">
										<span class="icon_close"></span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div class="row">
					<div class="checkout__form">
						<h4>배송 정보</h4>

						<form:form action="" method="post" modelAttribute="orderAddDTO">
<%--							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
							<input type="hidden" id="orderNum" name="orderNum" value="${orderAddDTO.orderNum}"/>
							<input type="hidden" id="memberId" name="memberId" value="${memberDTO.id}"/>
							<input type="hidden" id="productId" name="productId" value="${productDTO.productId}"/>

							<div class="row">
								<div class="col-lg-8 col-md-6">
<%--									<div class="row">--%>
<%--										<div class="col-lg-6">--%>
<%--											<div class="checkout__input">--%>
<%--												<p>Fist Name<span>*</span></p>--%>
<%--												<input type="text">--%>
<%--											</div>--%>
<%--										</div>--%>
<%--										<div class="col-lg-6">--%>
<%--											<div class="checkout__input">--%>
<%--												<p>Last Name<span>*</span></p>--%>
<%--												<input type="text">--%>
<%--											</div>--%>
<%--										</div>--%>
<%--									</div>--%>
									<div class="checkout__input">
										<p>주문자명<span>*</span></p>
										<input type="text" id="name" name="name" value="${memberDTO.name}" placeholder="주문자명"/>
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
									<div class="row">
										<div class="col-lg-6">
											<div class="checkout__input">
												<p>주문자 연락처<span>*</span></p>
												<input type="text" id="tel" name="tel" value="${memberDTO.tel}"/>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="checkout__input">
												<p>주문자 이메일<span>*</span></p>
												<input type="text" id="email" name="email" value="${memberDTO.email}"/>
											</div>
										</div>
									</div>
									<div class="checkout__input">
										<p>요청사항<span>*</span></p>
										<input type="text" id="request" name="request" value="${orderAddDTO.request}" placeholder="요청사항"/>
									</div>
								</div>

								<div class="col-lg-4 col-md-6">
									<div class="checkout__order">
										<h4>Your Order</h4>
										<div class="checkout__order__products">Products <span>Total</span></div>
										<ul>
											<li>Vegetable’s Package <span>$75.99</span></li>
											<li>Fresh Vegetable <span>$151.99</span></li>
											<li>Organic Bananas <span>$53.99</span></li>
										</ul>
										<div class="checkout__order__subtotal">Subtotal <span>$750.99</span></div>
										<div class="checkout__order__total">Total <span>$750.99</span></div>
										<div class="checkout__input__checkbox">
											<label for="acc-or">
												Create an account?
												<input type="checkbox" id="acc-or">
												<span class="checkmark"></span>
											</label>
										</div>
										<p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do eiusmod tempor incididunt
											ut labore et dolore magna aliqua.</p>
										<div class="checkout__input__checkbox">
											<label for="payment">
												Check Payment
												<input type="checkbox" id="payment">
												<span class="checkmark"></span>
											</label>
										</div>
										<div class="checkout__input__checkbox">
											<label for="paypal">
												Paypal
												<input type="checkbox" id="paypal">
												<span class="checkmark"></span>
											</label>
										</div>
										<button type="submit" class="site-btn">PLACE ORDER</button>
									</div>
								</div>

							</div>
						</form:form>
					</div>
				</div>

				<h1>order</h1>
				<form:form action="" method="post" modelAttribute="orderAddDTO">
					<div>
						<p>
							<span>Price</span>
							<input type="text" id="price" name="price" value="${productDTO.price}" placeholder="주문가격"/>
						</p>
					</div>
					<div>
						<p>
							<span>DeliveryCost</span>
							<input type="text" id="deliveryCost" name="deliveryCost" value="${orderAddDTO.deliveryCost}" placeholder="배송비"/>
						</p>
						<p>
							<span>Request</span>
						</p>
					</div>
					<button>입력</button>
					<button type="button" id="payment">결제하기</button>
				</form:form>
			</div>
		</div>
	</div>
</section>
<%--</body>--%>
<%--</html>--%>
