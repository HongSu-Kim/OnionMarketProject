<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="spad">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <img onclick="window.open('/img/member/${memberDTO.memberImageName}', 'Profile', 'width=600, height=800, location=no, status=no, scrollbars=yes');" class="rounded-circle mt-5 profile" src="/img/member/${memberDTO.memberImageName}">
                    <span class="font-weight-bold">${memberDTO.nickname}</span>
                    <span class="text-black-50">${memberDTO.userId}</span>
                </div>
                <div>
                    <span>
                        <ul class="list_modify">
                                <li><button type="button" onclick="location.href='/member/modifyProfileImg'" class="site-btn-modify">사진 변경</button></li>
                                <li><button type="button" onclick="location.href='/member/preModify'" class="site-btn-modify">회원정보 수정</button></li>
                            <sec:authorize access="hasRole('USER')">
                                <li><button type="button" onclick="location.href='/follow/list'" class="site-btn-modify">팔로우 목록</button></li>
                                <li><button type="button" onclick="location.href='/block/list'" class="site-btn-modify">차단 목록</button></li>
                                <li><button type="button" onclick="location.href='/member/cash/<sec:authentication property="principal.sessionDTO.id"/>'" class="site-btn-modify">양파페이 충전</button></li>
                                <li><button type="button" onclick="location.href='/member/withdraw'" class="site-btn-modify">회원탈퇴</button></li>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                                <li><button type="button" onclick="location.href='/member/manageMember'" class="site-btn-modify">회원관리</button></li>
                            </sec:authorize>
                        </ul>
                    </span>
                </div>
            </div>
            <div class="col-md-6">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-item s-center mb-3">
                        <h4 class="text-right">마이 페이지</h4>
                    </div>
                    <div class="col-lg-12 col-md-12">
                        <div class="checkout__input"><label class="labels">아이디</label><input type="text" class="form-control" value="${memberDTO.userId}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">닉네임</label><input type="text" class="form-control" value="${memberDTO.nickname}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">이름</label><input type="text" class="form-control" value="${memberDTO.name}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">생년월일</label><input type="date" class="form-control" value="${memberDTO.birth}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">휴대폰 번호</label><input type="text" class="form-control" value="${memberDTO.tel}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">우편번호</label><input type="text" class="form-control" value="${memberDTO.postcode}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">주소</label><input type="text" class="form-control" value="${memberDTO.address}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">상세주소</label><input type="text" class="form-control" value="${memberDTO.detailAddress}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">참고사항</label><input type="text" class="form-control" value="${memberDTO.extraAddress}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">이메일</label><input type="text" class="form-control" value="${memberDTO.email}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">양파페이</label><input type="text" class="form-control" value="${memberDTO.cash}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">포인트</label><input type="text" class="form-control" value="${memberDTO.point}" readonly="readonly"></div>
                        <div class="checkout__input"><label class="labels">평점</label><input type="text" class="form-control" value="${memberDTO.userGrade}" readonly="readonly"></div>
                    </div>
                        <div class="mt-5 text-center"><button class="site-btn-modify" type="button" onclick="location.href='/'">홈으로</button></div>
                </div>
            </div>
        </div>
    </div>
</section>
