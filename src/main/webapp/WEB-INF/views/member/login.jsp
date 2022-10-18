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

<body onload="document.myForm.userId.focus();">

<h1>로그인</h1>
<hr>

<div id="posts_list">
    <div class="container col-md-6">
        <form action="/member/loginProc" method="post" name="myForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="form-group">
                <label>아이디</label>
                <input type="text" class="form-control" name="userId" placeholder="아이디를 입력해주세요">
            </div>

            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력해주세요">
            </div>

            <c:if test="${not empty error}">
                <p id="valid" class="alert alert-danger">${exception}</p>
            </c:if>

            <button class="form-control btn btn-primary bi bi-lock-fill">로그인</button>
            <div>&nbsp;</div>
            <a href="/member/join" role="button" class="form-control btn btn-primary bi bi-lock-fill">회원가입</a>
        </form>
    </div>
</div>

</body>

</html>
