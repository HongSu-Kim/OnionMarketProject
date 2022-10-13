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
</head>
<body>

<h1>상품 수정하기</h1>

<form action="/product/update" method="post" enctype="multipart/form-data">
	<div class="update">
		상품명: <input type="text" name="productName" value="${dto.productName}"/><br/>
		제목: <input type="text" name="subject" value="${dto.subject}"/><br/>
		내용: <br/><textarea rows="10" cols="50" name="content" value="${dto.content}">상품설명</textarea><br/>
		상품가격: <input type="text" name="price" value="${dto.price}"/><br/>

		상품이미지: <input type="file" name="file" value="${dto.file}"/>
	</div>
	<div>
		<input type="submit" value="상품 등록"/>
	</div>
</form>

<form action="/product/productmain" method="get">
	<input type="submit" value="뒤로가기"/>
</form>

</body>
</html>