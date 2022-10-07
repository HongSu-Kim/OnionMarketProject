<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Join</title>

</head>
<body>

<h2>회원가입</h2>

<form action="/member/join" method="post">

    아이디:<input type="text" name="userId"><br/>
    비밀번호:<input type="password" name="pwd"><br/>
    이름:<input type="text" name="name"><br/>
    닉네임:<input type="text" name="nickname"><br/>
    우편번호:<input type="text" name="postcode"><br/>
    주소:<input type="text" name="address"><br/>
    상세주소:<input type="text" name="detailAddress"><br/>
    참고사항:<input type="text" name="extraAddress"><br/>
    전화번호:<input type="tel" name="tel"><br/>

    <input type="submit" value="가입하기"/>

</form>

</body>
</html>