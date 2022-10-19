<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Home</title>

</head>
<body>

<h1>Member Home</h1>
<hr>

    <sec:authorize access="isAnonymous()">
        <a href="/member/login">로그인</a>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <a href="/member/join">회원가입</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="/member/logout">로그아웃</a>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <a sec:authorize access="isAuthenticated()" href="/member/mypage">내정보</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a sec:authorize access="isAuthenticated()" href="/member/mypage">내정보</a>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/member/admin">어드민</a>
    </sec:authorize>

</body>
</html>