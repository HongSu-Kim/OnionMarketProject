<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<c:set var="orderDTO" value="${orderDTO.productDTO}"/>
								<c:set var="productDTO" value="${deliveryDTO.orderDTO.productDTO}"/>
								<td onclick="location.href='/product/detail?productId=${productDTO.productId}'" style="cursor: pointer">
									<img src="/img/product/${productDTO.representativeImage}">
									<span>${productDTO.subject}</span>
								</td>
								<td onclick="location.href='/order/detail?orderId=${orderDTO.orderId}'" style="cursor: pointer">
									${orderDTO.imp_uid}
								</td>
								<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${orderDTO.orderPayment}"/></td>
								<td>${orderDTO.orderDate}</td>
								<td>
									<p>${orderDTO.orderState.kor}</p>
									<p>
										<c:if test="${orderDTO.orderState eq 'ORDER'}">
											<a href="/delivery/update?orderId=${orderDTO.orderId}" class="primary-btn">배송지변경</a>
											<a onclick="confirm('정말 삭제하시겠습니까?') ? location.href='/order/cancel?orderId=${orderDTO.orderId}' : false"
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
	</div>
</section>