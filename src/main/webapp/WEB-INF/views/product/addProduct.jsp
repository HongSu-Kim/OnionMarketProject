<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
	//String cp = request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품 등록</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		function goBack(){
			window.history.back();
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.btnAdd').click(function () {
				$('.addInput').append(
						'<input type="file" name="fileList"><button type="button" class="btnRemove">삭제</button><br/>'
				);//input file
				$('.btnRemove').on('click',function(){//this='.btnRemove'
					$(this).prev().remove();// .prev()=input file을 가리키고 remove()실행
					$(this).next().remove();//<br/> 삭제
					$(this).remove();//버튼 삭제
				});
			});
		});

		function setImageFromFile(input, expression) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$(expression).attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>

</head>
<body>
<!-- Checkout Section Begin -->
<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<h4>기본 정보</h4>
			<form action="/product/add" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="row">
							<div class="col-lg-7">
								<div class="checkout__input">
									<p> 제 목 <span>*</span></p>
									<input type="text" name="subject"/>
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>거래 지역<span>*</span></p><%--townList foreach로 설정--%>
							<label for="town1">${townName}</label><input type="radio" style="width: 15px;height: 15px;" id="town1" name="townName" value="${townName}"/>
						</div>
						<div class="checkout__input">
							<p>경매 등록<span>*</span></p><%--true/false로 변경--%>
							<p style="color: #aaaaaa">
								경매 기간은 3일입니다.
								<input type="checkbox" style="width: 15px;height: 15px;margin: 8px;" name="auctionStatus" value="true"/>
								<input type="hidden" name="auctionStatus" value="false"/>
							</p>
							<span class="checkmark"></span>
						</div>
						<div class="checkout__input">
							<p>카테고리 선택<span>*</span></p>
							<select>
								<c:forEach var="topCategory" items="${topCategory}">
									<option>${topCategory.categoryName}</option>
								</c:forEach>
							</select>
							<select name="categoryId">
								<c:forEach var="subCategory" items="${subCategory}">
									<option value="${subCategory.id}">${subCategory.categoryName}</option>
								</c:forEach>
							</select><br/><br/><br/><br/>
						</div>
						<div class="checkout__input">
							<p>상품 가격<span>*</span></p>
							<input type="text" name="price"/>
						</div>
						<div class="checkout__input">
							<p>페이 결제<span>*</span></p><%--true/false로 변경--%>
							<p style="color: #aaaaaa">
								(페이 결제 시 혜택..?)
								<input type="checkbox" style="width: 15px;height: 15px;margin: 8px;" name="payStatus" value="true"/>
								<input type="hidden" name="payStatus" value="false"/>
							</p>
							<span class="checkmark"></span>
						</div>
						<div class="checkout__input">
							<p>설 명<span>*</span></p><br/>
							<textarea rows="10" cols="100" name="content" placeholder="상품설명"></textarea>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<div class='addInput'>

									</div>
									<button type="button" class="btnAdd">이미지 추가</button><br/>
								</div>
							</div>
						</div>
						<div>
							<input type="submit" value="상품 등록"/>
						</div>
						<div>
							<input type="button" value="뒤로가기" onclick="goBack();"/>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="checkout__order">
							<h4>Your Order</h4>
							<div class="checkout__order__products">Products <span>Total</span></div>
							<ul>
								<li>Vegetable’s Package <span>$75.99</span></li>
								<li>Fresh Vegetable <span>$151.99</span></li>
								<li>Organic Bananas <span>$53.99</span></li>
							</ul>
							<div class="checkout__order__subtotal">Subtotal <span>$750.99</span></div>
							<div class="checkout__order__total">Total <span>$750.99</span></div>
							<div class="checkout__input__checkbox">
								<label for="acc-or">
									Create an account?
									<input type="checkbox" id="acc-or">
									<span class="checkmark"></span>
								</label>
							</div>
							<p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do eiusmod tempor incididunt
								ut labore et dolore magna aliqua.</p>
							<div class="checkout__input__checkbox">
								<label for="payment">
									Check Payment
									<input type="checkbox" id="payment">
									<span class="checkmark"></span>
								</label>
							</div>
							<div class="checkout__input__checkbox">
								<label for="paypal">
									Paypal
									<input type="checkbox" id="paypal">
									<span class="checkmark"></span>
								</label>
							</div>
							<button type="submit" class="site-btn">PLACE ORDER</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>
<!-- Checkout Section End -->

</body>
</html>


<%--<div>--%>
<%--	<ul>--%>
<%--		<li class="menu">--%>
<%--			<a><img src="" alt="상위메뉴이미지1"/></a>--%>
<%--			<ul class="hide">--%>
<%--				<li>메뉴1-1</li>--%>
<%--				<li>메뉴1-2</li>--%>
<%--				<li>메뉴1-3</li>--%>
<%--				<li>메뉴1-4</li>--%>
<%--				<li>메뉴1-5</li>--%>
<%--				<li>메뉴1-6</li>--%>
<%--			</ul>--%>
<%--		</li>--%>

<%--		<li class="menu">--%>
<%--			<a><img src="" alt="상위메뉴이미지2"/></a>--%>
<%--			<ul class="hide">--%>
<%--				<li>메뉴2-1</li>--%>
<%--				<li>메뉴2-2</li>--%>
<%--				<li>메뉴2-3</li>--%>
<%--				<li>메뉴2-4</li>--%>
<%--				<li>메뉴2-5</li>--%>
<%--				<li>메뉴2-6</li>--%>
<%--			</ul>--%>
<%--		</li>--%>
<%--	</ul>--%>
<%--</div>--%>
<%--<script>--%>
<%--	$(document).ready(function(){--%>
<%--		$(".menu>a").click(function(){--%>
<%--			var submenu = $(this).next("ul");--%>

<%--			// submenu 가 화면상에 보일때는 위로 부드럽게 접고 아니면 아래로 부드럽게 펼치기--%>
<%--			if( submenu.is(":visible") ){--%>
<%--				submenu.slideUp(1000);--%>
<%--			}else{--%>
<%--				submenu.slideDown(1000);--%>
<%--			}--%>
<%--		}).mouseover(function(){--%>
<%--			$(this).next("ul").slideDown();--%>
<%--		});--%>
<%--	});--%>
<%--</script>--%>

<%--<style>--%>
<%--	.menu a{cursor:pointer;}--%>
<%--	.menu .hide{display:none;}--%>
<%--</style>--%>