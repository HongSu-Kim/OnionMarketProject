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

		$('#checkbox-value').text($('#checkbox1').val());

		$("#checkbox1").on('change', function() {
			if ($(this).is(':checked')) {
				$(this).attr('value', 'true');
			} else {
				$(this).attr('value', 'false');
			}

			$('#checkbox-value').text($('#checkbox1').val());
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.btnAdd').click(function () {
				$('.addInput').append(
						'<input type="file" name="fileList">\
                        <button type="button" class="btnRemove">삭제</button><br/>'
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

<h1>상품 등록하기</h1>

<form action="/product/add" method="post" enctype="multipart/form-data">
	<div class="add">
		제목: <input type="text" name="subject"/><br/>
		동네 선택<br/>
		<%--townList foreach로 설정--%>
		<label for="town1">${townName}</label><input type="radio" id="town1" name="townName" value="${townName}"/>
		<br/>
		경매 등록<%--true/false로 변경--%>
		<input type="checkbox" name="auctionStatus" id="checkbox1" value="false"/>

		<br/>

		카테고리 선택<br/>

		상품가격: <input type="text" name="price"/><br/>
		설명: <br/><textarea rows="10" cols="50" name="content">상품설명</textarea><br/>
		<hr/>
		<div class='addInput'>

		</div>
		<button type="button" class="btnAdd">이미지 추가</button><br/>
	</div>
	<div>
		<input type="submit" value="상품 등록"/>
	</div>

</form>

<div>
	<input type="button" value="뒤로가기" onclick="goBack();"/>
</div>




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