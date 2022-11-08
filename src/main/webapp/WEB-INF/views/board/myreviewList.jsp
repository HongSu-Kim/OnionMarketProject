<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section class="spad">
    <div class="row">
        <div class="container">
            <div class="section-title related-blog-title">
                <h2>${memberDTO.nickname}님의 후기 목록</h2>
            </div>

            <input type="hidden" id="memberId" value="${memberDTO.id}">

            <c:choose>
                <c:when test="${empty reviewList.content}">
                    <h5 style="text-align: center">회원님이 작성한 후기가 없습니다.</h5>
                </c:when>

                <c:otherwise>
                    <c:forEach var="dto" items="${reviewList.content }">
                        <hr/>
                        <div class="col-lg-12">
                            <div style="display: flex; margin-bottom: 20px">
                                    <%--<div>No.${reviewList.totalElements - (reviewList.number * reviewList.size) - reviewList.content.indexOf(dto)}</div> --%>
                                <div class="col-lg-1">
                                    <img alt="프로필사진" style="border-radius: 50%"
                                         src="/img/member/${dto.memberDTO.memberImageName}"/>
                                </div>

                                <div class="col-lg-1">
                                    <p style="height: 10px; width: 64px">${dto.memberDTO.nickname}</p>
                                    <p style="height: 10px"><c:forEach var="i" begin="1" end="${dto.grade}"><span
                                            style="color: gold">★</span></c:forEach></p>
                                </div>
                                <div class="col-lg-3">
                                        ${dto.memberDTO.address}
                                </div>
                                <div class="col-lg-6 text-right">
                                    <time class="review-time">
                                            ${dto.reviewDate}
                                    </time>
                                </div>
                                <div class="text-right">
                                    <c:if test="${dto.memberId == memberDTO.id}">
                                        <button onclick="location.href='/review/update/${memberDTO.id}/${dto.reviewId}'"
                                                style="background-color: #90C8AC; border-radius: 20px; border: none; font-size: 10pt; padding: 8px; color: white">
                                            수정
                                        </button>
                                        <button onclick="location.href='/review/delete/${memberDTO.id}/${dto.reviewId}'"
                                                style="background-color: #7e828f; border-radius: 20px; border: none; font-size: 10pt; padding: 8px; color: white">
                                            삭제
                                        </button>
                                    </c:if>
                                </div>
                            </div>

                            <div style="display: flex">
                                <div class="col-lg-5" style="margin-top: 20px; margin-left: 35px;">
                                    <p style="height: 10px; color: #90C8AC; font-weight: bold;">
                                        구매상품: ${dto.productDTO.subject}</p>
                                    <p>${dto.reviewContent}</p>
                                </div>


                                <div class="col-lg-7 text-right">
                                    <c:forEach items="${dto.reviewImageList}" var="reviewImageDTO">
                                        <img src="/img/review/${reviewImageDTO.storeImageName}" width="100px;"
                                             height="110px;" onmouseenter="zoomIn(event)"
                                             onmouseleave="zoomOut(event)"/>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>


    <!-- 페이지 그룹 -->
    <div class="text-xs-center" style="margin-bottom: 20px">
        <ul class="product__pagination text-center">
            <c:choose>
                <c:when test="${reviewList.first}"></c:when>
                <c:otherwise>
                    <%--                    <a href="/notice/list/?field=${field}&word=${word}&page=0">처음으로</a>--%>
                    <a href="/review/mylist/${memberDTO.id}?page=${reviewList.number-1}">◀</a>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                <c:choose>
                    <c:when test="${reviewList.pageable.pageNumber+1 == i}">
                        <a href="/review/mylist/${memberDTO.id}?page=${i-1}">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/review/mylist/${memberDTO.id}?page=${i-1}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- 다음 -->
            <c:choose>
                <c:when test="${reviewList.last}"></c:when>
                <c:otherwise>
                    <a href="/review/mylist/${memberDTO.id}?page=${reviewList.number+1}">▶</a>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <!-- 페이징 끝 -->

</section>
