<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="spad">
	<div class="container">
		<div class="blockList">

			<!-- Block List -->
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__table">
						<table>
							<thead>
							<tr>
								<th>프로필 사진</th>
								<th>닉네임</th>
								<th>평점</th>
								<th>차단 상태</th>
							</tr>
							</thead>
							<tbody>

							<!-- 차단한 회원 없음 -->
							<c:if test="${empty page.content}">
								<tr>
									<td colspan="5"class="text-center">차단한 회원이 없습니다.</td>
								</tr>
							</c:if>

							<!-- 차단 정보 -->
							<c:forEach var="blockDTO" items="${page.content}">
								<tr>
									<td class="text-align-left pointer" onclick="window.open('/img/member/${blockDTO.targetDTO.memberImageName}', 'Profile', 'width=600, height=800, location=no, status=no, scrollbars=yes');">
										<img src="/img/member/${blockDTO.targetDTO.memberImageName}" class="list-img profile">
									</td>
									<td><a href="/member/profile/${blockDTO.targetDTO.id}">${blockDTO.targetDTO.nickname}</a></td>
									<td>${blockDTO.targetDTO.userGrade}</td>
									<td>
										<c:if test="${!blockDTO.targetDTO.blockCheck}">
											<button type="button" onclick="location.href='/block/removeBlock/${blockDTO.targetDTO.id}'">차단해제</button>
										</c:if>
										<c:if test="${blockDTO.targetDTO.blockCheck}">
											<button type="button" onclick="location.href='/block/addBlock/${blockDTO.targetDTO.id}'">차단</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- Block List End -->

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
