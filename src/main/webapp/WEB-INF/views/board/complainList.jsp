<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="section-title">
                <h3>신고 처리 목록</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-wrap">
                    <table class="table myaccordion table-hover" id="accordion">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th style="text-align: center">신고 유형</th>
                            <th style="text-align: center">처리 대상</th>
                            <th style="text-align: center">신고 접수일</th>
                            <th style="text-align: center">상태</th>
                            <th style="text-align: center">신고 처리</th>
                            <th style="text-align: center">내용 보기</th>
                        </tr>
                        </thead>


                        <tbody>
                        <c:if test="${empty complainList.content}">
                            <tr>
                                <td colspan="7" style="text-align: center">신고가 없습니다.</td>
                            </tr>
                        </c:if>

                        <c:forEach var="dto" items="${complainList.content }">
                            <tr data-toggle="collapse" data-target="#collapse${dto.complainId}" aria-expanded="false"
                                aria-controls="collapseTwo" class="collapsed">
                                <th scope="row">${complainList.totalElements - (complainList.number * complainList.size) - complainList.content.indexOf(dto)}</th>
                                <td style="text-align: center">${dto.complainType}</td>
                                <td style="text-align: center">${dto.targetDTO.userId}</td>
                                <td style="text-align: center">${dto.complainDate}</td>
                                <td style="text-align: center">
                                    <c:if test="${dto.status == 'wait'}">처리 대기</c:if>
                                    <c:if test="${dto.status == 'complete'}">처리 완료</c:if>
                                    <c:if test="${dto.status == 'clear'}">재검토 대상</c:if>
                                </td>
                                <td style="text-align: center" class="modifyStatus">
                                    <c:choose>
                                        <c:when test="${dto.status == 'complete'}">
                                            <a href="/complain/modify/${dto.complainId}?select=clear">취소</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/complain/modify/${dto.complainId}?select=complete">완료</a> /
                                            <a href="/complain/modify/${dto.complainId}?select=cancel">취소</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td style="text-align: center">
                                    <i class="fa" aria-hidden="false"></i>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="7" id="collapse${dto.complainId}" class="collapse acc"
                                    data-parent="#accordion">
                                    <div>
                                        <p>[ 신고 상품 ]</p>
                                        <a style="font-weight: bold">${dto.productFindDTO.categoryParentName}/${dto.productFindDTO.categoryName}</a>
                                    </div>

                                    <div style="display: inline-block">
                                        <div>- ${dto.productFindDTO.subject}</div>
                                    </div>

                                    <div style="margin-top: 20px;" class="d-flex">
                                        <div><img src="/img/product/${dto.productFindDTO.representativeImage}"
                                                  width="150" height="120" style="border-radius: 20px;"/>
                                        </div>
                                        <div style="margin-left: 30px">
                                            <a style="font-weight: bold;">${dto.productFindDTO.content}</a><br/><br/>

                                            [ 신고 내용 ]<br/>
                                            <p>: ${dto.complainContent}</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
