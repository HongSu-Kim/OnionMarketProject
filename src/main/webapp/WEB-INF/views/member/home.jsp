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

<h1>메인 페이지</h1>
<hr>
<a sec:authorize="isAnonymous()" href="/member/login">로그인</a>
<a sec:authorize="isAnonymous()" href="/member/join">회원가입</a>
<a sec:authorize="isAuthenticated()" href="/member/logout">로그아웃</a>
<a sec:authorize="hasRole('ROLE_USER')" href="/member/info">내정보</a>
<a sec:authorize="hasRole('ROLE_ADMIN')" href="/member/admin">어드민</a>

</body>
</html>