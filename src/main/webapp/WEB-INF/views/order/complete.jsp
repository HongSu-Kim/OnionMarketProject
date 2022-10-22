<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="shoping__cart__table">
					<table>
						<thead>
						<tr>
							<th>상품정보</th>
							<th>주문번호</th>
							<th>결제금액</th>
							<th>주문날짜</th>
							<th>주문상태</th>
						</tr>
						</thead>
						<tbody>
							<tr>
								<c:set var="productDTO" value="${orderDTO.productDTO}"/>
								<td class="text-align-left pointer" onclick="location.href='/product/detail/${productDTO.productId}'">
									<img src="/img/product/${productDTO.representativeImage}" class="list-img">
									<span>${productDTO.subject}</span>
								</td>
								<td class="pointer" onclick="location.href='/order/detail/${orderDTO.orderId}'">
									${orderDTO.imp_uid}
								</td>
								<td>
									<fmt:formatNumber type="number" maxFractionDigits="3" value="${orderDTO.orderPayment}"/>원
								</td>
								<td>
									<fmt:parseDate var="orderDate" value="${orderDTO.orderDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
									<fmt:formatDate value="${orderDate}" pattern="yyyy/MM/dd"/>
								</td>
								<td>
									<p>${orderDTO.orderState.kor}</p>
									<p>
										<c:if test="${orderDTO.orderState eq 'ORDER'}">
											<c:if test="${!empty orderDTO.deliveryDTO}">
												<a href="/order/detail/${orderDTO.orderId}/update" class="primary-btn">배송지변경</a>
											</c:if>
											<a onclick="confirm('정말 취소하시겠습니까?') ? location.href='/order/cancel/${orderDTO.orderId}' : false"
												 class="primary-btn cart-btn">주문취소</a>
										</c:if>
										<c:if test="${orderDTO.orderState eq 'CANCEL'}">
											<a href="/order/detail/${orderDTO.orderId}" class="primary-btn">주문확인</a>
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
	</div>
</section>