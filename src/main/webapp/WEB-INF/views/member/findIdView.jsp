<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>아이디 찾기</title>

    <script type="text/javascript">
        var msg = "${msg}";

        if (msg != "") {
            alert(msg);
        }
    </script>

</head>

<body>

    <div class="posts_list">
        <h1 class="h4 text-gray-900 mb-2">아이디 찾기</h1>
    </div>

    <form action="/member/findId" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form-group">
            <input type="email" class="form-control" name="email" placeholder="이메일을 입력해주세요">
        </div>
        <button type="submit" class="btn btn-primary bi bi-lock-fill">확인</button>
        <a href="/member/login" class="btn btn-primary bi bi-lock-fill">로그인</a>
        <a href="#" class="btn btn-primary bi bi-lock-fill">비밀번호 찾기</a>
    </form>

    <hr>
    <div class="text-center">
        <a class="small" href="#">메인페이지</a>
    </div>

</body>

</html>
