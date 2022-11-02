<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<div class="site-wrap">
    <div class="site-section bg-white">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title related-blog-title">
                    <h2>리뷰 수정하기</h2>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-7 mb-5">

                    <form:form method="post"
                               action="/review/update/${reviewDTO.memberId}/${form == null ? reviewDTO.reviewId : form.reviewId}"
                               enctype="multipart/form-data" id="myform" modelAttribute="form">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input type="hidden" name="reviewId" value="${form == null ? reviewDTO.reviewId : form.reviewId}">

                    <div class="row form-group">
                        <div class="col-md-12">
                            <label class="text-black" style="font-size: 22pt; font-weight: bold"
                                   for="message">Content</label>
                            <textarea name="reviewContent" id="message" cols="30" rows="7" class="form-control" placeholder="내용을 입력하세요">${reviewDTO.reviewContent}</textarea>
                            <form:errors path="reviewContent" cssClass="field-error"/>
                        </div>
                    </div>


                    <!-- 사진 첨부 -->
                    <label class="fileButton" for="reviewImageName"><p id="fileFont">사진/동영상 첨부하기</p></label>
                    <input type="file" id="reviewImageName" name="reviewImageName" multiple="multiple"
                           onchange="setDetailImage(event);" style="display: none"/>
                        <!-- 사진 미리보기 -->
                            <div style="display: flex">
                                <div id="images_container"></div>
                                <c:forEach items="${reviewDTO.reviewImageList}" var="reviewImageDTO">
                                    <div>
                                        <img src="/img/review/${reviewImageDTO.storeImageName}" width="130" height="130"/>
                                        <button type="button" class="site-btn"
                                                style="width: 130px; height: 40px; background-color: #6c757d;"
                                                onclick="location.href='/review/images/delete/${reviewDTO.memberId}/${reviewImageDTO.id}/${reviewDTO.reviewId}'">
                                            삭제
                                        </button>
                                    </div>
                                </c:forEach>
                            </div>
                    <p id="review_span"><span>상품과 무관한 사진/동영상을 첨부한 리뷰는 통보없이 삭제 및 적립 혜택이 회수됩니다.</span></p><br/>

                </div>


                <div class="p-4 mb-3 bg-transparent">
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

                <div class="d-grid gap-2 col-9 mx-auto" align="center">
                    <button type="submit" class="site-btn">수정</button>
                    <button class="site-btn" style="background-color: #6c757d"
                            onclick="location.href='/review/mylist/${reviewDTO.memberId}'" type="button">취소
                    </button>
                </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</div>
