<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">
            <div class="container">
                <div class="py-5 text-center">
                    <h2><a href="/inquiry/list" id="hrefDeco">신고 처리 목록</a></h2>
                </div>

                <div>
                    <c:if test="${sessionDTO.name != null}">
                        <form action="/member/logout" method="post">
                            <button class="btn btn-danger float-end" type="submit">로그아웃</button>
                        </form></c:if>

                    <c:if test="${sessionDTO.name == null}">
                        <button class="btn btn-primary float-end" onclick="location.href='/member/login'" type="button">로그인</button>
                    </c:if>
                </div><br/>


                <hr class="my-4">
                <div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>신고처리대상</th>
                            <th>신고유형</th>
                            <th>신고내용</th>
                            <th>등록일</th>
            <%--                <th>신고자</th>--%>
                            <th>처리상태</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="dto" items="${complainList.content }">
                            <tr>
                                <td>${complainList.totalElements - (complainList.number * complainList.size) - complainList.content.indexOf(dto)}</td>
                                <td></td>
                                <td>${dto.complainType}</td>
                                <td>${dto.complainContent}</td>
                                <td>${dto.complainDate}</td>
            <%--                    <td>${dto.}</td>--%>
                                <td>${dto.status}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>

                <!-- 페이징 -->
                <div class="text-xs-center">
                    <ul class="pagination justify-content-center">

                        <!-- 이전 -->
                        <c:choose>
                            <c:when test="${complainList.first}"></c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/complain/list/?page=0">처음으로</a></li>
                                <li class="page-item"><a class="page-link" href="/complain/list/?page=${complainList.number-1}">◀</a></li>
                            </c:otherwise>
                        </c:choose>

                        <!-- 페이지 그룹 -->
                        <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                            <c:choose>
                                <c:when test="${complainList.pageable.pageNumber+1 == i}">
                                    <li class="page-item disabled"><a class="page-link" href="/complain/list/?page=${i-1}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="/complain/list/?page=${i-1}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- 다음 -->
                        <c:choose>
                            <c:when test="${complainList.last}"></c:when>
                            <c:otherwise>
                                <li class="page-item "><a class="page-link" href="/complain/list/?page=${complainList.number+1}">▶</a></li>
                                <li class="page-item "><a class="page-link" href="/complain/list/?page=${complainList.totalPages-1}">끝으로</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <!-- 페이징 끝 -->



            </div> <!-- /container -->
            </div>
        </div>
    </div>
</section>
