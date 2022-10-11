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
</head>
<body>

<h1>상품 등록하기</h1>

<form action="/product/add" method="post" enctype="multipart/form-data">
	<div class="add">
		상품명: <input type="text" name="productName"/><br/>
		제목: <input type="text" name="subject"/><br/>
		내용: <br/><textarea rows="10" cols="50" name="content">상품설명</textarea><br/>
		상품가격: <input type="text" name="price"/><br/>

<%--		상품이미지: <input type="file" name="file"/>--%>
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