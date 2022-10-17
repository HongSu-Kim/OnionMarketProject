<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript">

        const main = {
            init : function() {
                const _this = this;

                // 회원 수정
                $('#btn-user-modify').on('click', function () {
                    _this.userModify();
                });
            },

            /** 회원 수정 */
            userModify : function () {
                const data = {
                    id: $('#id').val(),
                    username: $('#userId').val(),
                    password: $('#pwd').val(),
                    nickname: $('#nickname').val(),
                    tel: $('#tel').val(),
                    postcode: $('#postcode').val(),
                    address: $('#address').val(),
                    detailAddress: $('#detailAddress').val(),
                    extraAddress: $('#extraAddress').val(),
                    email: $('#email').val()

                }
                if(!data.password || data.password.trim() === "" || !data.nickname || data.nickname.trim() === "" || !data.tel || data.tel.trim() === "" || !data.postcode || data.postcode.trim() === "" || !data.address || data.address.trim() === "" || !data.detailAddress || data.detailAddress.trim() === "" || !data.email || data.email.trim() === "") {
                    alert("공백 또는 입력하지 않은 부분이 있습니다.");
                    return false;
                } else if(!/(?=.*[a-z])(?=.*[0-9]).{4,20}/.test(data.password)) {
                    alert("비밀번호는 4~20자리 영문 소문자, 숫자를 사용해 주세요.");
                    $('#password').focus();
                    return false;
                } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.nickname)) {
                    alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
                    $('#nickname').focus();
                    return false;
                } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.tel)) {
                    alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
                    $('#tel').focus();
                    return false;
                } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.postcode)) {
                    alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
                    $('#postcode').focus();
                    return false;
                } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.address)) {
                    alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
                    $('#address').focus();
                    return false;
                } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.detailAddress)) {
                    alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
                    $('#detailAddress').focus();
                    return false;
                } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.email)) {
                    alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
                    $('#email').focus();
                    return false;
                }
                const con_check = confirm("수정하시겠습니까?");
                if (con_check === true) {
                    $.ajax({
                        type: "PUT",
                        url: "/api/member",
                        contentType: 'application/json; charset=utf-8',
                        data: JSON.stringify(data)

                    }).done(function () {
                        alert("회원정보 수정이 완료되었습니다.");
                        window.location.href = "/";

                    }).fail(function (error) {
                        if (error.status === 500) {
                            alert("이미 사용중인 닉네임 입니다.");
                            $('#nickname').focus();
                        } else {
                            alert(JSON.stringify(error));
                        }
                    });
                } else {
                    return false;
                }
            }
        };

        main.init();

    </script>


    <title>회원정보 수정</title>

</head>


<body>

<div id="posts_list">
    <div class="container col-md-4">
        <form>
            <label for="id"></label>
            <input type="hidden" id="id" value="${member.id}"/>
            <div class="form-group">
                <label for="userId">아이디</label>
                <input type="text" id="userId" value="${member.userId}" class="form-control" readonly/>
            </div>

            <div class="form-group">
                <label for="pwd">비밀번호</label>
                <input type="password" id="pwd" class="form-control" placeholder="수정할 비밀번호를 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="nickname">닉네임</label>
                <input type="text" id="nickname" value="${member.nickname}" class="form-control" placeholder="수정할 닉네임을 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="nickname">휴대전화 번호</label>
                <input type="text" id="tel" value="${member.tel}" class="form-control" placeholder="수정할 휴대전화 번호를 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="nickname">우편번호</label>
                <input type="text" id="postcode" value="${member.postcode}" class="form-control" placeholder="수정할 우편번호를 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="nickname">주소</label>
                <input type="text" id="address" value="${member.address}" class="form-control" placeholder="수정할 주소를 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="nickname">상세주소</label>
                <input type="text" id="detailAddress" value="${member.detailAddress}" class="form-control" placeholder="수정할 상세주소를 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="nickname">주소 추가사항</label>
                <input type="text" id="extraAddress" value="${member.extraAddress}" class="form-control" placeholder="수정할 주소 추가사항을 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="email" value="${member.email}" class="form-control" placeholder="수정할 이메일을 입력해 주세요."/>
            </div>

            <div class="form-group">
                <label for="memberImageName">프로필 사진</label>
                <input type="text" id="memberImageName" value="${member.memberImageName}" class="form-control" placeholder="수정할 프로필 사진을 설정해 주세요."/>
            </div>

        </form>
        <button id="btn-user-modify" class="btn btn-primary bi bi-check-lg"> 완료</button>
        <a href="/member/" role="button" class="btn btn-info bi bi-arrow-return-left"> 목록</a>
    </div>
</div>

</body>
</html>