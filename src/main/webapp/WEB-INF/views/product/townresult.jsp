<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>

</head>


<br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


<div style=background-color:white;">
    <div id="container">

    </div>


    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <div style="text-align: right">

                    <a href="/town/town">뒤로가기</a>
                </div>
                <div style="text-align: center">
                    <h3> [동네는 최대 3 지역 가능]</h3>
                </div>


                <br/>

                <form:form action="" name="isc" onsubmit="return check_submit();" method="get">

                    <input type="text" name="wishtown" value="" placeholder="  원하는 동네 검색"/>
                    <br/><br/>

                    <input type="hidden" name="memberId" value="${memberDTO.id}">

                    <input type="submit" value="동네검색하기" style="background-color: #90C8AC; color: white"/><br/>
                </form:form>


                <form:form action="town" method="get">


                </form:form>
                <form:form action="town" name="townadd" method="post">
                    <input type="hidden" name="memberId" value="${memberDTO.id}"/>
                    <input type="hidden" name="townName" id="coordinateId" style="width: 15%;">

                </form:form><br/>
                <div style="text-align: center">
                    <c:if test="${wishtown ==  '강남구'}">

                        <c:forEach var="Gangnam" items="${Gangnam}">


                            <a href="#" onclick="statusChange(this)"><p
                                    style="margin-top: 10px; margin-bottom: 10px"> ${Gangnam.townName} </p></a>

                            <p></p>

                        </c:forEach>


                    </c:if>

                    <c:if test="${wishtown == '송파구'}">

                        <c:forEach var="Songpa" items="${Songpa}">


                            <a href="#" onclick="statusChange(this)"><p
                                    style="margin-top: 10px; margin-bottom: 10px"> ${Songpa.townName}</p></a>
                            <p></p>
                        </c:forEach>

                    </c:if>


                    <c:if test="${wishtown ==  '강동구'}">

                        <c:forEach var="Gangdong" items="${Gangdong}">


                            <a href="#" onclick="statusChange(this)"><p
                                    style="margin-top: 10px; margin-bottom: 10px"> ${Gangdong.townName}</p></a>
                            <p></p>
                        </c:forEach>

                    </c:if>


                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>