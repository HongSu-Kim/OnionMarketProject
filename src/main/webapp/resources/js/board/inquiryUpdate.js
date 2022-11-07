
$(function(){
    $('#detailType').hide();
});
function selectType(type , select) {
    $('#detailType').empty();


    if (type == '회원정보') {
        $('#detailType').append("<option value='회원가입,정보수정' >회원가입,정보수정</option>");
        $('#detailType').append("<option value='아이디,비밀번호' >아이디,비밀번호</option>'");
        $('#detailType').append("<option value='로그인' >로그인</option>");

    } else if (type == '거래') {
        $('#detailType').append("<option value='거래방법' >거래방법</option>");
        $('#detailType').append("<option value='거래내역' >거래내역</option>");
        $('#detailType').append("<option value='상품찾기' >상품찾기</option>");
        $('#detailType').append("<option value='거래확정,후기' >거래확정,후기</option>");
    } else if (type == '기타서비스') {
        $('#detailType').append("<option value='양파페이,포인트' >양파페이,포인트</option>");
        $('#detailType').append("<option value='경매이용' >경매이용</option>");
        $('#detailType').append("<option value='채팅이용' >채팅이용</option>");
    }
    document.getElementById("detailType").style.display = "";

    if ($.trim(select) != "") {
        $('#select1').val(type);
        $('#detailType').val(select);
    }
}




