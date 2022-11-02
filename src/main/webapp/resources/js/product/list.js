// 찜 버튼
$('.wishBtn').click(function () {
    let wishBtn = $(this)

    // 찜 추가
    if (wishBtn.attr("class") != "wishBtn true") {
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
                wishBtn.siblings('#wishListSize').html(parseInt(wishBtn.siblings('#wishListSize').html()) + 1)
                alert(msg)
            },
            error: function (request)  {
                if (request.status == 401) {
                    location.href = request.responseText
                } else if (request.status == 403) {
                    alert(request.responseText)
                } else {
                    alert(request.status + " : " + request.responseText)
                }
            }
        })
    }
    // 찜 삭제
    else {
        if (confirm('찜 목록에서 삭제하시겠습니까?')) {
            $.ajax({
                url: "/wish/removeWish",
                method: "DELETE",
                data: ({
                    productId: wishBtn.siblings('#productId').val()
                }),
                beforeSend: function (jqXHR) {
                    jqXHR.setRequestHeader(header, token);
                },
                success: function (msg) {
                    wishBtn.removeClass("true")
                    wishBtn.siblings('#wishListSize').html(parseInt(wishBtn.siblings('#wishListSize').html()) - 1)
                    alert(msg)
                },
                error: function (request) {
                    if(request.status == 401) {
                        location.href = request.responseText
                    } else if (request.status == 403) {
                        alert(request.responseText)
                    } else {
                        alert(request.status + " : " + request.responseText)
                    }
                }
            })
        }
    }
})