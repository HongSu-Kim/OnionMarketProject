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

                    <div class="blog__details__text">
                        <img src="img/blog/details/details-pic.jpg" alt="">
                        <h3>받은 거래 후기</h3>
                    </div>

                    <div>
                        <c:if test="${sessionDTO.nickname != null}">
                            <form action="/member/logout" method="post">
                                <button class="btn btn-danger float-end" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">로그아웃</button>
                            </form></c:if>

                        <c:if test="${sessionDTO.nickname == null}">
                            <button class="btn btn-primary float-end" onclick="location.href='/member/login'" type="button">로그인</button>
                        </c:if>
                    </div><br/>

                    <div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>No</th>
                                <%-- <th>주문번호</th> --%>
                                <th>리뷰 내용</th>
                                <th>별점</th>
                                <th>등록일</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="dto" items="${userReviewList.content }">
                                <tr>
                                    <td>${userReviewList.totalElements - (userReviewList.number * userReviewList.size) - userReviewList.content.indexOf(dto)}</td>
                                        <%-- <td>${dto.orderId} --%>
                                    <td>
                                        <div>
                                            <c:forEach items="${dto.reviewImageList}" var="reviewImageDTO">
                                                <img src="/img/review/${reviewImageDTO.storeImageName}" width="300" height="300"/>
                                            </c:forEach>
                                        </div>
                                            ${dto.reviewContent}
                                    </td>
                                    <td>
                                        <c:forEach var="i" begin="1" end="${dto.grade}">★</c:forEach>
                                    </td>
                                    <td>${dto.reviewDate}</td>

                                    <td>
                                        <c:if test="${sessionDTO.id == dto.memberId}">
                                            <button onclick="location.href='/review/update/${dto.reviewId}'">수정</button>
                                            <button onclick="location.href='/review/delete/${dto.reviewId}'">삭제</button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- 페이징 -->
                        <div class="text-xs-center">
                            <ul class="pagination justify-content-center">

                                <!-- 이전 -->
                                <c:choose>
                                    <c:when test="${userReviewList.first}"></c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="/review/list/?page=0">처음으로</a></li>
                                        <li class="page-item"><a class="page-link" href="/review/list/?page=${userReviewList.number-1}">◀</a></li>
                                    </c:otherwise>
                                </c:choose>

                                <!-- 페이지 그룹 -->
                                <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                                    <c:choose>
                                        <c:when test="${userReviewList.pageable.pageNumber+1 == i}">
                                            <li class="page-item disabled"><a class="page-link" href="/review/list/?page=${i-1}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="/review/list/?page=${i-1}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <!-- 다음 -->
                                <c:choose>
                                    <c:when test="${userReviewList.last}"></c:when>
                                    <c:otherwise>
                                        <li class="page-item "><a class="page-link" href="/review/list/?page=${userReviewList.number+1}">▶</a></li>
                                        <li class="page-item "><a class="page-link" href="/review/list/?page=${userReviewList.totalPages-1}">끝으로</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>

                    </div>





                </div> <!-- /container -->
            </div>
        </div>
    </div>
</section>