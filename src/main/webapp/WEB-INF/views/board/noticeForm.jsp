<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 작성 페이지</title>
</head>
<body>
<form:errors path="noticeDTO"/>
<form:form method="post" action="/notice/created" modelAttribute="noticeDTO">

    <input type="hidden" name="memberId" value="${memberDTO.id}">

    <div>
        <h1>공지사항 작성</h1>
    </div>

    <div>
        <!--<label for="noticeType">공지 유형</label>-->
        <select id="noticeType" name="noticeType">
            <option selected="selected">선택해주세요</option>
            <option value="NOTICE">공지사항</option>
            <option value="QNA">QnA</option>
            <option value="EVENT">이벤트</option>
        </select>
    </div>
    <br/>
    <div>
        <label for="noticeSubject">제목</label>
        <input type="text" id="noticeSubject" name="noticeSubject"/>
    </div>
    <br/>
    <div>
        <label for="noticeContent">내용</label>
        <input type="text" id="noticeContent" name="noticeContent"/>
    </div>
    <br/>
    <div>
        <button type="submit">등록하기</button>
        <button type="button" onclick="location.href='/notice/list'">취소</button>
    </div>
</form:form>
</body>
</html>
