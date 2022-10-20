<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<%

    String userId = request.getParameter("userId");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>카테고리 생성</title>

</head>
<body>


<br/>
<br/>
<br/>




<form:form action=""  method="post">
    <input type="text" name="id" value="삭제할 번호 입력!"/>
    <input type="submit"  value="삭제하기"/>


    <br/>

</form:form>

-------------------------------------------------------------------------<br/>
   <strong><상위카테고리></상위카테고리></strong><br/>
    <c:forEach var="Topcategory" items="${Topcategory}">
        <strong>${Topcategory.id}.${Topcategory.categoryName}</strong><br/>




    </c:forEach>
-------------------------------------------------------------------------<br/>
<strong ><하위카테고리></하위카테고리></strong><br/>
<c:forEach var="Subcategory" items="${Subcategory}">
    <strong>${Subcategory.id}.${Subcategory.categoryName}</strong><br/>


</c:forEach>

-------------------------------------------------------------------------<br/>

<%--    <c:forEach var="uniformPARENT_ID" items="${uniformPARENT_ID}">--%>
<%--    <input type="text" name="id" value="${uniformPARENT_ID.id} ${uniformPARENT_ID.categoryName}"/><br/>--%>
<%--    </c:forEach>--%>


<%--        <select name="categoryName">--%>
<%--            <c:forEach var="uniformPARENT_ID" items="${uniformPARENT_ID}">--%>

<%--            <option value="${uniformPARENT_ID.categoryName}">${uniformPARENT_ID.categoryName} </option>--%>
<%--                <br/>--%>

<%--            </c:forEach>--%>
<%--        </select>--%>
<%--                <br/>  <br/>  <br/>--%>

<%--                <strong>축구화</strong><br/>--%>

<%--                <c:forEach var="footballbootPARENT_ID" items="${footballbootPARENT_ID}">--%>
<%--                <input type="text" name="id" value="${footballbootPARENT_ID.id} ${footballbootPARENT_ID.categoryName}"/><br/>--%>
<%--                </c:forEach>--%>


<%--                <select name="categoryName">--%>
<%--                    <c:forEach var="footballbootPARENT_ID" items="${footballbootPARENT_ID}">--%>

<%--                        <option value="${footballbootPARENT_ID.categoryName}">${footballbootPARENT_ID.categoryName} </option>--%>


<%--                    </c:forEach>--%>
<%--                </select>--%>

<%--                    <br/>  <br/>  <br/> <br/>  <br/>  <br/>--%>








</body>
</html>