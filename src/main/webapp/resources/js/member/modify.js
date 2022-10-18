const main = {
    init : function() {
        const _this = this;

        // 회원 수정
        $('#btn-member-modify').on('click', function () {
            _this.memberModify();
        });
    },

    /** 회원 수정 */
    memberModify : function () {
        const data = {
            id: $('#id').val(),
            userId: $('#userId').val(),
            pwd: $('#pwd').val(),
            nickname: $('#nickname').val(),
            tel: $('#tel').val(),
            postcode: $('#postcode').val(),
            address: $('#address').val(),
            detailAddress: $('#detailAddress').val(),
            extraAddress: $('#extraAddress').val(),
            email: $('#email').val()

        }
        if(!data.pwd || data.pwd.trim() === "" || !data.nickname || data.nickname.trim() === "" || !data.tel || data.tel.trim() === "" || !data.postcode || data.postcode.trim() === "" || !data.address || data.address.trim() === "" || !data.detailAddress || data.detailAddress.trim() === "" || !data.email || data.email.trim() === "") {
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
        } else if(!/(01[016789])(\d{3,4})(\d{4})/.test(data.tel)) {
            alert("휴대폰 번호 형식이 올바르지 않습니다.");
            $('#tel').focus();
            return false;
        } else if(!/^.{5}$/.test(data.postcode)) {
            alert("우편번호 5자리를 입력해 주세요.");
            $('#postcode').focus();
            return false;
        } else if(!/.+/.test(data.address)) {
            alert("주소는 필수 입력값입니다.");
            $('#address').focus();
            return false;
        } else if(!/.+/.test(data.detailAddress)) {
            alert("상세주소는 필수 입력값입니다.");
            $('#detailAddress').focus();
            return false;
        } else if(!/^(?:\w+\.?)*\w+@(?:\w+\.)+\w+$/.test(data.email)) {
            alert("이메일 형식이 올바르지 않습니다.");
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