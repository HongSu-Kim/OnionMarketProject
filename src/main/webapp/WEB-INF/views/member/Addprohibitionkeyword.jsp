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


</head>
<body>

<br/><br/><br/><br/><br/>


${categoryList}
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
                    <div style="position: center"><h3>금지 키워드 설정</h3></div>
                    <br/><br/>
                </div>
                <form:form action="" method="post" modelAttribute="">


                    <input type="hidden" name="memberId" value="${memberDTO.id}">


                    <input type="text" name="prohibitionKeywordName" placeholder="금지키워드를 입력해주세요 (예: 담배)" size="33"
                           style="color: black; margin-right: 10cm;"/><br/><br/>
                    <form:errors path="prohibitionKeywordName"/>
                    <strong> <input type="submit" value="금지키워드 등록"
                                    style="background-color: #90C8AC; color: white"/><br/></strong>

                </form:form>

                <form:form action="/prohibitionkeyword/prohibitionkeywordDelete" method="post">

                    <br/><br/>

                    <c:forEach var="prohibitionKeywordList" items="${prohibitionKeywordList}">


                        <button type="submit" class="btn btnEvent" name="id" value="${prohibitionKeywordList.id}
                            " style="background-color:#90C8AC; color: white;  border-radius: 20px;">
                            <div>
                                    ${prohibitionKeywordList.prohibitionKeywordName}
                                <a href="/product/prohibitionkeywordDelete"><span class="icon_close "
                                                                                  style="color: black"></span></a>

                            </div>

                        </button>


                    </c:forEach>


                </form:form>


            </div>

        </div>
    </div>


</div>


<br/><br/><br/><br/><br/>


</body>
</html>