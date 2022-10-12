<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Login</title>

</head>

<body>

<h1>로그인</h1>
<hr>

<form action="/member/login" method="post">

    <input type="text" name="userId" placeholder="아이디를 입력해 주세요.">
    <input type="password" name="pwd" placeholder="비밀번호를 입력해 주세요.">
    <button type="submit">로그인</button>
</form>

</body>

</html>
