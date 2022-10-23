<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>아이디 찾기</title>

</head>

<body>

<div class="card o-hidden border-0 shadow-lg my-5">

    <div class="jumbotron">
        아이디는&nbsp;
                <span>[${member.userId}]</span>
        &nbsp;입니다.
        <hr/>
        <button type="button" class="btn btn-primary" onclick="location.href='/member/login'">로그인</button>
        <button type="button" class="btn btn-primary" onclick="location.href='#'">메인페이지</button>


    </div>

</body>

</html>
