function selectType() {
    let inquiryType = $("#inquiryType").val();

    $("#type_회원정보").hide();
    $("#type_거래").hide();
    $("#type_기타서비스").hide();

    $("#type_" + inquiryType).show();
}

$(document).ready(function () {

});

function selectDetail(e) {
    let val = e.value;
    document.getElementById('detail').value = val;
}

function inquirysend() {
    var inquiryType = $("#inquiryType").val();

    if (inquiryType == "") {
        alert("문의유형을 선택해주세요");
        return;
    } else if ($("#detail").val() == "") {
        alert("상세유형을 선택해주세요");
        return;
    }

}
$(document).ready(function () {
    //$('#registerForm').validate(); //유효성 검사를 적용
    // validate signup form on keyup and submit
    $('#inquiryForm').validate({
        rules: {
            agreement: "required"
        },
        messages: {
            agreement: {
                required: "개인정보 수집에 동의해주세요."
            }
        },
        errorPlacement : function (error, element){
            if(element.is(':checkBox')){
                element.parent().after(error);
            } else {
                element.after(error);
            }
        }

    });
});
