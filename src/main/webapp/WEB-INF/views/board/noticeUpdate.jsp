<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <br/>
    <div>
    <h1>공지사항 수정</h1>
    </div>
    <br/>
    <br/>
    <form:form method="get" action="/notice/list" enctype="multipart/form-data" modelAttribute="noticeUpdateDTO">
    <div>
        <table border="1" align="center">
            <tr>
                <td colspan="3"><input type="text" name="noticeSubject" placeholder="${noticeDTO.noticeSubject}"></td>
            </tr>
            <tr>
                <td colspan="3"><input type="textarea" name="noticeContent" placeholder="${noticeDTO.noticeContent}"></td>
            </tr>
            <div>
                <c:forEach items="${noticeDTO.noticeImageList}" var="noticeImageDTO">
                <tr>
                    <td> <img src="/images/notice/${noticeImageDTO.noticeImageName}" width="150" height="150"/> </td>
                    <td>${noticeImageDTO.noticeImageName}</td>
                    <td><input type="button" onclick="location.href='/notice/image/delete/${noticeImageDTO.id}/${noticeDTO.noticeId}'" value="삭제"></td>
                </tr>
                </c:forEach>
            </div>
        </table>
    </div>
        <div>
            <label class="fileButton" for="noticeImage"><p id="fileFont">이미지 첨부하기</p></label>
            <input type="file" id="noticeImage" name="noticeImageName" multiple="multiple" style="display: none"/>
        </div>
    <div>
        <button type="submit">수정완료</button>
        <button type="button" onclick="location.href='/notice/list'">취소</button>
    </div>
    </form:form>
</body>
</html>
