<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<section class="hero hero-normal">
    <div class="container" style="width: 800px;">
        <div class="qna-container">
            <div class="n-section-title">
                <h1 class="tit">작성한 문의글</h1>
            </div>
            <br/>

            <div>
                <div class="inquiryArticle">
                    <p>문의내용</p>
                </div>
                <!-- 질문영역 -->
                <div class="qna-question">

                    <!-- 회원정보시작 -->
                    <div class="qna-memberinfo">
                        <div class="profile-image">
                            <img src="/template/img/product/product-2.jpg" width="100px;" height="100px" alt="프로필사진"/>
                            <%-- /${inquiryDTO.memberDTO.memberImageName} --%>
                        </div>
                        <!-- 아이디 -->
                        <div class="qna-memberId">
                            <span>${inquiryDTO.memberDTO.userId}</span>
                        </div>
                    </div>
                    <!-- 회원정보끝 -->

                    <!-- 질문영역 -->
                    <div class="qna-answer">
                        <div>[ ${inquiryDTO.inquirySubject} ]</div>
                        ${inquiryDTO.inquiryContent}


                        <div style="float: right; margin-right: 20px">
                            <c:if test="${memberDTO.id == inquiryDTO.memberDTO.id}">
                                <button type="submit" onclick="location.href='/inquiry/update/${inquiryDTO.inquiryId}'"
                                        class="adminbutton">수정
                                </button>
                                <button type="submit" onclick="location.href='/inquiry/delete/${inquiryDTO.inquiryId}'"
                                        class="adminbutton" style="background-color: #7e828f">삭제
                                </button>
                            </c:if>
                        </div>
                    </div>
                    <hr/>

                    <!-- 작성일, 답글달기버튼 -->
                    <div class="qna-dateline">
                        <div class="qna-dateline-left">
                            <span class="qna-date">
                                ${inquiryDTO.inquiryDate}
                            </span>

                            <span>
                                <c:if test="${memberDTO.role eq 'ADMIN'}">
                                    <div style="float:right;">
                                        <button type="submit" onclick="location.href='/answer/created/${inquiryDTO.inquiryId}'"
                                                class="site-btn-answer">답변 작성
                                        </button>
                                    </div>
                                </c:if>
                            </span>
                        </div>



                    </div>
                </div>
                <!-- 질문영역끝 -->

                <!-- 답변영역 -->
                <div>
                    <div style="text-align: center">
                        <c:if test="${inquiryDTO.answer.size() == 0}"><span>등록된 답변이 없습니다.</span></c:if>
                    </div>
                    <div style="width: 700px">
                        <c:if test="${inquiryDTO.answer.size() != 0}">
                            <c:forEach items="${inquiryDTO.answer}" var="dto">

                                <div class="qna-answer-container">
                                    <div class="qna-answer-in">

                                        <div class="qna-answer-header">
                                            <div class="qna-answer-admin">
                                                <span>양파마켓 ${dto.memberDTO.name}</span>

                                                <div style="float: right; margin-right: 20px">
                                                    <c:if test="${memberDTO.role == 'ADMIN'}">
                                                        <button type="submit" onclick="location.href='/answer/update/${dto.answerId}'"
                                                                class="adminbutton">수정
                                                        </button>
                                                        <button type="submit" onclick="location.href='/answer/delete/${dto.answerId}'"
                                                                class="adminbutton" style="background-color: #7e828f">삭제
                                                        </button>
                                                    </c:if>
                                                </div>
                                            </div>



                                        </div>

                                        <div class="qna-answer-content">
                                                ${dto.answerContent}
                                        </div>

                                        <div class="qna-answer-dateline">
                                        <span class="answer-date-left">
                                            <span class="answer-date">
                                                <fmt:parseDate value="${dto.answerDate}" var="dateFmt" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
                                                <fmt:formatDate value="${dateFmt}" pattern="YYYY-MM-dd HH:mm"/>
                                            </span>
                                        </span>
                                        </div>
                                    </div>


                                </div>
                                <!-- 답변영역끝 -->
                            </c:forEach>
                        </c:if>
                    </div>
                </div>


            </div>
        </div>
    </div>
</section>