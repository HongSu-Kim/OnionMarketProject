<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>비밀번호 찾기</title>

</head>

<body>

    <div class="posts_list">
        <h1 class="h4 text-gray-900 mb-2">입력된 정보로 임시 비밀번호가 발송됩니다.</h1>
    </div>

    <form action="/member/findPwd" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="form-group">
                <label>이메일</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="이메일을 입력해주세요"/>
            </div>
            <button type="submit" id="pwdFind" class="btn btn-primary bi bi-lock-fill">확인</button>
        <hr/>
        <a href="/member/login" class="btn btn-primary bi bi-lock-fill">로그인</a>
        <a href="/member/findPwd" class="btn btn-primary bi bi-lock-fill">비밀번호 찾기</a>
        <a href="/member/join" class="btn btn-primary bi bi-lock-fill">회원가입</a>
        </form>

    <hr>
    <div class="text-center">
        <a class="small" href="#">메인페이지</a>
    </div>

</body>

</html>
