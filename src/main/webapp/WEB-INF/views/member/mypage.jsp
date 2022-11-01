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
                    <img class="rounded-circle mt-5" src="/img/member/${memberDTO.memberImageName}">
                    <span class="font-weight-bold">${sessionDTO.userId}</span>
                    <span class="text-black-50">${sessionDTO.nickname}</span>
                </div>
                <div>
                    <span>
                        <ul class="list_modify">
                            <li><button type="button" onclick="location.href='/member/modifyProfileImg'" class="site-btn-modify">사진변경</button></li>
                            <li><button type="button" onclick="location.href='/member/preModify'" class="site-btn-modify">회원정보 수정</button></li>
                            <li><button type="button" onclick="location.href='/follow/list'" class="site-btn-modify">팔로우 리스트</button></li>
                            <li><button type="button" onclick="location.href='/block/list'" class="site-btn-modify">차단 리스트</button></li>
                            <li><button type="button" onclick="location.href='/member/withdraw'" class="site-btn-modify">회원탈퇴</button></li>
                        </ul>
                    </span>
                </div>
            </div>
            <div class="col-md-6">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-item s-center mb-3">
                        <h4 class="text-right">프로필</h4>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">아이디</label><input type="text" class="form-control" value="${sessionDTO.userId}"></div>
                        <div class="col-md-12"><label class="labels">닉네임</label><input type="text" class="form-control" value="${sessionDTO.nickname}"></div>
                        <div class="col-md-12"><label class="labels">이름</label><input type="text" class="form-control" value="${memberDTO.name}"></div>
                        <div class="col-md-12"><label class="labels">생년월일</label><br><span>${memberDTO.birth}</span></div>
                        <div class="col-md-12"><label class="labels">휴대폰 번호</label><input type="text" class="form-control" value="${memberDTO.tel}"></div>
                        <div class="col-md-12"><label class="labels">우편번호</label><input type="text" class="form-control" value="${memberDTO.postcode}"></div>
                        <div class="col-md-12"><label class="labels">주소</label><input type="text" class="form-control" value="${memberDTO.address}"></div>
                        <div class="col-md-12"><label class="labels">상세주소</label><input type="text" class="form-control" value="${memberDTO.detailAddress}"></div>
                        <div class="col-md-12"><label class="labels">참고사항</label><input type="text" class="form-control" value="${memberDTO.extraAddress}"></div>
                        <div class="col-md-12"><label class="labels">이메일</label><input type="text" class="form-control" value="${memberDTO.email}"></div>
                        <div class="col-md-12"><label class="labels">양파페이</label><input type="text" class="form-control" value="${memberDTO.cash}"></div>
                        <div class="col-md-12"><label class="labels">포인트</label><input type="text" class="form-control" value="${memberDTO.point}"></div>
                        <div class="col-md-12"><label class="labels">평점</label><input type="text" class="form-control" value=""></div>
                    </div>
                        <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button" onclick="location.href='/'">돌아가기</button></div>
                </div>
            </div>
        </div>
    </div>
</section>
