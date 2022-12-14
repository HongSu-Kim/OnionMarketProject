<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="spad">
	<div class="container">
		<div class="checkout__form">

			<div class="section-title">
				<h3 style="font-weight: bold">결제</h3>
			</div>
			<hr class="section-hr">

			<form action="/order/payment" method="post" id="form">
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

							<div class="checkout__order__products">상품정보</div>
							<ul>
								<li>
									${productDTO.subject}
										<span>${productDTO.price}</span>
									<input type="hidden" id="productPrice" name="productPrice" value="${productDTO.price}">
								</li>
								<div id="deliveryCost-group" class="d-none">
									<li>
										배송비
										<span>3000</span>
										<input type="hidden" id="deliveryCost" name="deliveryCost" value="3000">
									</li>
								</div>
							</ul>
							<div class="checkout__order__total">
								총 금액
								<span id="totalPrice">${productDTO.price}</span>
							</div>

							<div class="checkout__order__products">포인트</div>
							<ul>
								<li>
									보유 포인트
									<span>${memberDTO.point}</span>
								</li>
								<input type="hidden" id="point" name="point" value="${memberDTO.point}">
							</ul>
							<div class="checkout__order__total">
								사용 포인트
								<span><input type="text" id="usePoint" name="usePoint" value="0" onblur="blurUsePoint()"/></span>
							</div>

							<div class="checkout__order__total">
								결제 금액
								<span id="orderPaymentSpan">${productDTO.price}</span>
								<input type="hidden" id="orderPayment" name="orderPayment" value="${productDTO.price}">
							</div>

							<div class="checkout__input__checkbox">
								<label for="acc-or">
									결제에 동의하시겠습니까?<br/>
									(이니시스 결제시 24시에 자동으로 환불됩니다.)
									<input type="checkbox" id="acc-or">
									<span class="checkmark"></span>
								</label>
							</div>
							<button type="button" class="order-btn btn-50" id="imp-payment">결제하기</button>
							<c:if test="${productDTO.payStatus}">
								<button type="button" class="order-btn btn-50" id="payment">양파페이</button>
							</c:if>
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
			</form>
		</div>
	</div>
</section>
</body>
</html>
