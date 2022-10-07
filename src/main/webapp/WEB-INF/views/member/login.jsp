<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Login</title>

</head>

<body>

<h1>로그인 페이지</h1>
<h1>Login Page</h1>

<h1>로그인</h1>
<hr>
<form action="/member/login" method="post">
    <input type="text" name="userId" placeholder="아이디를 입력해주세요."><br/>
    <input type="password" name="pwd" placeholder="패스워드를 입력해주세요."><br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">로그인</button>
</form>

</body>

</html>
