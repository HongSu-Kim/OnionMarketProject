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
    <title>카테고리 생성</title>
<style>

    </style>

</head>
<body>

<div class="container">
    <div class="checkout__form" style="width: 800px; margin: auto">
        <br/><br/><br/>
        <h4>TOP Category<span style="font-size: small;color:#FF5058;margin: 0px 0px 0px 32px">*상위카테고리 입력</span></h4>


                    <div class="row">
                        <div class="col-lg-12">
                            <div class="checkout__input">
                                <p> 상위 카테고리 <span>*</span></p>
                                <form:form action="" method="post" modelAttribute="categoryAddDTO">
                                <input type="text" name="topcategoryName"  placeholder="상위 카테고리 이름을 입력해주세요."/>

                                <input type="hidden" name="categoryName" value=""/>
                                    <form:errors path="categoryName"/>


                                <input type="submit" value="상위카테고리 추가" style="width: 25%; background-color: #47cd65; color: white"/>
                                <br/><br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
</form:form>




<div class="container">
    <div class="checkout__form" style="width: 800px; margin: auto">
        <br/><br/><br/>
        <h4>TOP Category & Sub Category<span style="font-size: small;color:#FF5058;margin: 0px 0px 0px 32px">*상위카테고리 입력 &nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;*하위카테고리 입력</span></h4>

            <div class="row">
                <div class="col-lg-12">
                    <div class="checkout__input">
                        <p> 상위 카테고리 <span>*</span></p>
                        <form:form action="" method="post" modelAttribute="categoryAddDTO">
                            <input type="text" name="topcategoryName" placeholder="상위 카테고리 이름을 입력해주세요."/>
                            <br/>   <br/>
                            <p> 하위 카테고리 <span>*</span></p> <input type="text" name="categoryName"
                                                                  placeholder="하위 카테고리 이름을 입력해주세요."/>
                            <form:errors path="categoryName"/>


                        <input type="submit" value="하위카테고리 추가" style="width: 25%; background-color: #47cd65; color: white"/>
                        <br/><br/>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form:form>




<%--                                -------------------------------------------------------------------------<br/>--%>
<%--                                상위카테고리 이름: <input type="text" name="topcategoryName"/>--%>
<%--                                <input type="hidden" name="categoryName" value=""/>--%>
<%--                                <form:errors path="categoryName"/>--%>
<%--                                <input type="submit" value="상위카테고리 추가"/>--%>
<%--                                <br/><br/>--%>
<%--&lt;%&ndash;                            </form:form>&ndash;%&gt;--%>
<%--                            -------------------------------------------------------------------------<br/>--%>
<%--                            <form:form action="" method="post">--%>
<%--                                상위카테고리 이름: <input type="text" name="topcategoryName"/><br/>--%>
<%--                                하위카테고리 이름: <input type="text" name="categoryName"/>--%>


<%--                                <input type="submit" value="하위카테고리 추가"/><br/>--%>

<%--                                ---------------------------------------------------------------------------%>
<%--                            </form:form>--%>

<%--                            <form:form action="categoryupdate" method="get">--%>
<%--                               카테고리 수정하기 <input type="submit" value="수정">--%>
<%--                            </form:form>--%>

<%--                            <strong>현재 상위카데고리</strong>--%>
<%--                            <c:forEach var="category" items="${category}">--%>

<%--                                [${category.categoryName}]--%>

<%--                            </c:forEach>--%>




<br/><br/><br/><br/>

</body>
</html>