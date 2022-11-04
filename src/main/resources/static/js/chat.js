
const header = $("meta[name='_csrf_header']").attr("content");
const token = $("meta[name='_csrf']").attr("content");

const memberId = $('#memberId').val()
const memberNickname = $('#memberNickname').val()
const stomp = Stomp.over(new SockJS("/stomp/chat"));

let chatroomId
let targetId
let page
let hasNext
let lastDate

// data-target
$('.trigger').click(function () {
	let id = $(this).data("target")
	$('#' + id).click()
})


// 모달 변경
let modalChange = function (page) { // page - circle, list, room
	switch (page) {
		case "circle" :
			if($('#chat-circle').css("display") == 'none') {
				$('#chat-circle').toggle('scale');
				$('.chat-box').toggle('scale');
			}
			break
		case "list" :
			$('#chatList').css('z-index', 2)
			if($('#chatList').css("display") == 'none') {
				$('#chat-circle').toggle('scale');
				$('.chat-box').toggle('scale');
			}
			break
		case "room" :
			$('#chatList').css('z-index', 1)
			if($('#chatroom').css("display") == 'none') {
				$('#chat-circle').toggle('scale');
				$('.chat-box').toggle('scale');
			}
			break
	}
}


// 채팅 출력
let printChat = function () {

	$.ajax({
		url: "/chatroom/room/" + chatroomId + "?page=" + page,
		method: "GET",
		success: function (chatroomDTO) {

			if (chatroomDTO.memberDTO.id == memberId)
				targetId = chatroomDTO.productDTO.memberDTO.id
			else
				targetId = chatroomDTO.memberDTO.id
			page = chatroomDTO.page + 1
			hasNext = chatroomDTO.hasNext
			$('#title').html(chatroomDTO.productDTO.subject)

			let chatDTOList = chatroomDTO.chatDTOSlice.content
			let date = ""
			let str = ""
			$('.date:first').remove();

			if (chatDTOList.length == 0) {

			} else if (page == 1){
				lastDate = chatDTOList[chatDTOList.length - 1].sendingTime.substring(0, 10)
			}

			for (let i = 0; i < chatDTOList.length; i++) {
				let chatDTO = chatDTOList[i]

				if (date != chatDTO.sendingTime.substring(0, 10)) {
					date = chatDTO.sendingTime.substring(0, 10)

					let parseDate = new Date(chatDTO.sendingTime)
					let formatDate = `${parseDate.getFullYear()}년 ${parseDate.getMonth() + 1}월 ${parseDate.getDate()}일`

					str +=
						`<div class="chat-msg date">` +
						`   <div class="cm-msg-text">${formatDate}</div>` +
						`</div>`
				}

				str += chatTemplate(chatDTO)
			}
			$('#head').after(str)
		},
		error(request) {
			if (request.status == 401) {
				location.href = request.responseText
			} else {
				alert(request.status + " : " + request.responseText)
			}
		}
	})
}

// 채팅 템플릿
let chatTemplate = function (chatDTO) {

	let content

	if (chatDTO.message != null) {
		content = `<div class="cm-msg-text">${chatDTO.message}</div>`
	} else if (chatDTO.chatImageName != null) {
		content = `<img src="/img/chat/${chatDTO.chatImageName}"/>`
	} else {
		content = `<div class="cm-msg-box"><i class="material-icons">loop</i></div>`
	}

	let str =
		(chatDTO.memberId == memberId
			? `<div class="chat-msg self">`
			: `<div class="chat-msg user">`) +
		`   ${content}` +
		`   <div class="cm-msg-time">${chatDTO.sendingTime.substring(11, 19)}</div>` +
		`</div>`

	return str
}

// 채팅방 오픈
let openChatroom = function (num) { // num == chatroomId
	chatroomId = num
	page = 0

	$('#msgArea').html(
		`<div id="head"></div>` +
		`<div id="foot"></div>`
	)

	printChat()
	modalChange("room")

	setTimeout(function () {
		$('#msgArea').scrollTop($('#msgArea')[0].scrollHeight);
		$('#msg').focus()
	}, 50)
}

// 채팅 더보기
$('#msgArea').scroll(function() {
	if (page == 0) return

	let scrollTop = $(this).scrollTop()
	if (scrollTop < 1 && hasNext == true) {
		let view = $('.chat-msg ')
		printChat()
		view[0].scrollIntoView()
	}
})

