<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />

	<title>detail</title>

</head>
<body>
<section class="spad">
	<div class="container">
		<div class="checkout__form">
			<!-- product -->
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__table">
						<h4>상품 정보</h4>
						<table>
							<thead>
								<tr>
									<th>상품정보</th>
									<th>주문번호</th>
									<th>결제금액</th>
									<th>주문날짜</th>
									<th>상태변경</th>
								</tr>
							</thead>
							<tbody>

								<!-- 상품 정보 -->
								<tr>
									<c:set var="deliveryDTO" value="${orderDTO.deliveryDTO}"/>
									<c:set var="productDTO" value="${orderDTO.productDTO}"/>
									<td class="text-align-left pointer" onclick="location.href='/product/detail?productId=${productDTO.productId}'">
										<img src="/img/product/${productDTO.representativeImage}" height="100">
										<span>${productDTO.subject}</span>
									</td>
									<td>${orderDTO.imp_uid}</td>
									<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${orderDTO.orderPayment}"/>원</td>
									<td>
										<fmt:parseDate var="orderDate" value="${orderDTO.orderDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
										<fmt:formatDate value="${orderDate}" pattern="yyyy/MM/dd"/>
									</td>
									<td>
										<p>${orderDTO.orderState.kor}</p>
										<p>
											<c:if test="${orderDTO.orderState eq 'ORDER'}">
												<c:if test="${!empty deliveryDTO}">
													<a onclick="update()" class="primary-btn">배송지변경</a>
												</c:if>
												<a onclick="confirm('정말 취소하시겠습니까?') ? location.href='/order/cancel?orderId=${orderDTO.orderId}' : false"
													 class="primary-btn cart-btn">주문취소</a>
											</c:if>
											<c:if test="${orderDTO.orderState eq 'CANCEL'}">
												<a href="/order/detail?orderId=${orderDTO.orderId}" class="primary-btn">주문확인</a>
											</c:if>
											<c:if test="${orderDTO.orderState eq 'COMPLETE'}">
												<a href="/review/created/${orderDTO.orderId}" class="primary-btn">구매후기등록</a>
											</c:if>
										</p>
									</td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>


			<div class="row">

				<!-- 결제 정보 -->
				<div class="col-4 col-md-6">
					<div class="checkout__order">
						<h4>결제 정보</h4>
						<div class="checkout__order__products">상품 <span>금액</span></div>
						<ul>
							<li>${productDTO.subject} <span>${productDTO.price}원</span></li>
							<c:if test="${!empty deliveryDTO}">
								<li>배송비 <span>${deliveryDTO.deliveryCost}원</span></li>
							</c:if>
						</ul>
						<div class="checkout__order__total">결제금액 <span>${productDTO.price + deliveryDTO.deliveryCost}원</span></div>
					</div>
				</div>

				<!-- 배송 정보 -->
				<div class="col-8 col-md-6">
					<c:if test="${!empty deliveryDTO}">
						<div class="checkout__input">
							<h4>배송 정보</h4>
<%--							<c:set var="memberDTO" value="${orderDTO.memberDTO}"/>--%>
							<p>주문자명</p>
<%--							<input type="text" id="name" name="name" value="${memberDTO.name}" readonly/>--%>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>연락처</p>
<%--									<input type="text" id="tel" name="tel" value="${memberDTO.tel}" readonly/>--%>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>이메일</p>
<%--									<input type="text" id="email" name="email" value="${memberDTO.email}" readonly/>--%>
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>
								배송 주소
								<c:if test="${!empty deliveryDTO}">
									<a type="button" class="primary-btn" style="padding: 8px 15px 7px; float: right;"
										 id="updateBtn" onclick="update()">배송지 변경</a>
									<a type="button" class="primary-btn cart-btn" style="padding: 8px 15px 7px; display: none;"
										 id="postcodeBtn" onclick="sample6_execDaumPostcode()">우편번호 찾기</a>
									<a type="button" class="primary-btn" style="padding: 8px 15px 7px; float: right; display: none;"
										 id="submitBtn" onclick="save()">완료</a>
									<input type="hidden" id="mode" value="${mode}">
								</c:if>
							</p>
							<input type="hidden" id="orderId" value="${deliveryDTO.orderId}"/>
							<input type="text" class="checkout__input__add" id="postcode" name="postcode" value="${deliveryDTO.postcode}" readonly>
							<input type="text" class="checkout__input__add" id="address" name="address" value="${deliveryDTO.address}" readonly>
							<input type="text" class="checkout__input__add" id="detailAddress" name="detailAddress" value="${deliveryDTO.detailAddress}" placeholder="상세주소" readonly>
							<input type="text" id="extraAddress" name="extraAddress" value="${deliveryDTO.extraAddress}" placeholder="참고항목" readonly>
						</div>
						<div class="checkout__input">
							<p>요청사항</p>
							<input type="text" id="request" name="request" value="${deliveryDTO.request}" placeholder="요청사항" readonly/>
						</div>
					</c:if>
				</div>

			</div>
		</div>
	</div>
</section>
</body>
</html>
