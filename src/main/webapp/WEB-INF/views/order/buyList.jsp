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

								<!-- view test -->
									<tr>
										<td class="pointer" onclick="location.href='/product/detail?productId=1';">
											<img src="/img/product/edd75ee44b39477ef71df02dbc46e873c802479d.png" height="100">
											<span>상품명1</span>
										</td>
										<td class="pointer" onclick="location.href='/order/detail?orderId=1';">imp2024654216</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="20000"/>원</td>
										<td>2022/10/16</td>
										<td>
											<p>주문완료</p>
											<p>
												<a href="/order/detail?orderId=1&mode=update" class="primary-btn">배송지변경</a>
												<a onclick="confirm('정말 취소하시겠습니까?') ? location.href='/order/cancel?orderId=1' : false"
													 class="primary-btn cart-btn">주문취소</a>
											</p>
										</td>
									</tr>
									<tr>
										<td class="pointer" onclick="location.href='/product/detail?productId=1';">
											<img src="/img/product/edd75ee44b39477ef71df02dbc46e873c802479d.png" height="100">
											<span>상품명2</span>
										</td>
										<td class="pointer" onclick="location.href='/order/detail?orderId=1';">imp2024684568</td>
										<td>10,000원</td>
										<td>2022/10/15</td>
										<td>
											<p>주문취소</p>
											<p>
												<a href="/order/detail?orderId=1" class="primary-btn">주문확인</a>
											</p>
										</td>
									</tr>
									<tr>
										<td class="pointer" onclick="location.href='/product/detail?productId=1';">
											<img src="/img/product/edd75ee44b39477ef71df02dbc46e873c802479d.png" height="100">
											<span>상품명3</span>
										</td>
										<td class="pointer" onclick="location.href='/order/detail?orderId=1';">imp2020556126</td>
										<td>35,000원</td>
										<td>2022/10/13</td>
										<td>
											<p>배송완료</p>
											<p>
												<a href="/review/created/1" class="primary-btn">구매후기등록</a>
											</p>
										</td>
									</tr>
								<!-- view test end -->

								<!-- 주문 없음 -->
								<c:if test="${empty page.content}">
									<tr>
										<td colspan="5"class="text-center">주문한 상품이 존재하지 않습니다.</td>
									</tr>
								</c:if>

								<!-- 주문 정보 -->
								<c:forEach var="orderDTO" items="${page.content}">
									<tr>
										<td class="pointer" onclick="location.href='/product/detail?productId=${orderDTO.productDTO.productId}'">
											<img src="/img/product/${orderDTO.productDTO.representativeImage}">
											<span>${orderDTO.productDTO.subject}</span>
										</td>
										<td class="pointer" onclick="location.href='/order/detail?orderId=${orderDTO.orderId}'">
												${orderDTO.imp_uid}
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${orderDTO.orderPayment}"/></td>
										<td>${orderDTO.orderDate}</td>
										<td>
											<p>${orderDTO.orderState.kor}</p>
											<p>
												<c:if test="${orderDTO.orderState eq 'ORDER'}">
													<a href="/order/detail?orderId=${orderDTO.orderId}&mode=update" class="primary-btn">배송지변경</a>
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
