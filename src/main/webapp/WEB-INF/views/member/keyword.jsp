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

                    <a href="/product/wishRangeList"><span class="icon_close"></span></a>
                </div>
                <div style="text-align: center">

                </div>
                <div style="text-align: center">
                    <div style="position: center"><h3>키워드 알림 설정</h3></div>
                    <br/><br/>
                </div>
                <form:form action="" method="post" modelAttribute="keywordCreateDto">


                    <input type="hidden" name="memberId" value="${memberDTO.id}">


                    <input type="text" name="keywordName" placeholder="키워드를 입력해주세요 (예: 유니폼)" size="33"
                           style="color: black; margin-right: 10cm;"/><br/><br/>
                    <form:errors path="keywordName"/>
                    <strong> <input type="submit" value="키워드등록"
                                    style="background-color: #90C8AC; color: white"/><br/></strong>

                </form:form>

                <form:form action="kewordDelete" method="post">

                <br/><br/>

                <c:forEach var="MykeywordList" items="${MykeywordList}">


                <button type="submit" class="btn btnEvent" name="id" value="${MykeywordList.id}
                            " style="background-color:#90C8AC; color: white;  border-radius: 20px;">
                    <div>
                            ${MykeywordList.keywordName}
                        <a href="/product/list"><span class="icon_close"></span></a>

                    </div>


                    </c:forEach>


                    </form:form>


            </div>

        </div>
    </div>


</div>
</body>
</html>


<br/><br/>


</body>
</html>