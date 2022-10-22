<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />

	<title>buyList</title>

</head>
<body>
<section class="spad">
	<div class="container">
		<div class="buyList">

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
									<tr>
										<td class="text-align-left pointer" onclick="location.href='/product/detail/${orderDTO.productDTO.productId}'">
											<img src="/img/product/${orderDTO.productDTO.representativeImage}" class="list-img">
											<span>${orderDTO.productDTO.subject}</span>
										</td>
										<td class="pointer" onclick="location.href='/order/detail/${orderDTO.orderId}'">
											${orderDTO.imp_uid}
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${orderDTO.orderPayment}"/></td>
										<td>
											<fmt:parseDate var="orderDate" value="${orderDTO.orderDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
											<fmt:formatDate value="${orderDate}" pattern="yyyy/MM/dd"/>
										</td>
										<td>
											<p>${orderDTO.orderState.kor}</p>
											<p>
												<c:if test="${orderDTO.orderState eq 'ORDER'}">
													<a href="/order/detail/${orderDTO.orderId}/update" class="primary-btn">배송지변경</a>
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

					<%--<div class="buyList__nav">
						<nav th:if="${!lists.isEmpty()}" arial-label="Page navigation">
							<ul class="pagination justify-content-center pagination-sm"
									th:with="startNumber = ${lists.number / 10} * 10 + 1, endNumber = (${lists.totalPages} > ${startNumber + 9}) ? ${startNumber + 9} : ${lists.totalPages}">
								<!-- << -->
								<li><a class="page-link" th:href="@{|?boardCategory=${boardCategory}&page=1|}">&laquo;</a></li>
								<!-- < -->
								<li class="page-item" th:style="${lists.first} ? 'display:none'">
									<!-- <a class="page-link" th:href="@{/question/list(page = ${paging.number})}">&lsaquo;</a> -->
									<a class="page-link" th:href="@{|?boardCategory=${boardCategory}&page=${lists.number}|}">&lsaquo;</a>
								</li>
								<li class="page-item" th:each="page : ${#numbers.sequence(startNumber, endNumber)}" th:classappend="(${page} == ${lists.number + 1}) ? 'active' : null">
									<a class="page-link" th:href="@{|?boardCategory=${boardCategory}&page=${page}|}" th:text="${page}"></a>
								</li>
								<!-- > -->
								<li class="page-item" th:style="${lists.last} ? 'display:none'">
									<a class="page-link" th:href="@{|?boardCategory=${boardCategory}&page=${lists.number + 2}|}">&rsaquo;</a>
								</li>
								<!-- >> -->
								<li><a class="page-link" th:href="@{|?boardCategory=${boardCategory}&page=${lists.totalPages}|}">&raquo;</a></li>
							</ul>
						</nav>
					</div>--%>

					<!-- view test -->
					<c:if test="${empty page.content}">
						<div class="product__pagination text-center">
							<a href="#">1</a>
							<a href="#">2</a>
							<a href="#">3</a>
							<a href="#">></a>
						</div>
					</c:if>
					<!-- view test end -->

					<!-- 페이징 -->
					<c:if test="${!empty page.content}">
						<div class="product__pagination text-center">
							<a href="#">1</a>
							<a href="#">2</a>
							<a href="#">3</a>
							<a href="#">></a>
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
