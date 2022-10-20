<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">
            <div class="container">
                <c:choose>
                    <c:when test="${answerDTO == null}">
                        <form:form method="post" action="/answer/created/${inquiryDTO.inquiryId}" modelAttribute="answerFormDTO">
                            <input type="hidden" name="memberId" value="${sessionDTO.id}">
                            <input type="hidden" name="inquiryId" value="${inquiryDTO.inquiryId}">

                            <h4>답변 작성하기</h4>
                            <textarea name="answerContent" rows="8" cols="90" placeholder=""></textarea>
                            <form:errors path="answerContent"/>
                            <br/>
                            ${answerDTO == null ? '<input type="submit" value="등록하기">' : '<input type="submit" value="수정하기">'}
                        </form:form>
                    </c:when>

                    <c:otherwise>
                        <form:form method="post" action="/answer/update/${answerDTO.answerId}" modelAttribute="answerFormDTO">
                            <input type="hidden" name="memberId" value="${sessionDTO.id}">
                            <input type="hidden" name="inquiryId" value="${answerDTO.answerId}">

                            <h4>답변 수정하기</h4>
                            <textarea name="answerContent" rows="8" cols="90" placeholder=""></textarea>
                            <form:errors path="answerContent"/>
                            <br/>
                            ${answerDTO == null ? '<input type="submit" value="등록하기">' : '<input type="submit" value="수정하기">'}
                        </form:form>
                    </c:otherwise>
                </c:choose>

            </div>
            </div>
        </div>
    </div>
</section>