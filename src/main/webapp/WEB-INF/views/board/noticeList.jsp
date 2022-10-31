<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<!-- Shoping Cart Section Begin -->
<section class="shoping-cart spad">
    <div class="container" style="width: 1000px; margin-top: 20px">
        <div class="section-title">
            <h3 style="font-weight: bold">공지사항</h3>
        </div>

        <!-- 검색 -->
        <form action="/notice/list" class="d-flex" method="GET" style="margin-top: 10px; margin-bottom: 5px">
            <div class="col-lg-8">
                <div style="height: 45px">
                    <input type="text" name="word" class="searchIn" placeholder="검색할 단어를 입력하세요">
                    <button class="site-btn" type="submit" style="height: 44px;">
                        <span class="icon_search"></span></button>
                </div>
            </div>

            <c:if test="${memberDTO.role == 'ADMIN'}">
                <div class="col-lg-4">
                    <div style="text-align: center; float: right">
                        <a href="/notice/created" class="site-btn">공지 등록</a>
                    </div>
                </div>
            </c:if>
        </form>
        <hr style="background-color: #47cd65; height: 1px"/>
        <!-- 검색 끝 -->

        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__table">
                    <table>
                        <colgroup>
                            <col style="width:5%">
                            <col style="width:55%">
                            <col style="width:10%">
                            <col style="width:20%">
                            <col style="width:15%">
                        </colgroup>

                        <thead>
                        <tr>
                            <th>No</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>등록일</th>
                            <th>조회수</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty noticelist.content}">
                            <tr>
                                <td colspan="5">등록된 공지가 없습니다.</td>
                            </tr>
                        </c:if>

                        <c:forEach var="dto" items="${noticelist.content }">
                            <tr>
                                <td>${noticelist.totalElements - (noticelist.number * noticelist.size) - noticelist.content.indexOf(dto)}</td>
                                <td><a href="/notice/article/${dto.noticeId}">${dto.noticeSubject}</a></td>
                                <td>${dto.memberDTO.userId}</td>
                                <td>${dto.noticeDate}</td>
                                <td>${dto.hitCount}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- 페이징 -->
    <div class="text-xs-center">
        <ul class="product__pagination text-center">
            <!-- 이전 -->
            <c:choose>
                <c:when test="${noticelist.first}"></c:when>
                <c:otherwise>
                    <%--                    <a href="/notice/list/?field=${field}&word=${word}&page=0">처음으로</a>--%>
                    <a href="/notice/list/?field=${field}&word=${word}&page=${noticelist.number-1}">◀</a>
                </c:otherwise>
            </c:choose>

            <!-- 페이지 그룹 -->
            <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                <c:choose>
                    <c:when test="${noticelist.pageable.pageNumber+1 == i}">
                        <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- 다음 -->
            <c:choose>
                <c:when test="${noticelist.last}"></c:when>
                <c:otherwise>
                    <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${noticelist.number+1}">▶</a>
                    <%--                    <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${noticelist.totalPages-1}">끝으로</a>--%>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <!-- 페이징 끝 -->
</section>