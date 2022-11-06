<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .field-error {
        border-color: #f07682;
        color: #dc3545;
        font-weight: bold;
    }
    .detailSelect {
        display: none;
    }
    #secretBox {
        zoom: 1;
        width: 20px;
        height: 20px;
    }

    .inquiryline {
        border-bottom: 2px solid #47CD65;
    }
</style>

<!-- Contact Form Begin -->
<section class="contact-form spad">
    <div class="container" style="width: 1100px">
        <div class="row">
            <div class="col-lg-12">
                <div class="contact__form__title">
                    <h2>문의 내용 수정하기</h2>
                </div>
            </div>
        </div>

        <form:form method="post" action="/inquiry/update/${inquiryDTO.inquiryId}" id="inquiryForm"
                   modelAttribute="form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" name="memberId" value="${memberDTO.id}">

            <!-- 회원정보 -->
            <div class="row">
                <div class="col-lg-4">
                    ID <input type="text" readonly="readonly" value="${memberDTO.userId}"
                              placeholder="${memberDTO.userId}">
                </div>
                <div class="col-lg-4">
                    NAME <input type="text" readonly="readonly" value="${memberDTO.name}"
                                placeholder="${memberDTO.name}">
                </div>
                <div class="col-lg-4">
                    EMAIL <input type="text" readonly="readonly" value="${memberDTO.email}"
                                 placeholder="${memberDTO.email}">
                </div>
            </div>
            <hr/>

            <div class="row">
                <h4>*<span class="inquiryline" style="margin-left: 10px"> 문의 정보</span></h4><br/><br/>

                <div class="col-lg-12" style="margin-bottom: 20px; margin-left: 16px">
                    <!-- 비밀글 체크 -->
                    <div class="form-check" style="display: flex">
                        <input class="form-check-input" type="checkbox" name="secret" id="secretBox" placeholder="
                                ${inquiryDTO.secret == true ? '<input class="form-check-input" type="checkbox" checked="checked" name="secret" id="secretBox"/>' : ' '}
                        <div style=" margin-left: 5px; margin-top: 3px">&nbsp; 비밀글 설정
                    </div>
                </div>
            </div>

            <div class="col-lg-12">
                <div>
                    <label for="inquiryType">문의 유형</label><br/>
                    <input type="hidden" id="dtoType" value="${inquiryDTO.inquiryType}">
                    <form:select id="inquiryType" path="inquiryType" onchange="selectType(this.value);" style="width: 400px;">
                        <form:option selected="selected" value="${inquiryDTO.inquiryType}">${inquiryDTO.inquiryType}</form:option>
                        <form:option value="회원정보">회원정보/계정</form:option>
                        <form:option value="거래">거래</form:option>
                        <form:option value="기타서비스">기타 서비스</form:option>
                    </form:select>
                </div><br/><br/>

                <div>
                    <form:select id="detailType" class="nice-select" path="detailType">
                        <c:if test="${form.detailType != null}">
                            <form:option value="${form.detailType}">${form.detailType}</form:option>
                        </c:if>
                        <form:option id="dtoV" value="${inquiryDTO.detailType}">${inquiryDTO.detailType}</form:option>
                    </form:select>
                </div>
            </div><br/><br/>
            <br/><br/>


            <div class="col-lg-12">
                <div>문의제목 &nbsp;<form:errors path="inquirySubject" cssClass="field-error"/></div>
                <input type="text" id="inquirySubject" name="inquirySubject"
                       value="${form.inquirySubject == null ? inquiryDTO.inquirySubject : form.inquirySubject}"
                       class="form-control">
            </div>

            <div class="col-lg-12 text-center">
                <div class="text-left">문의내용 &nbsp;<form:errors path="inquiryContent" cssClass="field-error"/></div>
                <div>
                    <textarea
                            name="inquiryContent">${form.inquiryContent == null ? inquiryDTO.inquiryContent : form.inquiryContent}</textarea>
                </div>
                <br/>

                <button type="submit" class="site-btn">FINISH</button>
                <button class="site-btn" style="background-color: #5a6268"
                        onclick="location.href='/inquiry/article/${inquiryDTO.inquiryId}'" type="button">CANCEL
                </button>
            </div>
        </form:form>
    </div>
</section>
<!-- /container -->
