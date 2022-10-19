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
        } else if(!/(?=.*[a-z])(?=.*[0-9]).{4,20}/.test(data.pwd)) {
            alert("비밀번호는 4~20자리 영문 소문자, 숫자를 사용해 주세요.");
            $('#pwd').focus();
            return false;
        } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.nickname)) {
            alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
            $('#nickname').focus();

            $('#email').focus();
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: "PUT",
                url: "/api/member",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
                success: function (res) {
                    alert("회원정보 수정이 완료되었습니다.");
                    window.location.href = "/member/mypage";
                },
                error: function (e) {
                    alert("이미 사용중인 닉네임입니다. 다시 입력해 주세요.");
                }

            }).done(function () {
                // alert("회원정보 수정이 완료되었습니다.");
                // window.location.href = "/member/mypage";

            }).fail(function (error) {
                // alert(JSON.stringify(error));
            });
        } else {
            return false;
        }
    }
};

main.init();