<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>


<form:form action="town" method="get">
    <input type="submit" value="동네 다시고르기"/>
</form:form>


<form:form action="town" method="post">

   원하는 지역번호 입력: <input type="text" name="coordinateId" value="" style="width: 15%;">
   <input type="submit" value="동네설정하기"/>
</form:form>

<br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<strong><${wishtown}></strong>

<c:if test="${wishtown == '강남구'}">

<c:forEach var="Gangnam" items="${Gangnam}">
    <br/>

  <input type="text" value="${Gangnam.id} ${Gangnam.townName}" style="width: 24%;"/>
</c:forEach>

<br/>

</c:if>

<c:if test="${wishtown == '송파구'}">

<c:forEach var="Songpa" items="${Songpa}">
    <br/>

    <input type="text" value="${Songpa.id} ${Songpa.townName}" style="width: 24%;"/>
</c:forEach>

</c:if>


<c:if test="${wishtown == '강동구'}">

    <c:forEach var="Gangdong" items="${Gangdong}">
        <br/>

        <input type="text" value="${Gangdong.id} ${Gangdong.townName}" style="width: 24%;"/>
    </c:forEach>

</c:if>


</body>
</html>