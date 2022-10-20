<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
	request.setCharacterEncoding("UTF-8");
	//String cp = request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		.wrap {
			width: 100%;
			height: auto;
			position: relative;
			display: inline-block;
		}
		.wrap textarea {
			width: 100%;
			resize: none;
			min-height: 4.5em;
			line-height:1.6em;
			max-height: 9em;
		}

	    input.error, textarea.error{display: inline-block; color: red;}
		label.error{display:block;color:red;}
	</style>
	</style>
	<title>상품 등록</title>
</head>
<body>
<!-- Checkout Section Begin -->
<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<h4>기본 정보<span style="font-size: small;color:#FF5058;margin: 0px 0px 0px 32px">*필수항목</span></h4>
			<form action="/product/add" method="post" enctype="multipart/form-data" id="productForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="col-lg-12">
								<div class="checkout__input">
									<p> 제 목 <span>*</span></p>
									<input type="text" id="subject" name="subject" placeholder="제목을 입력해주세요.">
									<hr/>
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>거래 지역<span>*</span></p><%--townList foreach로 설정--%>
							<label for="townId">
								<c:forEach var="townList" items="${townList}">
									<p>${townList.townName}<input type="radio" style="width: 15px;height: 15px; margin-left:6px" id="townId" name="townId" value="${townList.id}"></p>
								</c:forEach>
							</label>

							<p><button type="button" onclick="location.href='/town/town'">내 동네 설정하러 가기</button></p><br>
							<hr/>
						</div>
						<div class="checkout__input">
							<p>경매 등록</p><%--true/false로 변경--%>
							<p style="color: #aaaaaa">
								경매 기간은 3일입니다.
								<input type="checkbox" style="width: 15px;height: 15px;margin: 8px;" name="auctionStatus" value="true">
								<input type="hidden" name="auctionStatus" value="false">
								<hr/>
							</p>
							<span class="checkmark"></span>
						</div>
						<div class="checkout__input">
							<p>카테고리 선택<span>*</span></p>
							<select>
								<option value="" selected="" disabled="">선택하세요</option>
								<c:forEach var="topCategory" items="${topCategory}">
									<option>${topCategory.categoryName}</option>
								</c:forEach>
							</select>
							<select id="categoryId" name="categoryId">
								<option value="" selected="" disabled="">선택하세요</option>
								<c:forEach var="subCategory" items="${subCategory}">
									<option value="${subCategory.id}">${subCategory.categoryName}</option>
								</c:forEach>
							</select>
							<br><br><br><br><hr/>
						</div>
						<div class="checkout__input">
							<p>상품 가격<span>*</span></p>
							<input type="text" id="price" name="price">
							<hr/>
						</div>
						<div class="checkout__input">
							<p>페이 결제</p><%--true/false로 변경--%>
							<p style="color: #aaaaaa">
								(페이 결제 시 혜택..?)
								<input type="checkbox" style="width: 15px;height: 15px;margin: 8px;" name="payStatus" value="true">
								<input type="hidden" name="payStatus" value="false">
							</p>
							<span class="checkmark"></span>
							<hr/>
						</div>
						<div class="checkout__input">
							<p>설 명<span>*</span></p>
							<div class="wrap">
								<textarea id="content" name="content" class="wrap"
										  placeholder="여러 장의 상품 사진과 구입 연도, 브랜드, 사용감, 하자 유무 등 구매자에게 필요한 정보를 꼭 포함해 주세요. (10자 이상)"></textarea>
								<div id="counter" align="right"/>
							</div>
							<hr/>
							<br/>
						</div>

						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<div class='addInput'>
										<p>상품 이미지<span>*</span></p>
										<input type="file" name="fileList" id="fileList"><button type="button" class="btnRemove">삭제</button><br>
									</div>
									<button type="button" class="btnAdd">이미지 추가</button><br>
								</div>
							</div>
							<div class="col-lg-6">
								* 상품 이미지는 640x640에 최적화 되어 있습니다.<br/>
								- 상품 이미지는 PC에서는 1:1, 모바일에서는 1:1.23 비율로 보여집니다.<br/>
								- 이미지는 상품 등록 시 정사각형으로 잘려서 등록됩니다.<br/>
								- 이미지를 클릭할 경우 원본 이미지를 확인할 수 있습니다.<br/>
								- 이미지를 클릭 후 이동하여 등록순서를 변경할 수 있습니다.<br/>
								- 큰 이미지일 경우 이미지가 깨지는 경우가 발생할 수 있습니다.<br/>
								- 최대 지원 사이즈인 640 X 640으로 리사이즈 해서 올려주세요.(개당 이미지 최대 5M)<br/>
							</div>
						</div>
						<div>
							<input type="submit" value="상품 등록">
						</div>
						<div>
							<input type="button" value="뒤로가기" onclick="goBack();">
						</div>
					</div>
<%--					<div class="col-lg-4 col-md-6">--%>
<%--						<div class="checkout__order">--%>
<%--							<h4>Your Order</h4>--%>
<%--							<div class="checkout__order__products">Products <span>Total</span></div>--%>
<%--							<ul>--%>
<%--								<li>Vegetable’s Package <span>$75.99</span></li>--%>
<%--								<li>Fresh Vegetable <span>$151.99</span></li>--%>
<%--								<li>Organic Bananas <span>$53.99</span></li>--%>
<%--							</ul>--%>
<%--							<div class="checkout__order__subtotal">Subtotal <span>$750.99</span></div>--%>
<%--							<div class="checkout__order__total">Total <span>$750.99</span></div>--%>
<%--							<div class="checkout__input__checkbox">--%>
<%--								<label for="acc-or">--%>
<%--									Create an account?--%>
<%--									<input type="checkbox" id="acc-or">--%>
<%--									<span class="checkmark"></span>--%>
<%--								</label>--%>
<%--							</div>--%>
<%--							<p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do eiusmod tempor incididunt--%>
<%--								ut labore et dolore magna aliqua.</p>--%>
<%--							<div class="checkout__input__checkbox">--%>
<%--								<label for="payment">--%>
<%--									Check Payment--%>
<%--									<input type="checkbox" id="payment">--%>
<%--									<span class="checkmark"></span>--%>
<%--								</label>--%>
<%--							</div>--%>
<%--							<div class="checkout__input__checkbox">--%>
<%--								<label for="paypal">--%>
<%--									Paypal--%>
<%--									<input type="checkbox" id="paypal">--%>
<%--									<span class="checkmark"></span>--%>
<%--								</label>--%>
<%--							</div>--%>
<%--							<button type="submit" class="site-btn">PLACE ORDER</button>--%>
<%--						</div>--%>
<%--					</div>--%>
				</div>
			</form>
		</div>
	</div>
</section>
<!-- Checkout Section End -->

</body>
</html>

