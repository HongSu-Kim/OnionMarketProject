function addComplain() {
    var complainType = $("#complainType option:selected").val();
    complainType = $.trim(complainType);
    if (complainType == "") {
        alert("신고유형을 선택해주세요");
        return;
    }
    $("#myForm").validate({
        rules: {
            complainContent: {
                required: true,
            }
        },
        messages: {
            complainContent: {
                required: "내용을 작성해주세요!",
            }
        },
        errorElement: "label",
        errorPlacement: function (error, element) {
            error.insertAfter(element);
            error.css("font-weight", "bold");
            error.css("color", "#DB0000");
        }
    })
}