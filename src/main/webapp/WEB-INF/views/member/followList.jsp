<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="spad">
	<div class="container">
		<div class="buyList">

			<!-- Follow List -->
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
									<td colspan="5"class="text-center">팔로잉한 회원이 없습니다.</td>
								</tr>
							</c:if>

							<!-- 주문 정보 -->
										<div class="people-nearby">

											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Sophia Page</a></h5>
														<p>Software Engineer</p>
														<p class="text-muted">500m away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Emma Johnson</a></h5>
														<p>Model at Fashion</p>
														<p class="text-muted">800m away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar5.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Nora Wilson</a></h5>
														<p>Writer at Newspaper</p>
														<p class="text-muted">2.5km away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar4.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Diana Amber</a></h5>
														<p>Student</p>
														<p class="text-muted">700m away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Addison Thomas</a></h5>
														<p>Barber at Fashion</p>
														<p class="text-muted">1.5km away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Jonathon Thompson</a></h5>
														<p>Fashion Designer</p>
														<p class="text-muted">2km away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Olivia Steward</a></h5>
														<p>Creative Director</p>
														<p class="text-muted">2km away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Elena Foster</a></h5>
														<p>Executive Officer</p>
														<p class="text-muted">4km away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Brian Walton</a></h5>
														<p>Designer at Designer</p>
														<p class="text-muted">3km away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
											<div class="nearby-user">
												<div class="row">
													<div class="col-md-2 col-sm-2">
														<img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="user" class="profile-photo-lg">
													</div>
													<div class="col-md-7 col-sm-7">
														<h5><a href="#" class="profile-link">Cris Haris</a></h5>
														<p>General Manager at Manager</p>
														<p class="text-muted">1km away</p>
													</div>
													<div class="col-md-3 col-sm-3">
														<button class="btn btn-primary pull-right">Add Friend</button>
													</div>
												</div>
											</div>
										</div>

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
