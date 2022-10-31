<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<section class="spad">
    <div class="row">
        <div class="container">
            <div class="section-title related-blog-title">
                <h2>${memberDTO.nickname}님의 받은 거래 후기 목록</h2>
            </div>

            <c:if test="${empty reviewList.content}">
                <div>등록된 후기가 없습니다.</div>
            </c:if>

            <c:forEach var="dto" items="${reviewList.content }">
            <hr/><div class="col-lg-12">
                <div style="display: flex;">
                    <%--<div>No.${reviewList.totalElements - (reviewList.number * reviewList.size) - reviewList.content.indexOf(dto)}</div> --%>
                    <div class="col-lg-1">
                        <img alt="프로필사진" style="border-radius: 50%"
                             src="https://dnvefa72aowie.cloudfront.net/origin/profile/202205/95dcbfc5cb4cebccc474649f3d6f54aae095667e6e9255ea3b1e05f2fe69d4f0.webp?q=82&amp;s=80x80&amp;t=crop"/>
                    </div>
                    <div class="col-lg-1">
                        <p style="height: 10px">${dto.memberDTO.nickname}</p>
                        <p style="height: 10px"><c:forEach var="i" begin="1" end="${dto.grade}"><span style="color: gold">★</span></c:forEach></p>
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
                            <button class="review-btn" onclick="location.href='/review/update/${dto.reviewId}'">수정</button>
                            <button class="review-btn" onclick="location.href='/review/delete/${dto.reviewId}'" style="background-color: #e4606d">삭제</button>
                        </c:if>
                    </div>
                </div>

                <div style="display: flex">
                    <div class="col-lg-6" style="margin-top: 20px; margin-left: 35px;">
                        <p style="height: 10px; color: #003eff; font-weight: bold;">상품제목</p>
                        <p>${dto.reviewContent} 고맙습니다 잘쓸게요</p>
                    </div>
                    <div class="col-lg-5 text-right">
                        <c:forEach items="${dto.reviewImageList}" var="reviewImageDTO">
                            <img src="/img/review/${reviewImageDTO.storeImageName}" width="100px;" height="110px;"/>
                        </c:forEach>
                    </div>
                </div>
            </div>
            </c:forEach>

        </div>
    </div>
</section>
