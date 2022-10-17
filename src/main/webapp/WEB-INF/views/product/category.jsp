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
    <title>카테고리 생성</title>

</head>
<body>



<strong>현재 상위카데고리</strong>
<c:forEach var="category" items="${category}">

  [${category.categoryName}]

</c:forEach>

<form:form action="" method="post" modelAttribute="categoryAddDTO">

    <br/>

    -------------------------------------------------------------------------<br/>
    상위카테고리 이름: <input type="text" name="topcategoryName"/>
     <input type="hidden" name="categoryName" value=""/>
    <form:errors path="categoryName"/>
    <input type="submit" value="상위카테고리 추가"/>
    <br/><br/>
</form:form>
    -------------------------------------------------------------------------<br/>
<form:form action="" method="post">
    상위카테고리 이름: <input type="text" name="topcategoryName"/><br/>
    하위카테고리 이름: <input type="text" name="categoryName"/>


    <input type="submit" value="하위카테고리 추가"/><br/>

    -------------------------------------------------------------------------
</form:form>

<form:form action="categoryupdate" method="get">
    카테고리 수정하기 <input type="submit" value="수정">
</form:form>


</body>
</html>