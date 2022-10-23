/*
$("#checkEmail").click(function () {
    let userId = $("#userId").val();
    let email = $("#email").val();

    $.ajax({
        type: "GET",
        url: "/member/findPwd",
        data: {
            "userId": userId,
            "email": email
        },
        success: function (res) {
            if (res['check']) {
                swal("발송 완료!", "입력하신 이메일로 임시 비밀번호가 발송되었습니다.", "success").then((OK)=> {
                    if (OK) {
                        $.ajax({
                            type: "POST",
                            url: "/member/findPwd/sendEmail",
                            data: {
                                "userId": userId,
                                "email": email
                            }
                        })
                        window.location = "/member/login";
                    }
                })
                $('#checkMsg').html('<p style="color:darkblue"></p>');
            } else {
                $('#checkMsg').html('<p style="color:red">일치하는 정보가 없습니다.</p>');
            }
        }
    })
});*/
