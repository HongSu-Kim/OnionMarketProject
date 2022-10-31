function selectType() {
    let inquiryType = $("#inquiryType").val();

    $("#type_회원정보").hide();
    $("#type_거래").hide();
    $("#type_기타서비스").hide();

    $("#type_" + inquiryType).show();
}

$(document).ready(function () {
    if ($("#detail").val() != "") {
        $("#formD").show();
    }
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

