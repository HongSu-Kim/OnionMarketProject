//비밀번호 확인
$(function(){
    $("#alert-success").hide();
    $("#alert-danger").hide();
    $("input").keyup(function(){
        var pwd=$("#pwd").val();
        var pwdCheck=$("#pwdCheck").val();
        if(pwd != "" || pwdCheck != ""){
            if(pwd == pwdCheck){
                $("#alert-success").show();
                $("#alert-danger").hide();
                $("#btn-member-modify").removeAttr("disabled");
            }else{
                $("#alert-success").hide();
                $("#alert-danger").show();
                $("#btn-member-modify").attr("disabled", "disabled");
            }
        }
    });
});

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
            return false;
        } else if (!/^(?:\w+\.?)*\w+@(?:\w+\.)+\w+$/.test(data.email)) {
            alert("이메일 형식이 올바르지 않습니다.");
            $('#email').focus();
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
                    alert("error!!!!!!");
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

//프로필 미리보기
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('preview').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        document.getElementById('preview').src = "";
    }
}

// 주소
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;

            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}