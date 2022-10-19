<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Category</title>
<%--
    <script type="text/javascript">
        $(document).ready(function () {
            $("#defaultBtId0").click(function () {
                $("#frmReq").submit();
            });
            if ($("#category").val() != "") {
                <c:forEach items="${category}" var="category" varStatus="status">
                    alert('<c:out value="${category}"/>');
                </c:forEach>
            }
        });
    </script>
    --%>
</head>

<body>

<h3>카테고리</h3>
관심항목을 선택하세요.
<hr>

<div class="content">
<%--    <form id="frmReq" method="get" action="welcomeWeb4.do" class="form-horizontal">
        <input type="hidden" id="category" name="category" value="${category[0]}"/>
        <button class="btn btn-primary btn-fill btn-wd" id="primaryBtId">Submit</button>
        <hr/>--%>
    <form action="category.do" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="checkbox" name="category" value="디지털기기"/>디지털기기
        <br/>
        <input type="checkbox" name="category" value="가구/인테리어"/>가구/인테리어
        <br/>
        <input type="checkbox" name="category" value="유아동"/>유아동
        <br/>
        <input type="checkbox" name="category" value="여성의류"/>여성의류
        <br/>
        <input type="checkbox" name="category" value="남성패션/잡화"/>남성패션/잡화
        <br/>
        <input type="checkbox" name="category" value="스포츠/레저"/>스포츠/레저
        <br/>
        <input type="checkbox" name="category" value="도서"/>도서
        <br/>
        <input type="checkbox" name="category" value="가공식품"/>가공식품
        <br/>
        <input type="checkbox" name="category" value="식물"/>식물
        <br/>
        <input type="checkbox" name="category" value="생활/가전"/>생활/가전
        <br/>
        <input type="checkbox" name="category" value="생활/주방"/>생활/주방
        <br/>
        <input type="checkbox" name="category" value="유아도서"/>유아도서
        <br/>
        <input type="checkbox" name="category" value="여성잡화"/>여성잡화
        <br/>
        <input type="checkbox" name="category" value="뷰티/미용"/>뷰티/미용
        <br/>
        <input type="checkbox" name="category" value="취미/게임/음반"/>취미/게임/음반
        <br/>
        <input type="checkbox" name="category" value="티켓/교환권"/>티켓/교환권
        <br/>
        <input type="checkbox" name="category" value="반려동물용품"/>반려동물용품
        <br/>
        <input type="checkbox" name="category" value="기타 중고물품"/>기타 중고물품
        <br/>
        <input type="checkbox" name="category" value="삽니다"/>삽니다
        <br/>
        <input type="submit" value="저장"/>
    </form>
</div>

</body>

</html>
