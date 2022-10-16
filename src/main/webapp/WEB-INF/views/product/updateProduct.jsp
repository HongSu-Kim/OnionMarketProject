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
	<title>상품 수정</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		function goBack(){
			window.history.back();
		}
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

<h1>상품 수정하기</h1>

<form action="/product/update" method="post" enctype="multipart/form-data">
	<div class="update">
		상품명: <input type="text" name="productName" value="${dto.productName}"/><br/>
		제목: <input type="text" name="subject" value="${dto.subject}"/><br/>
		내용: <br/><textarea rows="10" cols="50" name="content" value="${dto.content}">상품설명</textarea><br/>
		상품가격: <input type="text" name="price" value="${dto.price}"/><br/>
		<hr/>
		<div class='addInput'>

		</div>
		<button type="button" class="btnAdd">이미지 추가</button><br/>
	<div>
		<input type="hidden" name="productId" value="${productId}">
		<input type="submit" value="수정하기"/>
	</div>
</form>

<div>
	<input type="button" value="뒤로가기" onclick="goBack();"/>
</div>
</body>
</html>