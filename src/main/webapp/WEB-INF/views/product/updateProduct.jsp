<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Checkout Section Begin -->
<section class="checkout spad">
	<div class="container">
		<div class="product__form">
			<h4>정보 수정<span class="essential__content">*필수항목</span></h4>
			<form action="/product/update/${productFindDTO.productId}" method="post" enctype="multipart/form-data" id="productForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="col-lg-12">
								<div class="checkout__input">
									<p> 제 목 <span>*</span></p>
									<input type="text" id="subject" name="subject" value="${productFindDTO.subject}" placeholder="제목을 입력해주세요.">
								</div>
								<hr/>
							</div>
						</div>
						<div class="checkout__input">
							<p>거래 지역<span>*</span></p>
							<label for="townId">
								<c:forEach var="townList" items="${townList}">
									<p>${townList.townName}<input type="radio" id="townId" name="townId" value="${townList.id}" checked="${townList.id}"></p>
								</c:forEach>
							</label>
							<p><button type="button" class="primary-btn border-0" onclick="location.href='/town/town'">내 동네 설정하러 가기</button></p>
							<hr/>
						</div>
						<div class="checkout__input">
							<p>경매 등록</p>
							<div>
								경매 기간은 12시간입니다.
								<c:if test="${not empty productFindDTO.auctionDeadline}">
									<input type="checkbox" name="auctionStatus" value="true" checked="checked">
									<input type="hidden" name="auctionStatus" value="false">
								</c:if>
								<c:if test="${empty productFindDTO.auctionDeadline}">
									<input type="checkbox" name="auctionStatus" value="true">
									<input type="hidden" name="auctionStatus" value="false">
								</c:if>
								<hr/>
							</div>
						</div>
						<div class="checkout__input">
							<p>카테고리 선택<span>*</span></p>
							<select id="topCategory">
								<option value="">선택하세요</option>
								<c:forEach var="topCategory" items="${topCategoryList}">

									<option <c:if test="${topCategory.categoryId eq productFindDTO.categoryParentId}"> selected="selected" </c:if>
											value="${topCategory.categoryId}">${topCategory.categoryName}
									</option>

								</c:forEach>
								</select>
							<select id="subCategory" name="categoryId">
								<c:forEach var="subCategory" items="${subCategoryList}">

									<option <c:if test="${subCategory.categoryId eq productFindDTO.categoryId}"> selected="selected" </c:if>
											value="${subCategory.categoryId}">${subCategory.categoryName}
									</option>

								</c:forEach>
							</select>

							<br><br><br><br><hr/>
						</div>
						<div class="checkout__input">
							<p>상품 가격<span>*</span></p>
							<div class="col-6">
								<input type="text" id="price" name="price" value="${productFindDTO.price}" placeholder="숫자만 입력해주세요.">
							</div>
							<hr/>
						</div>
						<div class="checkout__input">
							<p>페이 결제</p>
							<div>
								<c:if test="${productFindDTO.payStatus eq true}">
								<input type="checkbox" name="payStatus" value="true" checked="checked">
								<input type="hidden" name="payStatus" value="false">
								</c:if>
								<c:if test="${productFindDTO.payStatus ne true}">
								<input type="checkbox" name="payStatus" value="true">
								<input type="hidden" name="payStatus" value="false">
								</c:if>
								안전결제 환영
								<img src="/template/img/product/pay.png">
							</div>
							<span class="checkmark"></span>
							<hr/>
						</div>
						<div class="checkout__input">
							<p>설 명<span>*</span></p>
							<div class="wrap">
								<textarea id="content" name="content" class="wrap"
										  placeholder="여러 장의 상품 사진과 구입 연도, 브랜드, 사용감, 하자 유무 등 구매자에게 필요한 정보를 꼭 포함해 주세요. (10자 이상)">${productFindDTO.content}</textarea>
								<div id="counter"/>
							</div>
							<hr/>
							<br/>
						</div>

						<div class="row">
							<div class="col-lg-4">
								<div class="checkout__input">
									<div class='addInput'>
										<p>상품 이미지<span>*</span></p>

										<c:forEach var="imageDTO" items="${productFindDTO.productImageDTOList}">
											<img src="/img/product/${imageDTO.productImageName}"/>
											<button type="button" class="btnRemove">삭제</button>
											<input type="hidden" name="productImageIdList" value="${imageDTO.productImageId}">
										</c:forEach>
									</div>
									<button type="button" class="btnAdd">이미지 추가</button>
								</div>
							</div>
							<div class="col-lg-8">
								* 상품 이미지는 640x640에 최적화 되어 있습니다.<br/>
								- 상품 이미지는 PC에서는 1:1, 모바일에서는 1:1.23 비율로 보여집니다.<br/>
								- 이미지는 상품 등록 시 정사각형으로 잘려서 등록됩니다.<br/>
								- 큰 이미지일 경우 이미지가 깨지는 경우가 발생할 수 있습니다.<br/>
								- 최대 지원 사이즈인 640 X 640으로 리사이즈 해서 올려주세요.
								<br>&nbsp;&nbsp;(개당 이미지 최대 5M)<br/><br/>
							</div>
						</div>
						<div>
							<input type="submit" value="상품 수정">
							<input type="hidden" name="productId" value="${productFindDTO.productId}">
						</div>
						<div>
							<input type="button" value="뒤로가기" onclick="goBack();">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>
<!-- Checkout Section End -->