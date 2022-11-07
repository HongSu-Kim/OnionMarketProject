<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<section class="contact-form spad">

    <form:form method="post" action="/notice/update/${noticeDTO.noticeId}" enctype="multipart/form-data"
               modelAttribute="form">
        <div class="container">
            <div class="row">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" id="memberId" name="memberId" value="${memberDTO.id}">

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

                <div>
                    <div class="d-flex" style="margin-left: 20px; margin-bottom: 20px">
                        <c:forEach items="${noticeDTO.noticeImageList}" var="noticeImageDTO">
                            <div style="margin-right: 10px;">
                                <img src="/img/notice/${noticeImageDTO.noticeImageName}" width="150" height="120"
                                     style="border-top-left-radius: 15px; border-top-right-radius: 15px"/><br/>
                                <button type="button"
                                        style="width: 150px; border: none; border-bottom-left-radius: 15px; border-bottom-right-radius:15px "
                                        onclick="location.href='/notice/image/delete/${noticeImageDTO.id}/${noticeDTO.noticeId}'">
                                    삭제
                                </button>
                                <br/>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="d-inline-flex" style="margin-left: 20px">
                        <div style="margin-left: 15px; display: inline">
                            <div style="display: inline;">
                                <p><em style="color: #5a6268">* 사진을 첨부해주세요</em></p>

                                <label class="primary-btn cart-btn" for="noticeImageName">사진 첨부하기</label>
                                <input type="file" id="noticeImageName" name="noticeImageName" multiple
                                       style="display: none" onchange="fileInfo(this)"/>
                                <div id="preview"></div>
                            </div>
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
<style>
    li{list-style: none;}
</style>

