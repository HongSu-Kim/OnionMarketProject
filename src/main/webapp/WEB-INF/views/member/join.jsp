<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>Join</title>

</head>
<body onload="document.myForm.userId.focus();">

<h1>회원 가입</h1>
<hr>
<div id="posts_list">
    <div class="container col-md-4">
        <form:form action="/member/joinProc" method="post" name="myForm" modelAttribute="memberJoinDTO">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="form-group">
                <label>아이디</label>
                <input type="text" name="userId" value="${memberJoinDTO.userId}" class="form-control" placeholder="아이디를 입력해주세요"/>
                <span id="valid_userId">${valid_userId}</span>
            </div>

            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" name="pwd" id="pwd" value="${memberJoinDTO.pwd}" class="form-control" placeholder="비밀번호를 입력해주세요"/>
                <span id="valid_pwd">${valid_pwd}</span>
            </div>

            <div class="form-group">
                <label>비밀번호 재확인</label>
                <input type="password" name="pwd" id="pwdCheck" value="${memberJoinDTO.pwd}" class="form-control" placeholder="비밀번호를 입력해주세요"/>
            </div>

            <div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
            <div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>

            <div class="form-group">
                <label>이름</label>
                <input type="text" name="name" value="${memberJoinDTO.name}" class="form-control" placeholder="이름을 입력해주세요"/>
                <span id="valid_name">${valid_name}</span>
            </div>

            <div class="form-group">
                <label>닉네임</label>
                <input type="text" name="nickname" value="${memberJoinDTO.nickname}" class="form-control" placeholder="닉네임을 입력해주세요"/>
                <span id="valid_nickname">${valid_nickname}</span>
            </div>

            <div class="form-group">
                <label>생년월일</label>
                <input type="date" name="birth" value="${memberJoinDTO.birth}" class="form-control" placeholder="생년월일을 입력해주세요"/>
                <span id="valid_birth">${valid_birth}</span>
            </div>

            <div class="form-group">
                <label>휴대폰 번호</label>
                <input type="text" name="tel" value="${memberJoinDTO.tel}" class="form-control" placeholder="휴대폰 번호를 입력해주세요"/>
                <span id="valid_tel">${valid_tel}</span>
            </div>

            <div class="form-group">
                <label>우편번호</label> &nbsp; <input type="button" value="우편번호 찾기" id="postcodeBtn" onclick="sample6_execDaumPostcode()">

                <input type="text" name="postcode" id="postcode" value="${memberJoinDTO.postcode}" class="form-control" placeholder="우편번호를 입력해주세요" readonly="readonly"/>
                <span id="valid_postcode">${valid_postcode}</span>
            </div>

            <div class="form-group">
                <label>주소</label>
                <input type="text" name="address" id="address" value="${memberJoinDTO.address}" class="form-control" placeholder="주소를 입력해주세요" readonly="readonly"/>
                <span id="valid_address">${valid_address}</span>
            </div>

            <div class="form-group">
                <label>상세주소</label>
                <input type="text" name="detailAddress" id="detailAddress" value="${memberJoinDTO.detailAddress}" class="form-control" placeholder="상세주소를 입력해주세요"/>
                <span id="valid_detailAddress">${valid_detailAddress}</span>
            </div>

            <div class="form-group">
                <label>참고항목</label>
                <input type="text" name="extraAddress" id="extraAddress" value="${memberJoinDTO.extraAddress}" class="form-control" placeholder="참고항목을 입력해주세요"/>
            </div>

            <div class="form-group">
                <label>이메일</label>
                <input type="text" name="email" value="${memberJoinDTO.email}" class="form-control" placeholder="이메일을 입력해주세요"/>
                <span id="valid_email">${valid_email}</span>
            </div>


            <button type="submit" id="submit" class="btn btn-primary bi bi-person">가입</button>
            <a href="/member/login" role="button" class="btn btn-info bi bi-arrow-return-left">돌아가기</a>
        </form:form>
    </div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>