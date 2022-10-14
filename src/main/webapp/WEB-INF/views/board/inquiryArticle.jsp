<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<!-- Hero Section Begin -->
<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">
                <div>
                    <h2>문의글</h2><hr/>
                </div>

                <div>
                    제목 : ${inquiryDTO.inquirySubject}
                </div><hr/>
                <div>
                    내용 : <span readonly>${inquiryDTO.inquiryContent}</span>
                </div>

                <hr>
                <div>
                    <div class="col">
                        <button class="w-100 btn btn-primary btn-lg"
                                onclick="location.href='/inquiry/update/${inquiryDTO.inquiryId}'" type="button">수정</button>
                    </div>
                    <div class="col">
                        <button class="w-100 btn btn-primary btn-lg"
                                onclick="location.href='/inquiry/delete/${inquiryDTO.inquiryId}'" type="button">삭제</button>
                    </div>
                    <div class="col">
                        <button class="w-100 btn btn-secondary btn-lg"
                                onclick="location.href='/inquiry/list?field=${param.field}&word=${param.word}&page=${param.page}'" type="button">목록으로</button>
                    </div>
                </div>
            </div> <!-- /container -->
        </div><br/><br/>

        <!-- 답변 -->
        <div class="row">
            <div class="col-lg-12">

<%--                <c:if test="${answerDTO != null || 접속한 회원의 ROLE이 admin일때 }">--%>
<%--                    <div>--%>
<%--                        ${answerDTO.answerContent}--%>
<%--                    </div>--%>
<%--                </c:if>--%>

                <button type="submit" onclick="location.href='/answer/created/${inquiryDTO.inquiryId}'" class="btn btn-success">답변 작성하기</button><hr/>

                <div>
                    <c:if test="${inquiryDTO.answer.size() != 0}">
                        <c:forEach items="${answerList}" var="dto">
                            <div>
                                <p>${dto.answerContent}</p>
                                <p>${dto.answerDate}</p>
                                <button type="submit" onclick="location.href='/answer/update/${dto.answerId}'" class="btn btn-primary btn-sm">답변 수정하기</button>
                                <button type="submit" onclick="location.href='/answer/delete/${dto.answerId}'" class="btn btn-danger btn-sm">답변 삭제하기</button><hr/><hr/>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>



            </div>
        </div>

    </div>
</section>


</body>
</html>
