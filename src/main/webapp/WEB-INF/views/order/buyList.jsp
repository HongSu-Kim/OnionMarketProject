<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<section class="spad">
	<div class="container">
		<div class="buyList">

			<div class="section-title">
				<h3 style="font-weight: bold">구매 목록</h3>
			</div>
			<hr class="section-hr">

			<!-- Buy List -->
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

								<!-- 주문 없음 -->
								<c:if test="${empty page.content}">
									<tr>
										<td colspan="5"class="text-center">주문한 상품이 존재하지 않습니다.</td>
									</tr>
								</c:if>

								<!-- 주문 정보 -->
								<c:forEach var="orderDTO" items="${page.content}">
									<c:set var="productDTO" value="${orderDTO.productDTO}"/>
									<tr>
										<td class="text-align-left pointer" onclick="location.href='/product/detail/${productDTO.productId}'">
											<img src="/img/product/${productDTO.representativeImage}" class="list-img">
											<span>${productDTO.subject}</span>
										</td>
										<td class="pointer" onclick="location.href='/order/detail/${orderDTO.orderId}'">
											${orderDTO.orderNum}
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${orderDTO.orderPayment}"/>원</td>
										<td>
											<fmt:parseDate var="orderDate" value="${orderDTO.orderDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
											<fmt:formatDate value="${orderDate}" pattern="yyyy/MM/dd"/>
										</td>
										<td>
											<p onclick="location.href='/order/detail/${orderDTO.orderId}'" class="pointer">${orderDTO.orderState.kor}</p>
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
													<c:if test="${empty orderDTO.reviewId}">
														<a href="/review/created/${orderDTO.orderId}" class="primary-btn">판매후기등록</a>
													</c:if>
													<c:if test="${!empty orderDTO.reviewId}">
														<a href="/review/update/<sec:authentication property="principal.sessionDTO.id"/>/${orderDTO.reviewId}" class="primary-btn">판매후기수정</a>
													</c:if>
												</c:if>
											</p>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- Buy List End -->

			<!-- List Paging -->
			<div class="row">
				<div class="col-lg-12">

					<!-- 페이징 -->
					<c:if test="${!empty page.content && page.totalPages != 1}">
						<input type="hidden" id="pageNumber" value="${page.number + 1}"/>
						<div class="product__pagination text-center">
							<c:set var="size" value="${page.pageable.pageSize}"/><%-- 10 --%>
							<fmt:parseNumber var="pageNumber" integerOnly="true" value="${page.number / size}"/><%-- 현재페이지 : 0 ~ --%>
							<c:set var="startNumber" value="${pageNumber * size}"/><%-- 0 * size ~ --%>
							<c:set var="endNumber" value="${page.totalPages > (pageNumber + 1) * size ? (pageNumber + 1) * size - 1 : page.totalPages - 1}"/>

							<c:if test="${page.totalPages > size && page.number + 1 > size}">
								<a href="?page=0"><<</a>
								<a href="?page=${startNumber - 1}"><</a>
							</c:if>
							<c:forEach var="currentNumber" begin="${startNumber}" end="${endNumber}">
								<a href="?page=${currentNumber}">${currentNumber + 1}</a>
							</c:forEach>
							<c:if test="${page.totalPages - 1 > endNumber}">
								<a href="?page=${endNumber + 1}">></a>
								<a href="?page=${page.totalPages - 1}">>></a>
							</c:if>
						</div>
					</c:if>

				</div>
			</div>
			<!-- List Paging End -->

		</div>
	</div>
</section>
</body>
</html>
