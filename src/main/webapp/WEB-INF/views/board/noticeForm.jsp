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
<form method="post" action="/notice/created" modelAttribute="noticeDTO">
    <div>
        <h1>공지사항 작성</h1>
    </div>

    <div>
        <label for="noticeType">공지 유형</label>
        <select id="noticeType" name="noticeType">
            <option selected="selected">선택해주세요</option>
            <option value="공지사항">공지사항</option>
            <option value="QnA">QnA</option>
            <option value="이벤트">이벤트</option>
        </select>
    </div>

    <div>
        <label for="noticeSubject">제목</label>
        <input type="text" id="noticeSubject" name="noticeSubject"/>

    </div>

    <div>
        <label for="noticeContent">내용</label>
        <input type="text" id="noticeContent" name="noticeContent"/>

    </div>

    <div>
        <button type="submit">등록하기</button>
        <button type="button" onclick="location.href='/notice/list'">취소</button>
    </div>
</form>
</body>
</html>
