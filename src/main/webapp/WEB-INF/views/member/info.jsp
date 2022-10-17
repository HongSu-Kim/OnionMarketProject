<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내 정보</title>
</head>
<body>
<h1>내 정보</h1>
<hr>
<span sec:authentication="name"></span> 님 반갑습니다.
<%--<span sec:authentication="name"></span> 님 반갑습니다.--%>
<div sec:authentication="principal.authorities"></div>

<sec:authorize access="isAuthenticated()">
    <a sec:authorize access="isAuthenticated()" href="/member/modify">회원정보 수정</a>
</sec:authorize>

<a href="/member/home">홈으로 가기</a>
</body>
</html>
