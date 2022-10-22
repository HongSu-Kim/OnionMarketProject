<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>
<style>
    .review-btn {
        font-size: 14px;
        color: #ffffff;
        font-weight: 800;
        text-transform: uppercase;
        display: inline-block;
        padding: 10px 10px 1px;
        background: #7fad39;
        border: none;
    }
</style>
<section class="spad">
    <div class="row">
        <div class="container">
            <div class="section-title related-blog-title">
                <h2>${memberDTO.nickname}님의 거래 후기 목록</h2>
            </div>

            <div>
                <c:if test="${memberDTO.nickname != null}">
                    <form action="/member/logout" method="post">
                        <button class="btn btn-danger float-end" type="submit" name="${_csrf.parameterName}"
                                value="${_csrf.token}">로그아웃
                        </button>
                    </form>
                </c:if>

                <c:if test="${memberDTO.nickname == null}">
                    <button class="btn btn-primary float-end" onclick="location.href='/member/login'" type="button">
                        로그인
                    </button>
                </c:if>
            </div>
            <br/>

            <c:forEach var="dto" items="${reviewList.content }">
            <hr/><div class="col-lg-12">
                <div style="display: flex;">
                    <%--<div>No.${reviewList.totalElements - (reviewList.number * reviewList.size) - reviewList.content.indexOf(dto)}</div> --%>
                    <div class="col-lg-1">
                        <img alt="프로필사진" style="border-radius: 50%"
                             src="https://dnvefa72aowie.cloudfront.net/origin/profile/202205/95dcbfc5cb4cebccc474649f3d6f54aae095667e6e9255ea3b1e05f2fe69d4f0.webp?q=82&amp;s=80x80&amp;t=crop"/>
                    </div>
                    <div class="col-lg-1">
                        <p style="height: 10px">${memberDTO.nickname}</p>
                        <p style="height: 10px"><c:forEach var="i" begin="1" end="${dto.grade}"><span style="color: gold">★</span></c:forEach></p>
                    </div>
                    <div class="col-lg-3">
                        ${memberDTO.address}구매자 주소 - 시,동
                    </div>
                    <div class="col-lg-6 text-right">
                        <time class="review-time">
                                ${dto.reviewDate}
                        </time>
                    </div>
                    <div>
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


            <div align="center">
                <button type="button" id="btnResultMore">더보기</button>
            </div>
        </div>
    </div>
</section>

<script >

</script>