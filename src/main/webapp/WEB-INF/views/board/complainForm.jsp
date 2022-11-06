<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<section class="hero hero-normal">
    <div class="container" style="width: 700px; margin-top: 200px">
        <div class="row">
            <div class="col-lg-12">
                <div class="card"></div>
                <div class="card">
                    <div class="d-flex">
                        <div class="col-lg-4"><h1 class="title">신고하기</h1></div>
                        <div class="col-lg-7"><div style="float:right;" class="toggle"></div></div>
                    </div>
                    <form:form method="post" action="/complain/created/${productDTO.productId}" id="myForm"
                               modelAttribute="form">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" name="memberId" value="<sec:authentication property="principal.sessionDTO.id"/>">
                        <input type="hidden" name="productId" value="${productDTO.productId}">

                        <div>
                            <div class="input-container">
                                <p class="comInfo">신 고 자 &nbsp;&nbsp;|&nbsp; <sec:authentication property="principal.sessionDTO.userId"/></p>
                                <p class="comInfo">신고대상 |&nbsp; ${targetDTO.userId}</p>
                                <p class="comInfo">상 품 명 &nbsp;&nbsp;&nbsp;|&nbsp; ${productDTO.subject}</p>
                            </div>

                            <div style="margin: 0px 60px 150px 50px">
                                <form:errors path="complainType" cssClass="field-error"/>
                                <form:select path="complainType" id="complainType" class="nice-select wide">
                                    <form:option selected="selected" value="">신고유형을 골라주세요</form:option>
                                    <form:option value="스팸홍보/도배글">스팸홍보/도배글입니다</form:option>
                                    <form:option value="음란물">음란물입니다</form:option>
                                    <form:option value="욕설">욕설과 불쾌한 표현이 있습니다</form:option>
                                    <form:option value="청소년유해">청소년에게 유해한 내용입니다</form:option>
                                    <form:option value="불법정보">불법정보를 포함하고 있습니다</form:option>
                                    <form:option value="개인정보노출">개인정보 노출 게시물입니다</form:option>
                                </form:select>
                            </div>
                        </div>

                        <div class="input-container">
                            <form:errors path="complainContent" cssClass="field-error"/>
                            <form:input type="text" path="complainContent" id="complainContent"
                                        placeholder="신고내용을 작성해주세요"/>
                            <div class="bar"></div>
                        </div>
                        <div class="button-container">
                            <button type="submit" onclick="addComplain();"><span>제출하기</span></button>
                                <%--                            <button><span>취소</span>--%>
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>

