<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>
<style>
    .field-error {
        border-color: #f07682;
        color: #dc3545;
        font-weight: bold;
    }

    #myform fieldset {
        display: inline-block;
        direction: rtl;
        border: 0px;
    }

    #myform fieldset legend {
        text-align: center;
        font-weight: bold;
    }

    #myform input[type=radio] {
        display: none;
    }

    #myform label {
        font-size: 3em;
        color: transparent;
        text-shadow: 0 0 0 #f0f0f0;
    }

    #myform label:hover {
        text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
    }

    #myform label:hover ~ label {
        text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
    }

    #myform input[type=radio]:checked ~ label {
        text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
    }

    #review_textarea {
        margin: auto;
        width: 900px;
        height: 300px;
    }
</style>

<section class="hero hero-normal">

    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">
                <form:form method="post" action="/review/update/${form == null ? reviewDTO.reviewId : form.reviewId}"
                           enctype="multipart/form-data" id="myform" modelAttribute="form">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input type="hidden" name="reviewId" value="${form == null ? reviewDTO.reviewId : form.reviewId}">

                    <div class="section-title related-blog-title">
                        <h2>리뷰 수정하기</h2>
                    </div>

                    <div class="p-4 mb-3 bg-white">
                        <div align="center" id="myform">
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
                            <br/>
                            <form:errors path="grade" cssClass="field-error"/>
                        </div>
                        <hr/>
                    </div>


                    <%-- 첨부사진 개별 삭제 --%>
                    <div style="display: flex">
                        <c:forEach items="${reviewDTO.reviewImageList}" var="reviewImageDTO">
                            <div>
                                <img src="/img/review/${reviewImageDTO.storeImageName}" width="200" height="200"/><br/>
                                <button type="button" class="site-btn"
                                        style="width: 200px; height: 40px; background-color: #6c757d;"
                                        onclick="location.href='/review/images/delete/${reviewImageDTO.id}/${reviewDTO.reviewId}'">
                                    삭제
                                </button>
                            </div>
                        </c:forEach>
                    </div>
                    <br/>

                    <!-- 사진 첨부 -->
                    <input type="file" id="reviewImg" name="reviewImageName" multiple="multiple"/>
                    <p id="review_span"><span>상품과 무관한 사진/동영상을 첨부한 리뷰는 통보없이 삭제 및 적립 혜택이 회수됩니다.</span></p><br/>

                    <div align="center">
                        <div>* 작성한 내용을 수정해주세요</div>
                        <textarea maxlength="40" rows="1" id="review_textarea" type="text" name="reviewContent"
                                  placeholder="${reviewDTO.reviewContent}"></textarea>
                        <br/><form:errors path="reviewContent" cssClass="field-error"/>
                    </div><br/>

                    <div class="d-grid gap-2 col-9 mx-auto" align="center">
                        <button type="submit" class="site-btn">등록</button>
                        <button class="site-btn" style="background-color: #6c757d"
                                onclick="location.href='/review/list'" type="button">취소
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>