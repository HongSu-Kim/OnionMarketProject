
$(function () {

	let msg
	let productId
	let productProgress
	let pageNumber

	// select 값 설정
	$('#productProgress option').each(function () {
		if ($(this).val() == $('#progress').val()) {
			$(this).attr('selected', true)
			$('#productProgress').niceSelect('update');
		}
	})

	// 상품 상태별 검색
	$('#productProgress').change(function () {
		location.href = '/order/sellList?productProgress=' + $(this).val()
	})
	
	// 상품 상태 변경
	$('.progressUpdate').click(function () {
		let $btn = $(this)

		productId = $btn.siblings('#productId').val()
		pageNumber = $('#pageNumber').val()
		let progress = $btn.html()

		switch (progress) {
			case "판매중" :
				productProgress = ""
				msg = "상태를 판매중으로 변경하시겠습니까?"; break
			case "예약중" :
				productProgress = "RESERVED"
				msg = "상태를 예약중으로 변경하시겠습니까?"; break
			case "거래중" :
				productProgress = "TRADINGS"
				msg = "상태를 거래중으로 변경하시겠습니까?"; break
			case "판매완료" :
				productProgress = "SOLDOUT"
				msg = "상태를 판매완료로 변경하시겠습니까?"; break
		}

		if (productProgress == "SOLDOUT" && $btn.siblings('#orderId').val() == "") {
			openModal()
		} else {
			sendIt()
		}
	})

	// 상품 구매자 선택 모달창
	let openModal = () => {

		$.ajax({
			url: "/member/chatMemberList",
			method: "GET",
			success: (memberDTOList) => {
				$('#memberListArea').html("")


				for (let i = 0; i < memberDTOList.length; i++) {
					let memberDTO = memberDTOList[i]

					let str =
						`<div class="chat-room" id="member${memberDTO.id}">` +
						`   <span class="msg-avatar">` +
						`       <img src="/img/member/${memberDTO.memberImageName}">` +
						`   </span>` +
						`   <div class="cr-room-text">` +
						`		${memberDTO.nickname}` +
						`   </div>` +
						`<div style="border: 1px solid #ebebeb"></div>`

					$('#memberListArea').append(str)

					$('#member' + memberDTO.id).click(function () {
						selectMember(memberDTO.id)
					})
				}

				document.body.style.overflow = "hidden";
				$('.modal-shadow').css('display', 'block');
				$('.member-box').toggle('scale');
			},
			error: (request) => {
				if (request.status == 401) {
					location.href = request.responseText
				} else {
					alert(request.status + " : " + request.responseText)
				}
			}
		})
	}

	// 모달창 닫기
	$('.member-close-btn').click(function () {
		document.body.style.overflow = "auto";
		$('.member-box').toggle('scale');
		$('.modal-shadow').css('display', 'none');
	})

	// 구매자 선택
	let selectMember = (num) => { // num == memberId
		let result = confirm(msg)

		document.body.style.overflow = "auto";
		$('.member-box').toggle('scale');
		$('.modal-shadow').css('display', 'none');

		if (result) {
			location.href = `/order/orderComplete/${productId}/${num}/${pageNumber}`
		}
	}

	// 상품 상태 변경 submit
	let sendIt = () => {
		if (confirm(msg)) {
			location.href = `/product/progressUpdate/${productId}/${productProgress}/${pageNumber}`
		}
	}

})
