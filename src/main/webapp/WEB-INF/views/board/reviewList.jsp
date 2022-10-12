<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/inquiryList.css" rel="stylesheet">
</head>
<body id="inquiryBody">
<div class="container">
    <div class="py-5 text-center">
        <h2><a href="/inquiry/list" id="hrefDeco">후기 목록</a></h2>
    </div>

    <c:if test="${memberDTO.name != null}">
        <p>${memberDTO.name}. 접속중입니다</p>
    </c:if>


    <div>
        <c:if test="${memberDTO.name != null}">
            <form action="/member/logout" method="post">
                <button class="btn btn-danger float-end" type="submit">로그아웃</button>
            </form></c:if>

        <c:if test="${memberDTO.name == null}">
            <button class="btn btn-primary float-end" onclick="location.href='/member/login'" type="button">로그인</button>
        </c:if>
    </div><br/>


    <hr class="my-4">
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>No</th>
                <th>주문번호</th>
                <th>리뷰 내용</th>
                <th>별점</th>
                <th>등록일</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="dto" items="${reviewList }">
                <tr>
                    <td>${dto.reviewId}</td>
                    <td>${dto.orderId}
                    <td>
                        <div>
                            <c:forEach items="${dto.reviewImageList}" var="reviewImageDTO">
                                <img src="/images/${reviewImageDTO.storeImageName}" width="300" height="300"/>
                            </c:forEach>
                        </div>
                        ${dto.reviewContent}
                    </td>
                    <td>${dto.grade}</td>
                    <td>${dto.reviewDate}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>





</div> <!-- /container -->
</body>
</html>