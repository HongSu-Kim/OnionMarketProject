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



<br/><br/><br/><br/>

<strong><h3>키워드 알림 설정</h3><br/></strong>

<form:form action="" method="post" modelAttribute="keywordCreateDto">


        <input type="hidden" name="memberId" value="${memberDTO.id}">



<input type="text" name="keywordName"  placeholder="키워드를 입력해주세요 (예: 유니폼)" size="33" />
<form:errors path="keywordName"/>
 <input type="submit" value="키워드등록" />

</form:form>

<form:form action="kewordDelete" method="post">

      <br/><br/>

        <c:forEach var="MykeywordList" items="${MykeywordList}">
            <strong>${MykeywordList.keywordName} </strong>

            <button type="submit"  class="btn btnEvent" name="id" value="${MykeywordList.id} ">
                <img src="https://cdn.pixabay.com/photo/2017/11/10/05/24/delete-2935433_960_720.png" alt="btnImages" class="btnImages"
                     height="23" width="23" border="0" align="left">
            </button>



        </c:forEach>




    </form:form>



<%--<form:form action="mykeyword" method="get">--%>

<%--&lt;%&ndash;    <input type="hidden" name="userId" value="asd">&ndash;%&gt;--%>

<%--    <input type="submit" value="MY키워드확인" />--%>
<%--</form:form>--%>


<br/><br/>



</body>
</html>