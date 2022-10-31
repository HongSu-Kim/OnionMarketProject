$(function () {

	const IMP = window.IMP;
	IMP.init("imp88641673");

	// let header = $("meta[name='_csrf_header']").attr("content");
	// let token = $("meta[name='_csrf']").attr("content");

	// 배송 받기
	$('#delivery-true').click(function () {
		$('#delivery').val("true")
		$('#deliveryInfo').removeClass("d-none")
		$('#deliveryCost-group').removeClass("d-none")
		$('#delivery-true').addClass("d-none")
		$('#totalPrice').html(parseInt($('#totalPrice').html()) + 3000)
	})

	// 직거래
	$('#delivery-false').click(function () {
		$('#delivery').val("false")
		$('#deliveryInfo').addClass("d-none")
		$('#deliveryCost-group').addClass("d-none")
		$('#delivery-true').removeClass("d-none")
		$('#totalPrice').html(parseInt($('#totalPrice').html()) - 3000)
	})

	// 등록된 주소
	$('#memberAddr').click(function () {
		$('#postcode').val($('#hiddenPostcode').val())
		$('#address').val($('#hiddenAddress').val())
		$('#detailAddress').val($('#hiddenDetailAddress').val())
		$('#extraAddress').val($('#hiddenExtraAddress').val())
	})

	// 결제 - 양파페이
	$('#payment').click(function () {
		if (validate())
			return

		$('#orderPayment').val($('#totalPrice').html())
		$('#form').submit()
	})

	// imp 결제
	$('#imp-payment').click(function() {
		if (validate())
			return

		let form = new FormData();

		$('#orderPayment').val($('#totalPrice').html())

		let param = {
			pg: "html5_inicis", // (html5_inicis - 이니시스웹표준)
			pay_method: "card", // 결제방식
			merchant_uid: $('#orderNum').val(), // 주문번호
			name: $('#subject').val(), // 상품명
			amount: parseInt($('#totalPrice').html()), // 결제 금액
			buyer_name: $('#name').val(), // 주문자명
			buyer_tel: $('#tel').val(), // 주문자 연락처
			buyer_email: $('#email').val(), // 주문자 이메일
		}

		if ($('#delivery').val() == "true") {
			param.buyer_addr = $('#address').val() // 주문자 주소
			param.buyer_postcode = $('#postcode').val() // 주문자 우편 번호
		}

		// IMP.request_pay(param, callback) 결제창 호출
		IMP.request_pay(param, function(rsp) { // callback
			if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
				// jQuery로 HTTP 요청
				$.ajax({
					url: "/order/imp-payment",
					method: "POST",
					contentType: 'application/json',
					data: JSON.stringify ({
						imp_uid: rsp.imp_uid, // 결제번호
						orderNum: rsp.merchant_uid, // 주문번호
						memberId: parseInt($('#memberId').val()), // 회원번호
						productId: parseInt($('#productId').val()), // 상품번호
						orderPayment: rsp.paid_amount, // 주문가격
						delivery: $('#delivery').val(), // 배송여부
						deliveryCost: parseInt($('#deliveryCost').val()), // 배송비
						recipient: $('#recipient').val(), // 받는사람
						deliveryTel: $('#deliveryTel').val(), // 연락처
						address: $('#address').val(), // 주문자 주소
						postcode: $('#postcode').val(), // 주문자 우편번호
						detailAddress: $('#detailAddress').val(), // 주문자 상세주소
						extraAddress: $('#extraAddress').val(), // 참고사항
						request: $('#request').val(), // 요구사항
					}),
					beforeSend: function (jqXHR) {
						jqXHR.setRequestHeader(header, token);
					},
					success: function (url) { // DB 입력 성공
						location.href = url
					},
					error: function (error) { // DB 입력중 오류
						alert(error.responseText)
						location.href = "/"
					}
				}).done(function (data) {
					// 가맹점 서버 결제 API 성공시 로직
				})
			} else {
				alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
			}
		});
	})

	// 유효성 검사
	function validate() {
		$('.err-msg').remove()

		if ($('#delivery').val() == "true") {
			let result = false

			if (!$('#recipient').val()) {
				$('#recipient').before('<span class="err-msg">받을 사람을 입력해주세요.</span>')
				result = true;
			}

			if (!$('#deliveryTel').val()) {
				$('#deliveryTel').before('<span class="err-msg">연락처를 입력해주세요.</span>')
				result = true;
			}

			let postcodeRegex = /^[0-9]{5}$/
			if (!$('#postcode').val()) {
				$('#postcode').before('<span class="err-msg">우편번호를 입력해주세요.</span>')
				result = true;
			} else if (!postcodeRegex.test($('#postcode').val())) {
				$('#postcode').before('<span class="err-msg">우편번호 형식이 맞지않습니다.</span>')
				result = true;
			}

			if (!$('#address').val()) {
				$('#address').before('<span class="err-msg">주소를 입력해주세요.</span>')
				result = true;
			}

			if (!$('#detailAddress').val()) {
				$('#detailAddress').before('<span class="err-msg">참고항목을 입력해주세요.</span>')
				result = true;
			}
			if (result) return result
		}

		if (!$('#acc-or').is(':checked')) {
			alert("결제정보를 확인해주세요")
			return true
		}
	}

	// 주소
	function sample6_execDaumPostcode() {
		new daum.Postcode({
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if(data.userSelectedType === 'R'){
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if(data.buildingName !== '' && data.apartment === 'Y'){
						extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if(extraAddr !== ''){
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
					document.getElementById("extraAddress").value = extraAddr;

				} else {
					document.getElementById("extraAddress").value = '';
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('postcode').value = data.zonecode;
				document.getElementById("address").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("detailAddress").focus();
			}
		}).open();
	}

})