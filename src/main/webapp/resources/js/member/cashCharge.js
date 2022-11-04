$('#charge_kakao').click(function () {
    // getter
    const IMP = window.IMP;
    IMP.init('imp88641673');
    const cash = $('input[name="cp_item"]:checked').val();
    console.log(cash);

    IMP.request_pay({
        pg: "html5_inicis", // (html5_inicis - 이니시스웹표준)
        pay_method: "card", // 결제방식
        merchant_uid: 'merchant_' + new Date().getTime(),

        name: 'ONION MARKET 양파페이 충전',
        amount: cash,
        buyer_email: 'youpriceonion@gmail.com',
        buyer_name: '구매자이름',
        buyer_tel: '010-3104-7411',
        buyer_addr: '서울시 강남구',
        buyer_postcode: '123-456'
    }, function (rsp) {
        let msg;
        console.log(rsp);
        if (rsp.success) {
            msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;
            $.ajax({
                type: "POST",
                url: "/member/charge/cash", //충전 금액값을 보낼 url 설정
                data: {
                    "amount": cash
                },
                beforeSend: function (jqXHR) {
                    jqXHR.setRequestHeader(header, token);
                },
                success: function (url) {
                    location.href=url
                },
            });
        } else {
            msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
        document.location.href = window.location.href; //alert창 확인 후 이동할 url 설정
    });
});