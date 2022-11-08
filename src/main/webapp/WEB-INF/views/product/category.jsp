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
        <br/><br/><br/><br/><br/><br/><br/>

        <h4>상위 카테고리<span style="font-size: small;color:#FF5058;margin: 0px 0px 0px 32px">*상위카테고리 입력 </span></h4>
        <div style="text-align: right ">
            <form:form action="categoryupdate" method="get">
            <input type="submit" value="카테고리 조회" onclick="select();"
                   style="font-size: 16px; width: 15%; height: 43px; margin-top: -30px;
                 border-style: solid ; border-color: #90C8AC; background-color: #90C8AC; color: white"/>
        </div>
        </form:form>

        <div class="row">
            <div class="col-lg-12">
                <div class="checkout__input">
                    <p> 상위 카테고리 <span>*</span></p>
                    <form:form action="" method="post">
                    <input type="text" name="categoryName" placeholder="상위 카테고리 이름을 입력해주세요."/>

                    <input type="hidden" name="subCategoryName"/>



                    <input type="submit" value="상위카테고리 추가"
                           style="width: 25%; background-color: #90C8AC;; color: white"/>


                    <br/><br/>
                </div>
            </div>
        </div>
    </div>
</div>
</form:form>


<div class="container">
    <div class="checkout__form" style="width: 800px; margin: auto">
        <br/>
        <h4> 상위 카테고리 & 하위 카테고리<span style="font-size: small;color:#FF5058;margin: 0px 0px 0px 32px">*상위카테고리 선택 필수 &nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;*하위카테고리 선택 필수</span></h4>

        <div class="row">
            <div class="col-lg-12">
                <div class="checkout__input">

                    <form:form action="" name="isc" onsubmit="return check_submit();" method="post">
                        <%--                            <input type="text" name="TopName" id="TopName" style="width: 15%;">--%>
                    <div class="checkout__input">
                        <p>상위카테고리 선택<span>*</span></p>
                        <select id="topCategoryName" name="categoryName" size="1">
                            <option>선택하세요</option>
                            <c:forEach var="topCategory" items="${topCategory}">
                                <option value="${topCategory.categoryName}"
                                        onclick="statusChange();">${topCategory.categoryName}</option>

                            </c:forEach>
                        </select>
                        <br/><br/><br/>
                        <p> 하위 카테고리 <span>*</span></p> <input type="text" name="subCategoryName"
                                                              placeholder="하위 카테고리 이름을 입력해주세요."/>



                        <input type="submit" value="하위카테고리 추가" onclick="select();"
                               style="width: 25%; background-color: #90C8AC;; color: white"/>
                        <br/><br/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </form:form>


    <br/><br/><br/><br/>

</body>
</html>