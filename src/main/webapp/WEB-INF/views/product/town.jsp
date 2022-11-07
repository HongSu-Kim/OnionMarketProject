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
    <title>게 시 판</title>
    <script src="//localhost:8083"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="../dist/rangeslider.js"></script>
</head>
<body>
<br/><br/><br/><br/>
<div style="text-align: center">
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h3>동네 설정</h3>
                <div style="text-align: right">
                    <a href="/product/wishRangeList"><span class="icon_close"></span></a>
                </div>
                <br/> <br/>
                <form:form action="/town/townresult" modelAttribute="townFindDTO" name="isc"
                           onsubmit="return check_submit();" method="get">

                    <input type="text" name="wishtown" value="" style="background-color: white "
                           placeholder="동네설정은 최대 3곳 가능">

                    <form:errors path="wishtown"/>
                    <input type="hidden" name="memberId" value="${memberDTO.id}"><br/><br/>
                    <input type="submit" value="동네설정하기" style="background-color: #90C8AC; color: white;"/><br/><br/>
                </form:form>
                <strong>
                    <현재 등록된 동네>
                </strong><br/>
                <form:form action="townDelete" method="get">

                    <c:forEach var="list" items="${list}">

                        <a href="#" onclick="statusChange(this)">
                            <br/>
                            <strong> ${list.townName}</strong>
                        </a>
                        <button type="submit" name="id" value="${list.id}" style="">
                            <a href="/town/townDelete"><span class="icon_close"></span></a>

                        </button>
                    </c:forEach>
                </form:form>
                <br/>
                <strong> [동네예시]
                    강남구/ 서초구/ 동작구/ 관악구/ 송파구/ 강동구/ 광진구/ 성동구/ 용산구
                </strong>
                <br/>
                <c:if test="${empty range}">
                    <form:form action="/product/wishRangeList" name="townadd" method="post">
                        <div id="js-example-disabled">
                            <input type="range" name="range" id="range" min="0" max="10" step="1" value="0"
                                   data-rangeslider
                                   class="seek-bar"/>
                        </div>
                        <input type="hidden" name="townName" id="coordinateId" style="width: 15%;"/>

                        <input type="hidden" name="memberId" value="${memberDTO.id}">
                        <output> 거리설정</output>
                    </form:form>
                </c:if>
            </div>
        </div>
    </div>
</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</body>
</html>