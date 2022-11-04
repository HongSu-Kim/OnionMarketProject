<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<section class="hero-normal" style="margin-top: 200px">
    <div class="container" style="width: 1000px;">
        <div class="section-title">
            <h3 style="margin-left: 100px">1:1 문의 게시판
                <div style="float: right;">
                    <a href="/inquiry/created" class="site-btn" style="height: 43px; ">1:1 문의하기</a>
                </div>
            </h3>

        </div>

        <form name="searchForm" id="searchForm" method="get" action="/inquiry/list">

            <div class="n-table-filter">
                <div class="n-radio-tab">
                    <input type="checkbox" id="radioTabGuide0" checked onClick="setPeriod(this,'1week');">
                    <label for="radioTabGuide0">1주일</label>

                    <input type="checkbox" id="radioTabGuide1" onClick="setPeriod(this,'1month');">
                    <label for="radioTabGuide1">1개월</label>

                    <input type="checkbox" id="radioTabGuide2" onClick="setPeriod(this,'3month');">
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

                <div style="text-align: right">
                    <button type="button" class="site-btn" onclick="search();">조회</button>
                </div>
            </div>


            <div class="d-flex" style="width: 970px">
                <select name="field" style="width: 130px;">
                    <option selected="selected" value="all">전체</option>
                    <option value="name">작성자</option>
                    <option value="회원정보">[문의유형]회원정보</option>
                    <option value="거래">[문의유형]거래</option>
                    <option value="기타서비스">[문의유형]기타서비스</option>
                </select>

                <div style="height: 45px; margin-bottom: 10px">
                    <input type="text" name="word" class="searchIn" placeholder="검색할 단어를 입력하세요">
                    <button class="site-btn" type="button" onclick="searchWord();" style="height: 43px;">
                        <span class="icon_search"></span></button>
                </div>
            </div>
        </form>
        <!-- 검색 -->


        <table class="n-table table-col table-row">
            <colgroup>
                <col style="width:10%">
                <col style="width:43%">
                <col style="width:40%">
                <col style="width:10%">
                <col style="width:20%">
                <col style="width:16%">
            </colgroup>

            <thead>
            <tr>
                <th>No</th>
                <th>문의유형</th>
                <th>문의글</th>
                <th>작성자</th>
                <th>등록일</th>
                <th>답변상태</th>
            </tr>
            </thead>


            <!-- list띄우기 -->
            <tbody>
            <c:choose>
                <c:when test="${empty questionlist.content}">
                    <tr><td colspan="6" style="text-align: center">등록된 문의가 없습니다.</td></tr>
                </c:when>

                <c:otherwise>
                    <c:forEach var="dto" items="${questionlist.content }">
                        <tr>
                            <td><a style="margin-left: 30px;">${questionlist.totalElements - (questionlist.number * questionlist.size) - questionlist.content.indexOf(dto)}</a></td>
                            <td><a style="margin-left: 75px;">${dto.inquiryType}/${dto.detailType}</a></td>

                            <!-- 비밀글 표시 -->
                            <c:if test="${dto.secret == true}">
                                <c:choose>
                                    <%-- <c:when test="${dto.member.userId eq member.userid || member.role eq '[ROLE_ADMIN, ROLE_USER]'}"> --%>
                                    <c:when test="${dto.memberId eq sessionDTO.id || sessionDTO.role eq 'ADMIN'}">
                                        <%--|| sessionDTO.role eq 'ROLE_ADMIN'--%>
                                        <!-- 작성자이거나 관리자일 때 볼 수 있는 링크 -->
                                        <td>Q <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}">
                                            <c:out value="${dto.inquirySubject}"/><c:if
                                                test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                        </a></td>
                                    </c:when>

                                    <c:otherwise>
                                        <td class="text-secondary"><a><i class="icofont-lock"></i>
                                            🔒<c:out value="${dto.inquirySubject}"/><c:if
                                                    test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                        </a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>

                            <c:if test="${dto.secret == false}">
                                <td>
                                    <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}"
                                       class="secretColor">
                                        Q ${dto.inquirySubject}
                                        <c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                    </a>
                                </td>
                            </c:if>

                            <td><a style="margin-left: 15px">${dto.memberDTO.userId}</a></td>

                            <td><a style="margin-left: 30px">${dto.inquiryDate}</a></td>
                            <td>
                                <c:if test="${dto.status == 'complete'}">
                                    <a style="margin-left: 40px"><span style="color: #00c73c">complete</span></a>
                                </c:if>
                                <c:if test="${dto.status == 'wait'}">
                                    <a style="margin-left: 40px"><span style="color: #7e828f">wait</span></a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <div>
            <c:if test="${questionlist == null}"><span>등록된 1:1문의가 없습니다.</span></c:if>
        </div>

        <!-- 페이징 -->
        <div class="text-xs-center" id="myPage">
            <ul class="product__pagination text-center">
                <!-- 이전 -->
                <c:choose>
                    <c:when test="${questionlist.first}"></c:when>
                    <c:otherwise>
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