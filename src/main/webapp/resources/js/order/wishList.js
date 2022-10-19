
let header = $("meta[name='_csrf_header']").attr("content");
let token = $("meta[name='_csrf']").attr("content");

$('#wishBtn').click(function () {
    if ($(this).attr("class") == "true") {
        $.ajax({
            url: "/wish/addWish",
            method: "POST",
            data: ({
                productId: $(this).siblings('#wishId').val()
            }),
            beforeSend: function (jqXHR) {
                jqXHR.setRequestHeader(header, token);
            },
            success: function (data) {
                alert("success" + data)
            },
            error: function (e) {
                alert("error" + e)
            }
        })
    } else {
        alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
    }
})