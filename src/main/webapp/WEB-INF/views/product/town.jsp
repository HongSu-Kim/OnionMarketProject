<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게 시 판</title>


</head>
<body>



<form:form action="townresult" method="post">


원하는 동네 검색: <input type="text" name="wishtown" value="" />
    <input type="hidden" name="memberId" value="${memberDTO.id}">
   <input type="submit" value="동네설정하기"/>
</form:form>

<strong> [동네예시]<br/><br/>
강남구 / 송파구 / 강동구
</strong>

<br/><br/>


</body>
</html>