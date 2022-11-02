<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<%--<section class="hero hero-normal">--%>
<section class="mypage-cont mypage-counsel">
    <div class="container" style="width: 1000px;">
        <div class="n-section-title">
            <h1 class="tit">나의 1:1문의</h1>
        </div>

        <form name="searchForm" id="searchForm" method="get" action="/inquiry/myList/${memberId}">

            <div class="n-table-filter">
                <div class="n-radio-tab">
                    <input type="radio" id="radioTabGuide0" checked onClick="setPeriod(this,'1week');">
                    <label for="radioTabGuide0">1주일</label>

                    <input type="radio" id="radioTabGuide1" onClick="setPeriod(this,'1month');">
                    <label for="radioTabGuide1">1개월</label>

                    <input type="radio" id="radioTabGuide2" onClick="setPeriod(this,'3month');">
                    <label for="radioTabGuide2">3개월</label>
                </div>

                <div class="n-datepicker sb">
                    <input type="text" class="n-input" name="dt_fr" value="" id="testDatepicker1"
                           onblur="checkDateFormat(this);">
                </div>
                <div class="n-datepicker">
                    <input type="text" class="n-input" name="dt_to" value="" id="testDatepicker2"
                           onblur="checkDateFormat(this);">
                </div>
                <div class="n-select">
                    <select id="state">
                        <option value="">전체보기</option>
                        <option value="답변대기">답변대기</option>
                        <option value="답변완료">답변완료</option>
                    </select>
                </div>

                <div style="text-align: right">
                    <button type="button" class="site-btn" onclick="search();">조회</button>
                </div>
            </div>
        </form>
        <!-- 검색 -->

        <table class="n-table table-col table-row">
            <colgroup>
                <col style="width:10%">
                <col style="width:28%">
                <col style="width:40%">
                <col style="width:20%">
                <col style="width:16%">
            </colgroup>

            <thead>
            <tr>
                <th>No</th>
                <th>문의유형</th>
                <th>문의글</th>
                <th>등록일</th>
                <th>답변상태</th>
            </tr>
            </thead>

            <!-- list띄우기 -->
            <tbody>
            <c:choose>
                <c:when test="${questionlist.size == 0}">
                    회원님의 문의내역이 존재하지 않습니다.
                </c:when>

                <c:otherwise>
                    <c:forEach var="dto" items="${questionlist.content }">
                        <tr>
                            <td>${questionlist.totalElements - (questionlist.number * questionlist.size) - questionlist.content.indexOf(dto)}</td>
                            <td>${dto.inquiryType}/${dto.detailType}</td>

                            <td style="text-align: left;">
                                <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}"
                                   style="color: #5a6268; margin-left: 100px;">
                                    Q ${dto.inquirySubject}
                                    <c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                </a>
                            </td>

                            <td>${dto.inquiryDate}</td>
                            <td>
                                <c:if test="${dto.status == 'complete'}">
                                    <span style="color: #00c73c">complete</span>
                                </c:if>
                                <c:if test="${dto.status == 'wait'}">
                                    <span style="color: #7e828f">wait</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- 페이징 -->
        <div class="text-xs-center" id="myPage" style="margin-top: 10px">
            <ul class="pagination justify-content-center">
                <!-- 이전 -->
                <c:choose>
                    <c:when test="${questionlist.first}"></c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="/inquiry/myList/${memberId}?field=${param.field}&word=${param.word}&page=0">처음</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="/inquiry/myList/${memberId}?field=${param.field}&word=${param.word}&page=${questionlist.number-1}">◀</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <!-- 페이지 그룹 -->
                <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                    <c:choose>
                        <c:when test="${questionlist.pageable.pageNumber+1 == i}">
                            <li class="page-item disabled">
                                <a class="page-link"
                                   href="/inquiry/myList/${memberId}?dt_fr=${param.dt_fr}&dt_to=${param.dt_to}&page=${i-1}">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="/inquiry/myList/${memberId}?dt_fr=${param.dt_fr}&dt_to=${param.dt_to}&page=${i-1}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- 다음 -->
                <c:choose>
                    <c:when test="${questionlist.last}"></c:when>
                    <c:otherwise>
                        <li class="page-item ">
                            <a class="page-link"
                               href="/inquiry/myList/${memberId}?dt_fr=${param.dt_fr}&dt_to=${param.dt_to}&page=${questionlist.number+1}">▶</a>
                        </li>
                        <li class="page-item ">
                            <a class="page-link"
                               href="/inquiry/myList/${memberId}?dt_fr=${param.dt_fr}&dt_to=${param.dt_to}&page=${questionlist.totalPages-1}">끝</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>


        <div class="n-table-none">
            <c:if test="${questionlist == null}"><span>등록된 1:1문의가 없습니다.</span></c:if>
        </div>
        <div style="text-align: center; margin-bottom: 15px;">
            <a href="/inquiry/created" class="site-btn">1:1 문의하기</a>
        </div>
    </div>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
</section>
