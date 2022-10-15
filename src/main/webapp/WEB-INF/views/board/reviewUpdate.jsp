<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 수정하기</title>
    <style>
        .field-error{
            border-color: #f07682;
            color: #dc3545;
            font-weight: bold;
        }
    </style>
</head>
<body id="review_body">

<form:form method="post" action="/review/update/${reviewDTO.reviewId}" enctype="multipart/form-data" id="myform" modelAttribute="form">

    <input type="hidden" name="reviewId" value="${reviewDTO.reviewId}">

    <div align="center">
        <fieldset>
            <legend class="text-bold">별점을 선택해주세요</legend>
            <input type="radio" name="grade" value="5" id="rate1">
            <label for="rate1">★</label>
            <input type="radio" name="grade" value="4" id="rate2">
            <label for="rate2">★</label>
            <input type="radio" name="grade" value="3" id="rate3">
            <label for="rate3">★</label>
            <input type="radio" name="grade" value="2" id="rate4">
            <label for="rate4">★</label>
            <input type="radio" name="grade" value="1" id="rate5">
            <label for="rate5">★</label>
            <form:errors path="grade" cssClass="field-error"/>
        </fieldset>
    </div><hr/>


    <!-- 사진 첨부 -->
    <label class="fileButton" for="reviewImg"><p id="fileFont">사진/동영상 첨부하기</p></label>
    <input type="file" id="reviewImg" name="reviewImageName" multiple="multiple" style="display: none"/>
    <p id="review_span"><span>상품과 무관한 사진/동영상을 첨부한 리뷰는 통보없이 삭제 및 적립 혜택이 회수됩니다.</span></p><br/>

    <span >* 상세 리뷰를 작성해주세요.</span><br/>
    <div align="center">
            <textarea maxlength="40" rows="1" id="review_textarea" type="text" name="reviewContent"
                      placeholder="${reviewDTO.reviewContent}">
            </textarea>
    </div>
    <form:errors path="reviewContent" cssClass="field-error"/><br/><br/>

    <div class="d-grid gap-2 col-9 mx-auto">
        <button type="submit" class="btn btn-success">등록</button>
        <button class="w-100 btn btn-secondary btn-lg"
                onclick="location.href='/'" type="button">취소</button>
    </div>
</form:form>

</body>
</html>