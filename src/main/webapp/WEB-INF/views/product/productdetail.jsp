<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품 정보</title>
</head>
<body>

<div>
	<h1>상품 정보</h1>
	상품명: ${dto.productName}<br/>
	제목: ${dto.subject}<br/>
	내용: <br/><textarea rows="10" cols="50">${dto.content}</textarea><br/>
	상품가격: ${dto.price}<br/>
	등록일: ${dto.uploadDate} <br/>
	<%--	이미지: ${id.file}--%>
	<hr/>
</div>

<form action="/product/productmain" method="get">
	<input type="submit" value="목록 보기"/>
</form>

</body>
</html>