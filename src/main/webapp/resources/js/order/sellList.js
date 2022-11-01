$('.progressUpdate').click(function () {
    let productId = $(this).siblings('#productId').val()
    let pageNumber = $('#pageNumber').val()

    let productProgress
    let str

    switch ($(this).html()) {
        case '판매중' :
            productProgress = 'SALESON'
            str = '상태를 판매중으로 변경하시겠습니까?'; break
        case '예약중' :
            productProgress = 'RESERVED'
            str = '상태를 예약중으로 변경하시겠습니까?'; break
        case '거래중' :
            productProgress = 'TRADINGS'
            str = '상태를 거래중으로 변경하시겠습니까?'; break
        case '판매완료' :
            productProgress = 'SOLDOUT'
            str = false
    }

    if (str && confirm(str)) {
        location.href ='/product/progressUpdate/' + productId + '/' + productProgress + '/' + pageNumber
    }
})