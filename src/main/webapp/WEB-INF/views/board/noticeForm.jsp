<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<!-- Contact Form Begin -->
<div class="contact-form spad">
    <form:form method="post" action="/notice/created" enctype="multipart/form-data" modelAttribute="form">
        <div class="container">
            <div class="row">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="memberId" value="${memberDTO.id}">

                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>공지사항 작성</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <form:errors path="noticeSubject" cssStyle="font-weight: bold; color: #e95050" />
                </div>
                <div class="col-lg-6 col-md-6">
                    <form:errors path="noticeType" cssClass="field-error" cssStyle="font-weight: bold; color: #e95050"/></div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <form:input type="text" path="noticeSubject" placeholder="제목을 입력해주세요" cssStyle="height: 42px"/>
                </div>

                <div class="col-lg-6">
                    <form:select id="noticeType" path="noticeType" name="noticeType" cssClass="nice-select wide">
                        <form:option selected="selected" value="">유형을 선택해주세요</form:option>
                        <form:option value="NOTICE">NOTICE</form:option>
                        <form:option value="QNA">QNA</form:option>
                    </form:select>
                </div>

                <form:errors path="noticeContent" cssClass="field-error" cssStyle="margin-left: 15px; font-weight: bold; color: #e95050" />
                <div class="col-lg-12 text-center">
                    <form:textarea path="noticeContent" style="height: 400px"
                                   placeholder="공지사항을 작성해주세요"></form:textarea>
                </div>

                <div class="d-inline-flex" style="margin-left: 20px">
                    <div style="margin-left: 15px; display: inline">
                        <div style="display: inline;">
                            <label class="primary-btn cart-btn" for="noticeImageName">사진 첨부하기</label>
                            <form:input type="file" id="noticeImageName" path="noticeImageName" multiple="multiple" style="display: none"/>
                            <div id="preview"></div>
                        </div>
                    </div>
                </div>


                <div class="col-lg-12 text-center">
                    <button type="submit" class="site-btn">등록하기</button>
                    <button type="button" class="site-btn" onclick="location.href='/notice/list'"
                            style="background-color: #b2b2b2">취소
                    </button>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- Contact Form End -->
<style>
    li{list-style: none;}
</style>
