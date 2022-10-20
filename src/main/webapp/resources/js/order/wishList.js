
let header = $("meta[name='_csrf_header']").attr("content");
let token = $("meta[name='_csrf']").attr("content");

$('#wishBtn').click(function () {
    let wishBtn = $(this)

    if (wishBtn.attr("class") != "true") {
        $.ajax({
            url: "/wish/addWish",
            method: "POST",
            data: ({
                productId: wishBtn.siblings('#productId').val()
            }),
            beforeSend: function (jqXHR) {
                jqXHR.setRequestHeader(header, token);
            },
            success: function (msg) {
                wishBtn.addClass("true")
                alert(msg)
            },
            error: function (e) {
                wishBtn.addClass("true")
                alert("error")
            }
        })
    } else {
        if (confirm('찜 목록에서 삭제하시겠습니까?')) {
            $.ajax({
                url: "/wish/removeWish",
                method: "DELETE",
                data: ({
                    wishId: wishBtn.siblings('#wishId').val()
                }),
                beforeSend: function (jqXHR) {
                    jqXHR.setRequestHeader(header, token);
                },
                success: function (msg) {
                    wishBtn.removeClass("true")
                    alert(msg)
                },
                error: function (e) {
                    wishBtn.removeClass("true")
                    alert("error")
                }
            })
        }
    }
})