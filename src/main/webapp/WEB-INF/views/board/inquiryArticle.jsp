<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<!-- Hero Section Begin -->
<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">
                <div>
                    <h2>문의글</h2><hr/>
                </div>

                <div>
                    <c:if test="${sessionDTO.nickname != null}">
                        <form action="/member/logout" method="post">
                            <button class="btn btn-danger float-end" name="${_csrf.parameterName}" value="${_csrf.token}" type="submit">로그아웃</button>
                        </form></c:if>

                    <c:if test="${sessionDTO.nickname == null}">
                        <button class="btn btn-primary float-end" onclick="location.href='/member/login'" type="button">로그인</button>
                    </c:if>
                </div><br/>



                <div>
                    제목 : ${inquiryDTO.inquirySubject}
                </div><hr/>
                <div>
                    내용 : <span readonly>${inquiryDTO.inquiryContent}</span>
                </div>

                <hr>
                <div>
                    <c:if test="${inquiryDTO.memberId == sessionDTO.id}">
                        <div class="col">
                            <button class="w-100 btn btn-primary btn-lg"
                                    onclick="location.href='/inquiry/update/${inquiryDTO.inquiryId}'" type="button">수정</button>
                        </div>
                        <div class="col">
                            <button class="w-100 btn btn-primary btn-lg"
                                    onclick="location.href='/inquiry/delete/${inquiryDTO.inquiryId}'" type="button">삭제</button>
                        </div>
                    </c:if>
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

                <c:if test="${sessionDTO.role eq 'ADMIN'}">
                    <div>
                        <button type="submit" onclick="location.href='/answer/created/${inquiryDTO.inquiryId}'"
                                class="btn btn-success">답변 작성하기</button><hr/>
                    </div>
                </c:if>

                <div>
                    <c:if test="${inquiryDTO.answer.size() != 0}">
                        <c:forEach items="${answerList}" var="dto">
                            <div>
                                <p>${dto.answerContent}</p>
                                <p>${dto.answerDate}</p>

                                <c:if test="${sessionDTO.role eq 'ADMIN'}">
                                <button type="submit" onclick="location.href='/answer/update/${dto.answerId}'" class="btn btn-primary btn-sm">답변 수정하기</button>
                                <button type="submit" onclick="location.href='/answer/delete/${dto.answerId}'" class="btn btn-danger btn-sm">답변 삭제하기</button><hr/><hr/>
                                </c:if>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>



            </div>
        </div>

    </div>
</section>