function addComplain() {
    var complainType = $("#complainType option:selected").val();
    complainType = $.trim(complainType);
    if (complainType == "") {
        alert("신고유형을 선택해주세요");
        return;
    }
}