// 채팅방 리스트 출력
let openChatroomList = function () {
	chatroomId = 0

	$.ajax({
		url: "/chatroom/list",
		method: "GET",
		success: function (chatroomDTOList) {
			$('#listArea').html("")

			for (let i = 0; i < chatroomDTOList.length; i++) {
				let chatroomDTO = chatroomDTOList[i]

				let content
				if (chatroomDTO.chatDTO == null) {
					content = "메세지가 없습니다."
				} else if (chatroomDTO.chatDTO.message != null) {
					content = `<span id="chatMessage${chatroomDTO.chatroomId}">${chatroomDTO.chatDTO.message}</span>`
				} else if (chatroomDTO.chatDTO.chatImageName != null) {
					content = "사진"
				} else {
					content = "오류"
				}

				let notRead = (chatroomDTO.notRead != 0 ? `<div class="not-read">${chatroomDTO.notRead}</div>` : "")

				let str =
					`<div class="chat-room" id="chatroom${chatroomDTO.chatroomId}">` +
					`	<span class="msg-avatar">` +
					`		<img src="/img/product/${chatroomDTO.productDTO.representativeImage}">` +
					`	</span>` +
					`   <div class="cr-room-text">` +
					`		${chatroomDTO.productDTO.subject}<br/>${content}` +
					`	</div>` +
						notRead +
					`</div>` +
					`<div style="border: 1px solid #ebebeb"></div>`
				$('#listArea').append(str);

				$('#chatroom' + chatroomDTO.chatroomId).click(function () {
					openChatroom(chatroomDTO.chatroomId)
				})
			}

		},
		error: function (request) {
			if (request.status == 401) {
				location.href = request.responseText
			} else {
				alert(request.status + " : " + request.responseText)
			}
		}
	})
}

// 채팅방 생성
let createChatroom = function (num) { // num == productId
	$.ajax({
		url: "/chatroom/create",
		method: "POST",
		data: ({ productId: parseInt(num) }),
		beforeSend: function (jqXHR) {
			jqXHR.setRequestHeader(header, token);
		},
		success: function (chatroomId) {
			openChatroom(chatroomId)
		},
		error: function (request) {
			if (request.status == 401) {
				location.href = request.responseText
			} else {
				alert(request.status + " : " + request.responseText)
			}
		}

	})
}


// 채팅창 닫기
$(".chat-close-btn").click(function () {
	modalChange("circle")
})

// 채팅방 리스트
$('#chat-circle').click(function() {
	openChatroomList()
	modalChange("list")
})

// 리스트로 돌아가기
$('.chat-list-btn').click(function() {
	$('#chat-circle').click()
})


// Enter -> 메세지 전송
$('#msg').keyup(function (event) {
	if (event.which == 13) {
		$('#button-send').click()
	}
})

// 메세지 전송
$('#button-send').click(function() {
	let $msg = $('#msg');

	let data = {
		chatroomId: chatroomId,
		message: $msg.val(),
		memberId: memberId,
		memberNickname: memberNickname,
		targetId: targetId
	}

	if($msg.val().trim() != "") {
		stomp.send("/pub/chat/message", {}, JSON.stringify(data));
	}

	$msg.val("");
	$msg.focus()
})

// 이미지 전송
$('#chatImageName').change(function () {

	let formData = new FormData()
	formData.set("chatroomId", chatroomId)
	formData.set("chatImageName", document.getElementById('chatImageName').files[0])
	formData.set("memberId", memberId)
	formData.set("memberNickname", memberNickname)
	formData.set("targetId", targetId)

	$.ajax({
		url: "/chat/image",
		method: "POST",
		contentType : false, // multipart/form-data
		processData : false,
		data : formData,
		beforeSend: jqXHR => {
			jqXHR.setRequestHeader(header, token);
		},
		error: (request) => { alert(request.responseText) }
	})
})

// 메세지 출력
stomp.connect({}, function () {
	stomp.subscribe("/sub/chat/" + memberId, function (chat) {

		let chatDTO = JSON.parse(chat.body)

		// 채팅방 리스트에 있을 때
		if (chatroomId == 0) {
			openChatroomList()
		}
		
		// 메세지가 온 채팅방에 있을때
		else if (chatroomId == chatDTO.chatroomId) {

			// 채팅 읽음
			$.ajax({
				url: "/chat/readChat",
				method: "PUT",
				data: { memberId: memberId, chatroomId: chatroomId },
				beforeSend: jqXHR => { jqXHR.setRequestHeader(header, token) }
			})

			// 채팅 출력
			let str = ""
			if (lastDate != chatDTO.sendingTime.substring(0, 10)) {
				lastDate = chatDTO.sendingTime.substring(0, 10)

				let parseDate = new Date(chatDTO.sendingTime)
				let formatDate = `${parseDate.getFullYear()}년 ${parseDate.getMonth() + 1}월 ${parseDate.getDate()}일`

				str +=
					`<div class="chat-msg date">` +
					`   <div class="cm-msg-text">${formatDate}</div>` +
					`</div>`
			}

			str += chatTemplate(chatDTO);
			$('#foot').before(str)

			setTimeout(() => {
				$('#msgArea').scrollTop($('#msgArea')[0].scrollHeight)
			}, 50)
		}
	})

})

// modal에서 body 스크롤 방지 // 수정 필요
$('#chat-modal').mouseover(() => { document.body.style.overflow = "hidden" })
$('#chat-modal').mouseout (() => { document.body.style.overflow = "auto" })
