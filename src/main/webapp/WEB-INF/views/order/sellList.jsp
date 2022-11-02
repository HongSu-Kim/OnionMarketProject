<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="spad">
	<div class="container">
		<div class="sellList">

			<div class="section-title">
				<h3 style="font-weight: bold">판매 목록</h3>
				<p style="width: 100px; float: right">
					<input type="hidden" id="progress" value="${productProgress}"/>
					<select name="productProgress" id="productProgress">
						<option value="">전체</option>
						<option value="SALESON">판매중</option>
						<option value="RESERVED">예약중</option>
						<option value="TRADINGS">거래중</option>
						<option value="SOLDOUT">판매완료</option>
					</select>
				</p>
			</div>
			<hr class="section-hr">

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
										<td class="text-align-left pointer" onclick="location.href='/product/detail/${productDTO.productId}';">
											<img src="/img/product/${productDTO.representativeImage}" class="list-img">
											<span>${productDTO.subject}</span>
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${productDTO.price}"/>원</td>
										<td>
											<fmt:parseDate var="date" value="${productDTO.date}" pattern="yyyy-MM-dd'T'HH:mm"/>
											<fmt:formatDate value="${date}" pattern="yyyy/MM/dd"/>
										</td>
										<td>
											<p>${productDTO.productProgress.kor}</p>
											<p>
												<input type="hidden" id="productId" neme="productId" value="${productDTO.productId}"/>
												<input type="hidden" id="orderId" neme="orderId" value="${productDTO.orderId}"/>
												<c:if test="${productDTO.productProgress eq 'SALESON'}">
													<a class="primary-btn progressUpdate">예약중</a>
													<a class="primary-btn progressUpdate">판매완료</a>
												</c:if>
												<c:if test="${productDTO.productProgress eq 'RESERVED'}">
													<a class="primary-btn progressUpdate">판매중</a>
													<a class="primary-btn progressUpdate">판매완료</a>
												</c:if>
												<c:if test="${productDTO.productProgress eq 'TRADINGS'}">
													<a class="primary-btn progressUpdate">판매완료</a>
												</c:if>
												<c:if test="${productDTO.productProgress eq 'SOLDOUT'}">
													<c:if test="${empty productDTO.reviewId}">
														<a href="/review/created/${productDTO.orderId}" class="primary-btn">판매후기등록</a>
													</c:if>
													<c:if test="${!empty productDTO.reviewId}">
														<a href="/review/update/${sessionDTO.id}/${productDTO.reviewId}" class="primary-btn">판매후기수정</a>
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
			<!-- Sell List End -->

			<!-- List Paging -->
			<div class="row">
				<div class="col-lg-12">

					<!-- 페이징 -->
					<c:if test="${!empty page.content && page.totalPages != 1}">
						<input type="hidden" id="pageNumber" value="${page.number}"/>
						<div class="product__pagination text-center">
							<c:set var="size" value="${page.pageable.pageSize}"/><%-- 10 --%>
							<fmt:parseNumber var="pages" integerOnly="true" value="${page.number / size}"/><%-- 현재페이지 : 0 ~ --%>
							<c:set var="startNumber" value="${pages * size}"/><%-- 0 * size ~ --%>
							<c:set var="endNumber" value="${page.totalPages > (pages + 1) * size ? (pages + 1) * size - 1 : page.totalPages - 1}"/>

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

<div class="modal-shadow">
	<div id="member-modal">

		<div class="member-box" id="memberList">
			<input type="hidden" id="memberId" value="${sessionDTO.id}"/>
			<input type="hidden" id="memberNickname" value="${sessionDTO.nickname}"/>

			<div class="chat-box-header">
				구매자를 선택해주세요
				<span class="chat-box-toggle right member-close-btn"><i class="material-icons">close</i></span>
			</div>
			<div class="chat-box-body list">
				<div class="chat-box-overlay"></div>
				<div class="chat-logs" id="memberListArea">
				</div>
			</div>
		</div>

	</div>
</div>

</body>
</html>
