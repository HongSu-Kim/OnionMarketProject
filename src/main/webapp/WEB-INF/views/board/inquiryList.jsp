<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<%--<section class="hero hero-normal">--%>
<section class="mypage-cont mypage-counsel">
    <div class="container" style="width: 1000px;">
        <div class="n-section-title">
            <h1 class="tit">1:1 문의 게시판</h1>
        </div>

        <!-- 검색 -->
        <form action="/inquiry/list" class="d-flex" method="GET" style="margin-top: 10px">
            <div class="col-lg-8">
                <select name="field" style="width: 130px;">
                    <option selected="selected" value="all">전체</option>
                    <option value="name">작성자</option>
                    <option value="회원정보">[문의유형]회원정보</option>
                    <option value="거래">[문의유형]거래</option>
                    <option value="기타서비스">[문의유형]기타서비스</option>
                </select>

                <div style="height: 42px">
                    <input type="text" name="word" style="width:200px; height: 40px" placeholder="Search">
                    <button class="site-btn" type="submit" style="height: 45px">
                        <span class="icon_search"></span></button>
                </div>
            </div>

            <div class="col-lg-4">
                <div style="text-align: center; float: right">
                    <a href="/inquiry/created" class="site-btn">1:1 문의하기</a>
                </div>
            </div>
        </form>
        <br/>
        <!-- 검색 끝 -->

        <table class="n-table table-col table-row">
            <colgroup>
                <col style="width:10%">
                <col style="width:28%">
                <col style="width:40%">
                <col style="width:20%">
                <col style="width:20%">
                <col style="width:16%">
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
            <c:forEach var="dto" items="${questionlist.content }">
                <tr class="linkcolor">
                    <td>${questionlist.totalElements - (questionlist.number * questionlist.size) - questionlist.content.indexOf(dto)}</td>
                    <td>${dto.inquiryType}/${dto.detailType}</td>

                    <!-- 비밀글 표시 -->
                    <c:if test="${dto.secret == true}">
                        <c:choose>
                            <c:when test="${dto.memberId eq memberDTO.id || memberDTO.role eq 'ADMIN'}">
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
                            <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}">
                                Q ${dto.inquirySubject}
                                <c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                            </a>
                        </td>
                    </c:if>

                    <td>${dto.inquiryDate}</td>
                    <td>${dto.memberDTO.nickname}</td>
                    <td>
                        <c:if test="${dto.status == '답변완료'}">
                            <span style="color: #00c73c">${dto.status}</span>
                        </c:if>
                        <c:if test="${dto.status == '답변대기'}">
                            <span style="color: #7e828f">${dto.status}</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div>
            <c:if test="${questionlist == null}"><span>등록된 1:1문의가 없습니다.</span></c:if>
        </div>

        <!-- 페이징 -->
        <div class="text-xs-center" id="myPage">
            <ul class="pagination justify-content-center">
                <!-- 이전 -->
                <c:choose>
                    <c:when test="${questionlist.first}"></c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/inquiry/list/?field=${param.field}&word=${param.word}&page=0">처음</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.number-1}">◀</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <!-- 페이지 그룹 -->
                <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                    <c:choose>
                        <c:when test="${questionlist.pageable.pageNumber+1 == i}">
                            <li class="page-item disabled"><a class="page-link"
                                                              href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- 다음 -->
                <c:choose>
                    <c:when test="${questionlist.last}"></c:when>
                    <c:otherwise>
                        <li class="page-item "><a class="page-link"
                                                  href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.number+1}">▶</a>
                        </li>
                        <li class="page-item "><a class="page-link"
                                                  href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.totalPages-1}">끝</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</section>