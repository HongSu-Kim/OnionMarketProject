<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />

	<title>sellList</title>

</head>
<body>
<section class="spad">
	<div class="container">
		<div class="sellList">

			<!-- Sell List -->
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__table">
						<table>
							<thead>
								<tr>
									<th>상품정보</th>
									<th>판매금액</th>
									<th>등록/수정일</th>
									<th>상태변경</th>
								</tr>
							</thead>
							<tbody>

								<!-- view test -->
									<tr>
										<td onclick="location.href='/product/Detail?productId=1'" style="cursor: pointer">
											<img src="/img/product/edd75ee44b39477ef71df02dbc46e873c802479d.png" height="100">
											<span>제목1</span>
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="10000"/>원</td>
										<td>2022/10/17</td>
										<td>
											<p>거래중</p>
											<p>
												<a onclick="confirm('상태를 예약중으로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=1&productProgress=RESERVED' : false"
													 class="primary-btn">예약중</a>
												<a onclick="confirm('상태를 판매완료로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=1&productProgress=SOLDOUT' : false"
													 class="primary-btn">판매완료</a>
											</p>
										</td>
									</tr>
									<tr>
										<td onclick="location.href='/product/Detail?productId=2'" style="cursor: pointer">
											<img src="/img/product/edd75ee44b39477ef71df02dbc46e873c802479d.png" height="100">
											<span>제목2</span>
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="20000"/>원</td>
										<td>2022/10/16</td>
										<td>
											<p>예약중</p>
											<p>
												<a onclick="confirm('상태를 거래중으로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=2&productProgress=TRADINGS' : false"
													 class="primary-btn">거래중</a>
												<a onclick="confirm('상태를 판매완료로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=2&productProgress=SOLDOUT' : false"
													 class="primary-btn">판매완료</a>
											</p>
										</td>
									</tr>
									<tr>
										<td onclick="location.href='/product/Detail?productId=3'" style="cursor: pointer">
											<img src="/img/product/edd75ee44b39477ef71df02dbc46e873c802479d.png" height="100">
											<span>제목3</span>
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="30000"/>원</td>
										<td>2022/10/15</td>
										<td>
											<p>판매완료</p>
											<p>
												<a href="/review/created/1" class="primary-btn">판매후기등록</a>
											</p>
										</td>
									</tr>
								<!-- view test end -->

								<!-- 주문 없음 -->
								<c:if test="${empty page.content}">
									<tr>
										<td colspan="5" style="text-align: center">판매한 상품이 존재하지 않습니다.</td>
									</tr>
								</c:if>

								<!-- 주문 정보 -->
								<c:forEach var="productDTO" items="${page.content}">
									<tr>
										<td onclick="location.href='/product/Detail?productId=${productDTO.productId}'" style="cursor: pointer">
											<img src="/img/product/${productDTO.productImageName}" height="100">
											<span>${productDTO.subject}</span>
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${productDTO.price}"/>원</td>
										<td>${productDTO.date}</td>
										<td>
											<p>${productDTO.productProgress.kor}</p>
											<p>
												<c:if test="${productDTO.productProgress eq 'TRADINGS'}">
													<a onclick="confirm('상태를 예약중으로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=${productDTO.productId}&productProgress=RESERVED' : false"
														 class="primary-btn">예약중</a>
													<a onclick="confirm('상태를 판매완료로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=${productDTO.productId}&productProgress=SOLDOUT' : false"
														 class="primary-btn">판매완료</a>
												</c:if>
												<c:if test="${productDTO.productProgress eq 'RESERVED'}">
													<a onclick="confirm('상태를 거래중으로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=${productDTO.productId}&productProgress=TRADINGS' : false"
														 class="primary-btn">거래중</a>
													<a onclick="confirm('상태를 판매완료로 변경하시겠습니까?') ? location.href='/product/progressUpdate?productId=${productDTO.productId}&productProgress=SOLDOUT' : false"
														 class="primary-btn">판매완료</a>
												</c:if>
												<c:if test="${productDTO.productProgress eq 'SOLDOUT'}">
													<a href="/review/created/${orderDTO.orderId}" class="primary-btn">판매후기등록</a>
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
			<!-- Sell List End -->

		</div>
	</div>
</section>
</body>
</html>
