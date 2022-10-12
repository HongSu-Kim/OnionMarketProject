<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
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

            <c:forEach var="dto" items="${reviewList.content }">
                <tr>
                    <td>${reviewList.totalElements - (reviewList.number * reviewList.size) - reviewList.content.indexOf(dto)}</td>
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

            <!-- 페이징 -->
            <div class="text-xs-center">
                <ul class="pagination justify-content-center">

                    <!-- 이전 -->
                    <c:choose>
                        <c:when test="${reviewList.first}"></c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/inquiry/list/?page=0">처음으로</a></li>
                            <li class="page-item"><a class="page-link" href="/inquiry/list/?page=${reviewList.number-1}">◀</a></li>
                        </c:otherwise>
                    </c:choose>

                    <!-- 페이지 그룹 -->
                    <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                        <c:choose>
                            <c:when test="${reviewList.pageable.pageNumber+1 == i}">
                                <li class="page-item disabled"><a class="page-link" href="/review/list/?page=${i-1}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/review/list/?page=${i-1}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <!-- 다음 -->
                    <c:choose>
                        <c:when test="${reviewList.last}"></c:when>
                        <c:otherwise>
                            <li class="page-item "><a class="page-link" href="/review/list/?page=${reviewList.number+1}">▶</a></li>
                            <li class="page-item "><a class="page-link" href="/review/list/?page=${reviewList.totalPages-1}">끝으로</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>

            </tbody>
        </table>
    </div>





</div> <!-- /container -->
</body>
</html>