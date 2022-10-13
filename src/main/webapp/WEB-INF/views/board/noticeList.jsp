<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<%
    request.setCharacterEncoding("UTF-8");
    String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Onion Market</title>
</head>
<body>
<div>
<h2>공지사항</h2>
</div>
<br/>
    <!-- 검색
    <form action="/notice/list" method="GET">
        <select name="field" id="field">
            <option selected="selected">전체</option>
            <option value="NOTICE">공지사항</option>
            <option value="QNA">QnA</option>
            <option value="EVENT">이벤트</option>
        </select>

        <input type="text" id="searchBox" name="word"/>
        <button type="submit">검색</button>
    </form>
    -->

    <!-- 리스트 -->
    <div>
        <table class="table" border="1">

            <tr>
                <td>No</td>
                <th>공지유형</th>
                <th>글제목</th>
                <th>작성자</th>
                <th>등록일</th>
                <th>조회수</th>
            </tr>

            <c:forEach var="dto" items="${noticelist.content }">
            <tr>
                <td>${noticelist.totalElements - (noticelist.number * noticelist.size) - noticelist.content.indexOf(dto)}</td>
                <td>${dto.noticeType}</td>
                <td><a href="/notice/article/${dto.noticeId}">${dto.noticeSubject}</a></td>
                <td>운영자</td>
                <td>${dto.noticeDate}</td>
                <td>${dto.hitCount}</td>
            </tr>
            </c:forEach>

        </table>
    </div>

    <!-- 검색 -->
    <form action="/notice/list" method="GET">
        <select name="field" id="field">
            <option selected="selected">전체</option>
            <option value="NOTICE">공지사항</option>
            <option value="QNA">QnA</option>
            <option value="EVENT">이벤트</option>
        </select>

        <input type="text" id="searchBox" name="word"/>
        <button type="submit">검색</button>
    </form>

    <!-- 등록버튼 -->
    <div align="right">

        <!-- 운영진일때만 버튼 보이게 설정 -->
        <button class="button"><a href="/notice/created">등록</a></button>

    </div>


    <!-- 페이징 -->
    <div class="text-xs-center">
        <ul class="pagination justify-content-center">

            <!-- 이전 -->
            <c:choose>
                <c:when test="${noticelist.first}"></c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/notice/list/?field=${field}&word=${word}&page=0">처음으로</a></li>
                    <li class="page-item"><a class="page-link" href="/notice/list/?field=${field}&word=${word}&page=${noticelist.number-1}">◀</a></li>
                </c:otherwise>
            </c:choose>

            <!-- 페이지 그룹 -->
            <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                <c:choose>
                    <c:when test="${noticelist.pageable.pageNumber+1 == i}">
                        <li class="page-item disabled"><a class="page-link" href="/notice/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/notice/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- 다음 -->
            <c:choose>
                <c:when test="${noticelist.last}"></c:when>
                <c:otherwise>
                    <li class="page-item "><a class="page-link" href="/notice/list/?field=${param.field}&word=${param.word}&page=${noticelist.number+1}">▶</a></li>
                    <li class="page-item "><a class="page-link" href="/notice/list/?field=${param.field}&word=${param.word}&page=${noticelist.totalPages-1}">끝으로</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <!-- 페이징 끝 -->

</body>
</html>
