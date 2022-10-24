<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Password Check</title>

    <script>
        var msg = '${msg}';
        if(msg === 'msg') {
            alert("비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
        }
    </script>

</head>

<body onload="document.myForm.pwd.focus();">

<h1>비밀번호 확인</h1>
<hr>

<div id="posts_list">
    <div class="container col-md-6">
        <form action="/member/preModify" method="post" name="myForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" class="form-control" name="preModifypwd" placeholder="비밀번호를 입력해주세요">
            </div>


            <button class="btn btn-primary bi bi-lock-fill">확인</button>
            <a href="/member/mypage" role="button" class="btn btn-primary bi bi-lock-fill">마이 페이지</a>
        </form>
            <div>&nbsp;</div>
    </div>
</div>

</body>

</html>
