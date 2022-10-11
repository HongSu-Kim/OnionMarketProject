<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Join</title>

</head>
<body>

<h1>회원 가입</h1>
<hr>

<form action="/member/join" method="post">

    <input type="text" name="userId" placeholder="아이디를 입력해 주세요."><br/>
    <input type="password" name="pwd" placeholder="비밀번호를 입력해 주세요."><br/>
    <input type="text" name="name" placeholder="이름을 입력해 주세요."><br/>
    <input type="text" name="nickname" placeholder="닉네임을 입력해 주세요."><br/>
    <input type="date" name="birth" placeholder="생일을 입력해 주세요."><br/>
    <input type="text" name="tel" placeholder="전화번호를 입력해 주세요."><br/>
    <input type="text" name="postcode" placeholder="우편번호를 입력해 주세요."><br/>
    <input type="text" name="address" placeholder="주소를 입력해 주세요."><br/>
    <input type="text" name="detailAddress" placeholder="상세주소를 입력해 주세요."><br/>
    <input type="text" name="extraAddress" placeholder="주소 추가사항을 입력해 주세요."><br/>
    <input type="text" name="email" placeholder="이메일을 입력해 주세요."><br/>
    <button type="submit">가입하기</button>
</form>

</body>
</html>