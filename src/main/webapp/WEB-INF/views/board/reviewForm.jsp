<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 작성 페이지</title>
    <style>
        .field-error{
            border-color: #f07682;
            color: #dc3545;
            font-weight: bold;
        }
    </style>
</head>
<body id="review_body">

<div align="center">
    <h1>후기를 남겨주세요</h1>
</div><br/>

<table id="review_table">
    <thead><th colspan="2"><h4 id="review_table h4">상품명</h4></th></thead>
    <tbody>
    <tr>
        <td>상품 이미지</td>
        <%-- <td><img src="/productImage/${productImageDTO.productImageName}"/></td> --%>
        <td>상품 상세 정보
            <div>
<%--                상품명 :${productDTO.productName}--%>
<%--                제목 :${productDTO.subject}--%>
<%--                가격 :${productDTO.price}--%>
            </div>
        </td>

    </tr>
    </tbody>
</table><hr/>

<form:form method="post" action="/review/created/${orderDTO.orderId}" enctype="multipart/form-data" id="myform" modelAttribute="reviewFormDTO">

    <input type="hidden" name="memberId" value="${memberDTO.id}">
    <input type="hidden" name="orderId" value="${orderDTO.orderId}">
    <input type="hidden" name="salesId" value="${memberDTO.id}">


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
        </fieldset>
    </div><hr/>

    <div id="review_check">
        <div>님을 평가해주세요.</div><br/>

        <span id="review_span_2">* 해당하는 항목을 선택해주세요. (최대 3개)</span>
        <ul class="list-group">
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                1. 친절하고 매너가 좋아요
            </li>
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                2. 시간 약속을 잘 지켜요
            </li>
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                3. 제가 있는 곳까지 와서 거래했어요
            </li>
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                4. 상품 설명이 자세해요.
            </li>
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                5. 좋은 상품을 저렴하게 판매해요.
            </li>
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                6. 상품 상태가 설명한 것과 같아요.
            </li>
        </ul>
    </div><br/>

    <div id="review_check">
        <span>* 해당하는 비매너를 선택해주세요</span>
        <ul class="list-group">
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                1. 반말을 사용하고 예의가 없어요
            </li>
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                2. 시간약속을 안지켜요
            </li>
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
                3. 불친절해요
            </li>
        </ul>
    </div><br/><hr/>

    <!-- 사진 첨부 -->
    <label class="fileButton" for="reviewImg"><p id="fileFont">사진/동영상 첨부하기</p></label>
    <input type="file" id="reviewImg" name="reviewImageName" multiple="multiple" style="display: none"/>
    <p id="review_span"><span>상품과 무관한 사진/동영상을 첨부한 리뷰는 통보없이 삭제 및 적립 혜택이 회수됩니다.</span></p><br/>

    <span >* 상세 리뷰를 작성해주세요.</span><br/>
    <div align="center">
            <textarea maxlength="40" rows="1" id="review_textarea" type="text" name="reviewContent"
                      placeholder="후기를 남겨주세요. 포토 후기를 남기면 200포인트 지급!">
            </textarea>
    </div>
    <form:errors path="reviewContent" cssClass="field-error"/><br/><br/>

    <div class="d-grid gap-2 col-9 mx-auto">
        <button type="submit" class="btn btn-success">등록</button>
        <button class="w-100 btn btn-secondary btn-lg"
                onclick="location.href='/review/list'" type="button">취소</button>
    </div>
</form:form>


</body>
</html>