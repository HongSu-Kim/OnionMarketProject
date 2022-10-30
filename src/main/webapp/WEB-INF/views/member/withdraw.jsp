<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>회원 탈퇴</title>

</head>

<body onload="document.myForm.pwd.focus();">

<h1>회원 탈퇴</h1>
<hr>

<div id="posts_list">
    <div class="container col-md-6">
        <form action="/member/withdraw" method="post" name="myForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" class="form-control" name="withdrawpwd" id="withdrawpwd" placeholder="비밀번호를 입력해주세요">
            </div>

            <div class="form-group">
                <label>비밀번호 재확인</label>
                <input type="password" name="pwdCheck" id="pwdCheck" class="form-control" placeholder="비밀번호를 입력해주세요"/>
            </div>


            <div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
            <div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>

            <button class="btn btn-primary bi bi-lock-fill" id="btn-member-withdraw">탈퇴하기</button>
            <a href="/member/mypage" role="button" class="btn btn-primary bi bi-lock-fill">마이 페이지</a>
        </form>
            <div>&nbsp;</div>
    </div>
</div>

</body>

</html>
