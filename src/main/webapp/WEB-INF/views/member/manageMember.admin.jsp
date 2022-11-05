<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="spad">
    <div class="container">
        <div class="memberList">

            <!-- Member List -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                            <tr>
                                <th>프로필 사진</th>
                                <th>권한</th>
                                <th>닉네임</th>
                                <th>판매중인 물품</th>
                                <th>평점</th>
                                <th>신고당한 횟수</th>
                                <th>계정 잠금</th>
                            </tr>
                            </thead>
                            <tbody>

                            <!-- 회원 없음 -->
                            <c:if test="${empty page.content}">
                                <tr>
                                    <td colspan="5"class="text-center">등록된 회원이 없습니다.</td>
                                </tr>
                            </c:if>

                            <!-- 회원 정보 -->
                            <c:forEach var="memberDTO" items="${page.content}">
                                <tr>
                                    <td class="text-align-left pointer" onclick="window.open('/img/member/${memberDTO.memberImageName}', 'Profile', 'width=600, height=800, location=no, status=no, scrollbars=yes');">
                                        <img src="/img/member/${memberDTO.memberImageName}" class="list-img profile">
                                    </td>
                                    <td>${memberDTO.role}</td>
                                    <td><a href="/member/profile/${memberDTO.id}">${memberDTO.nickname}</a></td>
                                    <td><a href="/product/personalList/${memberDTO.id}">보러 가기</a></td>
                                    <td>${memberDTO.userGrade}</td>
                                    <td><a href="/complain/personalList/${memberDTO.id}">${memberDTO.complaintCount}</a></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${memberDTO.role == 'USER'}">
                                                <button onclick="location.href='/member/addLock/${memberDTO.id}'">계정 잠금</button>
                                            </c:when>
                                            <c:when test="${memberDTO.role == 'LOCK'}">
                                                <button onclick="location.href='/member/removeLock/${memberDTO.id}'">잠금 해제</button>
                                            </c:when>
                                            <c:when test="${memberDTO.role == 'ADMIN' || 'WITHDRAWL'}">

                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- Member List End -->

            <!-- List Paging -->
            <div class="row">
                <div class="col-lg-12">

                    <!-- 페이징 -->
                    <c:if test="${!empty page.content && page.totalPages != 1}">
                        <div class="product__pagination text-center">
                            <c:set var="size" value="${page.pageable.pageSize}"/>
                            <fmt:parseNumber var="pages" integerOnly="true" value="${page.number / size}"/>
                            <c:set var="startNumber" value="${pages * size + 1}"/>
                            <c:set var="endNumber" value="${page.totalPages > (pages + 1) * size ? (pages + 1) * size : page.totalPages}"/>
                            <c:if test="${page.totalPages > size && page.number + 1 > size}">
                                <a href="?page=1"><<</a>
                                <a href="?page=${startNumber - 1}"><</a>
                            </c:if>
                            <c:forEach var="currentNumber" begin="${startNumber}" end="${endNumber}">
                                <a href="?page=${currentNumber}">${currentNumber}</a>
                            </c:forEach>
                            <c:if test="${page.totalPages > endNumber}">
                                <a href="?page=${endNumber + 1}">></a>
                                <a href="?page=${page.totalPages}">>></a>
                            </c:if>
                        </div>
                    </c:if>

                </div>
            </div>
            <!-- List Paging End -->
            <div class="mt-5 text-center"><button class="site-btn" type="button" onclick="location.href='/member/mypage'">마이페이지</button></div>
        </div>
    </div>
</section>
