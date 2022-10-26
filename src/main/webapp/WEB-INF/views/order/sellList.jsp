<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

								<!-- 주문 없음 -->
								<c:if test="${empty page.content}">
									<tr>
										<td colspan="5" class="text-align-center">판매한 상품이 존재하지 않습니다.</td>
									</tr>
								</c:if>

								<!-- 주문 정보 -->
								<c:forEach var="productDTO" items="${page.content}">
									<tr>
										<td class="text-align-left pointer" onclick="location.href='/product/Detail/${productDTO.productId}';">
											<img src="/img/product/${productDTO.representativeImage}" class="list-img">
											<span>${productDTO.subject}</span>
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${productDTO.price}"/>원</td>
										<td>
											<fmt:parseDate var="date" value="${productDTO.date}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
											<fmt:formatDate value="${date}" pattern="yyyy/MM/dd"/>
										</td>
										<td>
											<p>${productDTO.productProgress.kor}</p>
											<p>
												<c:if test="${productDTO.productProgress eq 'TRADINGS'}">
													<a onclick="confirm('상태를 예약중으로 변경하시겠습니까?') ? location.href='/product/progressUpdate/${productDTO.productId}/RESERVED' : false"
														 class="primary-btn">예약중</a>
													<a onclick="confirm('상태를 판매완료로 변경하시겠습니까?') ? location.href='/product/progressUpdate/${productDTO.productId}/SOLDOUT' : false"
														 class="primary-btn">판매완료</a>
												</c:if>
												<c:if test="${productDTO.productProgress eq 'RESERVED'}">
													<a onclick="confirm('상태를 거래중으로 변경하시겠습니까?') ? location.href='/product/progressUpdate/${productDTO.productId}/TRADINGS' : false"
														 class="primary-btn">거래중</a>
													<a onclick="confirm('상태를 판매완료로 변경하시겠습니까?') ? location.href='/product/progressUpdate/${productDTO.productId}/SOLDOUT' : false"
														 class="primary-btn">판매완료</a>
												</c:if>
												<c:if test="${productDTO.productProgress eq 'SOLDOUT'}">
													<a href="/review/created/${productDTO.orderId}" class="primary-btn">판매후기등록</a>
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

			<!-- List Paging -->
			<div class="row">
				<div class="col-lg-12">

					<!-- 페이징 -->
					<c:if test="${!empty page.content && page.totalPages != 1}">
						<div class="product__pagination text-center">
							<c:set var="size" value="${page.pageable.pageSize}"/>
							<fmt:parseNumber var="pages" integerOnly="true" value="${page.number / size}"/>
							<c:set var="startNumber" value="${pages * size + 1}"/>
							<c:set var="endNumber" value="${page.totalPages > (pages + 1) * size ? (pages + 1) * size : page.totalPages}"/>
							<c:if test="${page.totalPages > size && page.number + 1 > size}">
								<a href="?page=1"><<</a>
								<a href="?page=${startNumber - 1}"><</a>
							</c:if>
							<c:forEach var="currentNumber" begin="${startNumber}" end="${endNumber}">
								<a href="?page=${currentNumber}">${currentNumber}</a>
							</c:forEach>
							<c:if test="${page.totalPages > endNumber}">
								<a href="?page=${endNumber + 1}">></a>
								<a href="?page=${page.totalPages}">>></a>
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
