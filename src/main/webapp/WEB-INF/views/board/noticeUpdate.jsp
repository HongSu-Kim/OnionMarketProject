<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
    공지사항을 수정하는 곳입니다
    </div>
    <form:form method="post" action="/notiec/update/${id}" modelAttribute="noticeDTO">
    <div>
        <table border="1" align="center">
            <tr>
                <td><input type="text" placeholder="${noticeDTO.noticeSubject}"></td>
            </tr>
            <tr>
                <td><input type="textarea" placeholder="${noticeDTO.noticeContent}"></td>
            </tr>
        </table>
    </div>

    <div>
        <button type="submit">수정완료</button>
        <button type="button" onclick="location.href='/notice/list'">취소</button>
    </div>
    </form:form>
</body>
</html>
