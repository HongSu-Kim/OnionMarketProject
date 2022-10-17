<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Category</title>

    <script>
        function checkboxArr() {
            var checkArr = [];     // 배열 초기화
            $("input[name='selcategory']:checked").each(function (i) {
                checkArr.push($(this).val());
            })

            $.ajax({
                url: 'selcategory'
                , type: 'post'
                , dataType: 'text'
                , data: {
                    categoryArrTest: checkArr
                }
            });
        }

    </script>

</head>

<body>

<h3>카테고리</h3>
관심항목을 선택하세요.
<hr>

<div class="content">
    <form id="selcategory" method="get" action="category.do" class="form-horizontal">
        <button class="btn btn-primary btn-fill btn-wd" id="primaryBtId">Submit</button>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="디지털기기">디지털기기
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="가구/인테리어">가구/인테리어
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="유아동">유아동
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="여성의류">여성의류
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="남성패션/잡화">남성패션/잡화
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="스포츠/레져">스포츠/레져
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="도서">도서
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="가공식품">가공식품
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="식물">식물
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="생활가전">생활가전
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="생활/주방">생활/주방
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="유아도서">유아도서
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="여성잡화">여성잡화
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="뷰티/미용">뷰티/미용
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="취미/게임/음반">취미/게임/음반
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="교환권">티켓/교환권
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="반려동물용품">반려동물용품
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="중고물품">기타 중고물품
        </label>

        <label class="checkbox checkbox-inline">
            <input type="checkbox" name="category" value="삽니다">삽니다
        </label>
    </form>
</div>


</body>

</html>
