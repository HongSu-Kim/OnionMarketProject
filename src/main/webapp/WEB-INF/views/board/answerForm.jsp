<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<style>
    .n-section-title {
        border-bottom: 3px solid #47CD65;
        padding-bottom: 10px;
        margin-top: 200px;
        line-height: 1.5;
        font-size: 20px;
        position: relative;
    }

    .n-section-title .tit {
        display: inline-block;
        font-size: 20pt;
        font-weight: bolder;
    }
    .field-error{
        border-color: #f07682;
        color: #dc3545;
        font-weight: bold;
    }

    .inquiryArticle > p {
        margin-top: 10px;
        font-weight: bold;
        font-size: 17pt;
        color: black;
    }
    .textArea {
        border: 2px solid #47CD65;
        margin-left: 10px;
    }

</style>

<section class="hero hero-normal" style="margin-top: 250px">
    <div class="container" style="width: 800px;">
        <div class="qna-container" style="margin-bottom: 30px">
            <div class="n-section-title">
                <c:if test="${answerDTO == null}">
                    <h1 class="tit">답변 작성하기</h1>
                </c:if>
                <c:if test="${answerDTO != null}">
                    <h1 class="tit">답변 수정하기</h1>
                </c:if>
            </div>
            <br/>

            <div class="textborder">
                <c:choose>
                    <c:when test="${answerDTO == null}">
                        <form:form method="post" action="/answer/created/${inquiryDTO.inquiryId}" modelAttribute="answerFormDTO">
                            <input type="hidden" name="memberId" value="${memberDTO.id}">
                            <input type="hidden" name="inquiryId" value="${inquiryDTO.inquiryId}">

                            <textarea name="answerContent" class="textArea" rows="8" cols="100"
                                      placeholder="">${answerDTO.answerContent}</textarea>
                            <span style="margin-left: 10px"><form:errors path="answerContent" cssClass="field-error"/></span>
                            <input type="submit" class="site-btn" style="float: right; margin-right: 10px" value="등록하기">
                            <br/>
                        </form:form>
                    </c:when>

                    <c:otherwise>
                        <form:form method="post" action="/answer/update/${answerDTO.answerId}"
                                   modelAttribute="answerFormDTO">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" name="memberId" value="${memberDTO.id}">
                            <input type="hidden" name="inquiryId" value="${answerDTO.answerId}">

                            <textarea name="answerContent" class="textArea" rows="8" cols="100"
                                     placeholder="">${answerDTO.answerContent}</textarea>
                            <span style="margin-left: 10px"><form:errors path="answerContent" cssClass="field-error"/></span>
                            <input type="submit" class="site-btn" style="float: right; margin-right: 10px" value="수정하기">
                            <br/>
                        </form:form>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</section>