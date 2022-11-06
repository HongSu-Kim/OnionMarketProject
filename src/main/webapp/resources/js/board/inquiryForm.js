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

    if ($("input[name=agreement]:checkbox").is(":checked")) {
        $("input[name=agreement]:checkbox").val("checked", "checked");
    }

    if($('#inquiryType').val() == ''){

    }
}


$(document).ready(function () {

    $('#inquiryForm').validate({

        rules: {
            inquiryType:{
                valueNotEquals: ""
            },
            inquirySubject: {
                required: true,
            },
            inquiryContent: {
                required: true
            },
            agreement : "required"
        },
        messages: {
            inquiryType:{
                valueNotEquals: "유형을 선택해주세요."
            },
            inquirySubject: {
                required: "제목을 입력해주세요.",
            },
            inquiryContent: {
                required: "내용을 작성해주세요.",
            },
            agreement : {
                required: "개인정보 이용에 동의해주세요",
            },
        },

        errorElement: "label",
        errorPlacement: function (error, element){
            error.appendTo( element.parent().prev() );
        }
    });


    jQuery.validator.addMethod(
        "select",
        function(value, element) {
            if (element.value == "")
            {
                return false;
            }
            else return true;
        },
        "Please select an option."
    );

});


