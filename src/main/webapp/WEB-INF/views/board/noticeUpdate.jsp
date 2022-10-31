<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<!-- Contact Form Begin -->
<section class="contact-form spad">
    <form:form method="post" action="/notice/update/${noticeDTO.noticeId}" enctype="multipart/form-data"
               modelAttribute="form">
        <div class="container">
            <div class="row">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="memberId" value="${memberDTO.id}">

                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>공지사항 수정</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <form:errors path="noticeSubject" cssStyle="font-weight: bold; color: #e95050"/>
                </div>
                <div class="col-lg-6 col-md-6">
                    <form:errors path="noticeType" cssClass="field-error"
                                 cssStyle="font-weight: bold; color: #e95050"/></div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <input type="text" id="noticeSubject" name="noticeSubject"
                           value="${form.noticeSubject == null ? noticeDTO.noticeSubject : form.noticeSubject}"
                           class="form-control" style="height: 42px">
                </div>

                <div class="col-lg-6">
                    <form:select id="noticeType" path="noticeType" name="noticeType" cssClass="nice-select wide">
                        <form:option selected="selected"
                                     value="${noticeDTO.noticeType}">${noticeDTO.noticeType}</form:option>
                        <form:option value="NOTICE">NOTICE</form:option>
                        <form:option value="QNA">QNA</form:option>
                    </form:select>
                </div>

                <form:errors path="noticeContent" cssClass="field-error"
                             cssStyle="margin-left: 15px; font-weight: bold; color: #e95050"/>
                <div class="col-lg-12 text-center">
                    <textarea name="noticeContent"
                              style="height: 300px">${form.noticeContent == null ? noticeDTO.noticeContent : form.noticeContent}</textarea>
                </div>

                <div style="margin-left: 15px">
                    <div class="shoping__cart__btns">
                        <div>
                            <label class="primary-btn cart-btn" for="noticeImage"
                                   style="height: 50px; margin-left: 5px"><p
                                    style="line-height: 20px; width: 80px; font-weight: bold">사진 첨부</p></label>
                            <input type="file" id="noticeImage" name="noticeImageName" multiple="multiple"
                                   class="upload-hidden" style="display: none;" onchange="setDetailImage(event);"/>
                        </div>

                        <div id="images_container" style="margin-bottom: 10px"></div>

                        <div class="d-flex" style="margin-left: 10px">
                            <c:forEach items="${noticeDTO.noticeImageList}" var="noticeImageDTO">
                                <div>
                                    <img src="/img/notice/${noticeImageDTO.noticeImageName}" width="130" height="130"/><br/>
                                    <input type="button" style="width: 130px"
                                           onclick="location.href='/notice/image/delete/${noticeImageDTO.id}/${noticeDTO.noticeId}'"
                                           value="삭제"><br/></div>
                            </c:forEach>
                        </div>
                    </div>
                </div>


                <div class="col-lg-12 text-center">
                    <button type="submit" class="site-btn">수정하기</button>
                    <button type="button" class="site-btn" onclick="location.href='/notice/list'"
                            style="background-color: #b2b2b2">취소
                    </button>
                </div>

            </div>
        </div>
    </form:form>
</section>
<!-- Contact Form End -->

