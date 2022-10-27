
function selectType(){
    let inquiryType = $("#inquiryType").val();

    $("#type_회원정보").hide();
    $("#type_거래").hide();
    $("#type_기타서비스").hide();

    $("#type_" + inquiryType).show();

}
function selectDetail(e){
    let val = e.value;
    document.getElementById('detail').value = val;
}