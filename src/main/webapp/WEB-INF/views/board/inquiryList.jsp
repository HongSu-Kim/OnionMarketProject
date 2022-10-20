<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">
            <div class="container">
                <div class="section-title" style="margin-top: 20px">
                    <h2>1:1 문의</h2>
                </div>

                <!-- 검색 -->
                <form action="/inquiry/list" class="d-flex" method="GET">
                    <select name="field" id="field" class="form-control form-control-sm" style="width: 130px;">
                        <option selected="selected" value="all">전체</option>
                        <option value="name">작성자</option>
                        <option value="회원정보">[문의유형]회원정보</option>
                        <option value="거래">[문의유형]거래</option>
                        <option value="기타서비스">[문의유형]기타서비스</option>
                    </select>

                    <input type="text" id="searchBox" name="word" class="col-xl-4" placeholder="검색어를 입력하세요">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form><br/>
                <!-- 검색 끝 -->

                <div>
                    <c:if test="${sessionDTO.nickname != null}">
                        <form action="/member/logout" method="post">
                            <button class="btn btn-danger float-end" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">로그아웃</button>
                        </form></c:if>

                        <button class="btn btn-success float-end" onclick="location.href='/inquiry/created'" type="button">1:1 문의 등록</button>
                    <c:if test="${sessionDTO.nickname == null}">
                        <button class="btn btn-primary float-end" onclick="location.href='/member/login'" type="button">로그인</button>
                    </c:if>
                </div><br/>

                <div>
                    <table class="table">
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
                        <tbody>

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
                                            <td>Q <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}">
                                                <c:out value="${dto.inquirySubject}"/><c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                            </a></td>
                                        </c:when>

                                        <c:otherwise>
                                            <td class="text-secondary"><i class="icofont-lock"></i>
                                                🔒<c:out value="${dto.inquirySubject}"/><c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>

                                <c:if test="${dto.secret == false}">
                                    <td>
                                        <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}">
                                            Q ${dto.inquirySubject}
                                            <c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                        </a>
                                    </td>
                                </c:if>

                                <td>${dto.inquiryDate}</td>
                                <td>${dto.memberId}</td>
                                <td>${dto.status}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>

                <!-- 페이징 -->
                <div class="text-xs-center">
                    <ul class="pagination justify-content-center">

                        <!-- 이전 -->
                        <c:choose>
                            <c:when test="${questionlist.first}"></c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/inquiry/list/?field=${field}&word=${word}&page=0">처음으로</a></li>
                                <li class="page-item"><a class="page-link" href="/inquiry/list/?field=${field}&word=${word}&page=${questionlist.number-1}">◀</a></li>
                            </c:otherwise>
                        </c:choose>

                        <!-- 페이지 그룹 -->
                        <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                            <c:choose>
                                <c:when test="${questionlist.pageable.pageNumber+1 == i}">
                                    <li class="page-item disabled"><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- 다음 -->
                        <c:choose>
                            <c:when test="${questionlist.last}"></c:when>
                            <c:otherwise>
                                <li class="page-item "><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.number+1}">▶</a></li>
                                <li class="page-item "><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.totalPages-1}">끝으로</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <!-- 페이징 끝 -->



            </div> <!-- /container -->
            </div>
        </div>
    </div>
</section>