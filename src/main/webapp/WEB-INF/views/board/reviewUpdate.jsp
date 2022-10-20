<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
    <style>
        .field-error{
            border-color: #f07682;
            color: #dc3545;
            font-weight: bold;
        }
        #myform fieldset{
            display: inline-block;
            direction: rtl;
            border:0px;
        }
        #myform fieldset legend{
            text-align: center;
            font-weight: bold;
        }
        #myform input[type=radio]{
            display: none;
        }
        #myform label{
            font-size: 3em;
            color: transparent;
            text-shadow: 0 0 0 #f0f0f0;
        }
        #myform label:hover{
            text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
        }
        #myform label:hover ~ label{
            text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
        }
        #myform input[type=radio]:checked ~ label{
            text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
        }
        #review_textarea{
            margin: auto;
            width: 900px;
            height: 300px;
        }
    </style>

<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">

        <div class="row">
            <div class="col-lg-12">

            <form:form method="post" action="/review/update/${form == null ? reviewDTO.reviewId : form.reviewId}" enctype="multipart/form-data" id="myform" modelAttribute="form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="reviewId" value="${form == null ? reviewDTO.reviewId : form.reviewId}">

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
                    <form:errors path="grade" cssClass="field-error"/>
                </div><hr/>


                <%-- 첨부사진 개별 삭제 --%>
                <div style="display: flex">
                <c:forEach items="${imageList}" var="reviewImageDTO">
                    <div>
                    <img src="/img/review/${reviewImageDTO.storeImageName}" width="200" height="200"/><br/>
                        <div align="right">
                        <button type="button" onclick="location.href='/review/images/delete/${reviewImageDTO.id}/${reviewDTO.reviewId}'" style="width: 80px">삭제</button>
                        </div>
                    </div>
                </c:forEach>
                </div><br/>

                <!-- 사진 첨부 -->
                <label class="fileButton" for="reviewImg"><p id="fileFont">사진/동영상 첨부하기</p></label>
                <input type="file" id="reviewImg" name="reviewImageName" multiple="multiple" style="display: none"/>
                <p id="review_span"><span>상품과 무관한 사진/동영상을 첨부한 리뷰는 통보없이 삭제 및 적립 혜택이 회수됩니다.</span></p><br/>

                <div align="center">
                    <span >* 상세 리뷰를 작성해주세요.</span><br/>
                    <textarea maxlength="40" rows="1" id="review_textarea" type="text" name="reviewContent" placeholder="${reviewDTO.reviewContent}"></textarea>
                    <br/><form:errors path="reviewContent" cssClass="field-error"/>
                </div><br/><br/>

                <div class="d-grid gap-2 col-9 mx-auto" align="center">
                    <button type="submit" class="btn btn-success btn-lg">등록</button>
                    <button class="btn btn-secondary btn-lg"
                            onclick="location.href='/review/list'" type="button">취소</button>
                </div>
            </form:form>
            </div>
        </div>
    </div>
</section>