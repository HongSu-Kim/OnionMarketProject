<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<section class="shoping-cart spad">
    <div class="container" style="width: 1000px; margin-top: 20px">
        <div class="section-title">
            <h3>1:1 문의 게시판</h3>
        </div>
        <!-- 검색 -->
        <form action="/inquiry/list" class="d-flex" method="GET" style="margin-top: 10px; margin-bottom: 5px">
            <div class="col-lg-8">
                <select name="field" style="width: 130px;">
                    <option selected="selected" value="all">전체</option>
                    <option value="name">작성자</option>
                    <option value="회원정보">[문의유형]회원정보</option>
                    <option value="거래">[문의유형]거래</option>
                    <option value="기타서비스">[문의유형]기타서비스</option>
                </select>

                <div style="height: 45px">
                    <input type="text" name="word" class="searchIn" placeholder="검색할 단어를 입력하세요">
                    <button class="site-btn" type="submit" style="height: 43px;">
                        <span class="icon_search"></span></button>
                </div>
            </div>

            <div class="col-lg-4">
                <div style="text-align: center; float: right">
                    <a href="/inquiry/created" class="site-btn">1:1 문의하기</a>
                </div>
            </div>
        </form>
        <hr style="background-color: #47cd65; height: 1px"/>
        <!-- 검색 끝 -->

        <div class="shoping__cart__table">
            <table>
                <colgroup>
                    <col style="width:5%">
                    <col style="width:25%">
                    <col style="width:35%">
                    <col style="width:15%">
                    <col style="width:10%">
                    <col style="width:10%">
                </colgroup>

                <thead>
                <tr>
                    <th>No</th>
                    <th>문의유형</th>
                    <th>문의글</th>
                    <th>등록일</th>
                    <th>작성자</th>
                    <th>답변상태</th>
                </tr>
                </thead>

                <!-- list띄우기 -->
                <tbody>
                <c:if test="${empty questionlist.content}">
                    <tr><td colspan="5">등록된 문의가 없습니다.</td></tr>
                </c:if>

                <c:forEach var="dto" items="${questionlist.content }">
                    <tr>
                        <td>${questionlist.totalElements - (questionlist.number * questionlist.size) - questionlist.content.indexOf(dto)}</td>
                        <td>${dto.inquiryType}/${dto.detailType}</td>

                        <!-- 비밀글 표시 -->
                        <c:if test="${dto.secret == true}">
                            <c:choose>
                                <%-- <c:when test="${dto.member.userId eq member.userid || member.role eq '[ROLE_ADMIN, ROLE_USER]'}"> --%>
                                <c:when test="${dto.memberId eq sessionDTO.id || sessionDTO.role eq 'ADMIN'}">
                                    <%--|| sessionDTO.role eq 'ROLE_ADMIN'--%>
                                    <!-- 작성자이거나 관리자일 때 볼 수 있는 링크 -->
                                    <td>Q <a
                                            href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}">
                                        <c:out value="${dto.inquirySubject}"/><c:if
                                            test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                    </a></td>
                                </c:when>

                                <c:otherwise>
                                    <td class="text-secondary"><i class="icofont-lock"></i>
                                        🔒<c:out value="${dto.inquirySubject}"/><c:if
                                                test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:if>

                        <c:if test="${dto.secret == false}">
                            <td>
                                <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}" class="secretColor">
                                    Q ${dto.inquirySubject}
                                    <c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                </a>
                            </td>
                        </c:if>

                        <td>${dto.inquiryDate}</td>
                        <td>${dto.memberDTO.nickname}</td>
                        <td>
                            <c:if test="${dto.status == 'complete'}">
                                <span style="color: #00c73c">답변완료</span>
                            </c:if>
                            <c:if test="${dto.status == 'wait'}">
                                <span style="color: #7e828f">답변대기</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="n-table-none">
            <c:if test="${questionlist == null}"><span>등록된 1:1문의가 없습니다.</span></c:if>
        </div>

        <!-- 페이징 -->
        <div class="text-xs-center" id="myPage">
            <ul class="product__pagination text-center">
                <!-- 이전 -->
                <c:choose>
                    <c:when test="${questionlist.first}"></c:when>
                    <c:otherwise>
<%--                        <a href="/inquiry/list/?field=${param.field}&word=${param.word}&page=0">처음</a>--%>
                        <a href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.number-1}">◀</a>

                    </c:otherwise>
                </c:choose>

                <!-- 페이지 그룹 -->
                <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                    <c:choose>
                        <c:when test="${questionlist.pageable.pageNumber+1 == i}">
                            <a href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- 다음 -->
                <c:choose>
                    <c:when test="${questionlist.last}"></c:when>
                    <c:otherwise>
                        <a href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.number+1}">▶</a>
<%--                        <a href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.totalPages-1}">끝</a>--%>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</section>