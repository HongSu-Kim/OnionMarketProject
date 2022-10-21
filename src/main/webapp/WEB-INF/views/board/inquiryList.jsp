<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<style>
    #pageButton > li > a{
        color: black;
    }
</style>
<!-- Blog Details Section Begin -->
<section class="blog-details spad" style="background-color: #fdfdfe">
    <div class="col-lg-12">
        <div class="section-title related-blog-title">
            <h2>Q&A Board</h2>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-5 order-md-1 order-2">
                <div class="blog__sidebar">
                        <!-- 검색 -->
                        <form action="/inquiry/list" class="d-flex" method="GET">
                            <select name="field" style="width: 130px;">
                                <option selected="selected" value="all">전체</option>
                                <option value="name">작성자</option>
                                <option value="회원정보">[문의유형]회원정보</option>
                                <option value="거래">[문의유형]거래</option>
                                <option value="기타서비스">[문의유형]기타서비스</option>
                            </select>

                            <input type="text" name="word" class="col-xl-5" placeholder="Search">
                            <button class="btn btn-outline-success" type="submit"><span class="icon_search"></span></button>
                        </form><br/>
                        <!-- 검색 끝 -->
                    <div class="blog__sidebar__item">
                        <h4>Categories</h4>
                        <ul>
                            <li><a href="#">All</a></li>
                            <li><a href="#">Beauty (20)</a></li>
                            <li><a href="#">Food (5)</a></li>
                            <li><a href="#">Life Style (9)</a></li>
                            <li><a href="#">Travel (10)</a></li>
                        </ul>
                    </div>
                    <div class="blog__sidebar__item">
                        <h4>Recent News</h4>
                        <div class="blog__sidebar__recent">
                            <a href="#" class="blog__sidebar__recent__item">
                                <div class="blog__sidebar__recent__item__pic">
                                    <img src="/template/img/blog/sidebar/sr-1.jpg" alt="">
                                </div>
                                <div class="blog__sidebar__recent__item__text">
                                    <h6>09 Kinds Of Vegetables<br /> Protect The Liver</h6>
                                    <span>MAR 05, 2019</span>
                                </div>
                            </a>
                            <a href="#" class="blog__sidebar__recent__item">
                                <div class="blog__sidebar__recent__item__pic">
                                    <img src="/template/img/blog/sidebar/sr-2.jpg" alt="">
                                </div>
                                <div class="blog__sidebar__recent__item__text">
                                    <h6>Tips You To Balance<br /> Nutrition Meal Day</h6>
                                    <span>MAR 05, 2019</span>
                                </div>
                            </a>
                            <a href="#" class="blog__sidebar__recent__item">
                                <div class="blog__sidebar__recent__item__pic">
                                    <img src="/template/img/blog/sidebar/sr-3.jpg" alt="">
                                </div>
                                <div class="blog__sidebar__recent__item__text">
                                    <h6>4 Principles Help You Lose <br />Weight With Vegetables</h6>
                                    <span>MAR 05, 2019</span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="blog__sidebar__item">
                        <h4>Search By</h4>
                        <div class="blog__sidebar__item__tags">
                            <a href="#">Apple</a>
                            <a href="#">Beauty</a>
                            <a href="#">Vegetables</a>
                            <a href="#">Fruit</a>
                            <a href="#">Healthy Food</a>
                            <a href="#">Lifestyle</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-9 col-md-7 order-md-1 order-1" style="width: 840px;">
                <div class="blog__details__text">

                </div>
                <div class="blog__details__content">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="blog__details__author">

                                <div>
                                    <table class="table">
                                        <thead>
                                        <tr style="background-color: #c5c5c5">
                                            <th>No</th>
                                            <th>문의유형</th>
                                            <th>문의글</th>
                                            <th>등록일</th>
                                            <th>작성자</th>
                                            <th>답변상태</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach var="dto" items="${questionlist.content }">
                                            <tr>
                                                <td>${questionlist.totalElements - (questionlist.number * questionlist.size) - questionlist.content.indexOf(dto)}</td>
                                                <td>${dto.inquiryType}/${dto.detailType}</td>

                                                <!-- 비밀글 표시 -->
                                                <c:if test="${dto.secret == true}">
                                                    <c:choose>
                                                        <%-- <c:when test="${dto.member.userId eq member.userid || member.role eq '[ROLE_ADMIN, ROLE_USER]'}"> --%>
                                                        <c:when test="${dto.memberId eq sessionDTO.id || sessionDTO.role eq 'ADMIN'}">
                                                            <%--|| sessionDTO.role eq 'ROLE_ADMIN'--%>
                                                            <!-- 작성자이거나 관리자일 때 볼 수 있는 링크 -->
                                                            <td>Q <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}" style="color: #5a6268">
                                                                <c:out value="${dto.inquirySubject}"/><c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                                            </a></td>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <td class="text-secondary"><i class="icofont-lock"></i>
                                                                🔒<c:out value="${dto.inquirySubject}"/><c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>

                                                <c:if test="${dto.secret == false}">
                                                    <td>
                                                        <a href="/inquiry/article/${dto.inquiryId}?field=${param.field}&word=${param.word}&page=${param.page}" style="color: #5a6268">
                                                            Q ${dto.inquirySubject}
                                                            <c:if test="${dto.answer.size() != 0}">[${dto.answer.size()}]</c:if>
                                                        </a>
                                                    </td>
                                                </c:if>

                                                <td>${dto.inquiryDate}</td>
                                                <td>${dto.memberId}</td>
                                                <td>${dto.status}</td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>

                                <!-- 페이징 -->
                                <div class="text-xs-center">
                                    <ul class="pagination justify-content-center" id="pageButton">

                                        <!-- 이전 -->
                                        <c:choose>
                                            <c:when test="${questionlist.first}"></c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a class="page-link" href="/inquiry/list/?field=${field}&word=${word}&page=0">처음으로</a></li>
                                                <li class="page-item"><a class="page-link" href="/inquiry/list/?field=${field}&word=${word}&page=${questionlist.number-1}">◀</a></li>
                                            </c:otherwise>
                                        </c:choose>

                                        <!-- 페이지 그룹 -->
                                        <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                                            <c:choose>
                                                <c:when test="${questionlist.pageable.pageNumber+1 == i}">
                                                    <li class="page-item disabled"><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                        <!-- 다음 -->
                                        <c:choose>
                                            <c:when test="${questionlist.last}"></c:when>
                                            <c:otherwise>
                                                <li class="page-item "><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.number+1}">▶</a></li>
                                                <li class="page-item "><a class="page-link" href="/inquiry/list/?field=${param.field}&word=${param.word}&page=${questionlist.totalPages-1}">끝으로</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </div>
                                <!-- 페이징 끝 -->


                                <div style="display: flex; float: right">
                                    <c:if test="${sessionDTO.nickname != null}">
                                        <form action="/member/logout" method="post">
                                            <button class="site-btn" style="background-color: #e4606d" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">LOGOUT</button>
                                        </form></c:if>

                                    <button class="site-btn" onclick="location.href='/inquiry/created'" type="button">1:1 문의 등록</button>
                                    <c:if test="${sessionDTO.nickname == null}">
                                        <button class="site-btn" onclick="location.href='/member/login'" type="button">LOGIN</button>
                                    </c:if>
                                </div><br/>


                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Blog Details Section End -->
