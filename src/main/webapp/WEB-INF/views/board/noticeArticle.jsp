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
        <h1>공지사항</h1>
    </div>
    <br/>
    <br/>
    <div>
        제목:${noticeDTO.noticeSubject}
    </div>
    <hr/>
    <div>
        내용:${noticeDTO.noticeContent}
    </div>
    <div>
        작성일자:${noticeDTO.noticeDate}
    </div>
    <div>
        조회수:${noticeDTO.hitCount}
    </div>
    <div>
        <c:forEach items="${noticeDTO.noticeImageList}" var="noticeImageDTO">
            <img src="/images/notice/${noticeImageDTO.noticeImageName}" width="900" height="1200"/>
        </c:forEach>
    </div>

    <div>
        <button class="button" onclick="location.href='/notice/update/${id}'">수정하기</button>
        <button class="button" onclick="location.href='/notice/delete/${id}'">삭제</button>
        <button class="button" onclick="location.href='/notice/list'" aline="center">돌아가기</button>
    </div>

</body>
</html>
