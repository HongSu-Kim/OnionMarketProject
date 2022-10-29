
let header = $("meta[name='_csrf_header']").attr("content");
let token = $("meta[name='_csrf']").attr("content");

let memberId = $('#memberId').val()
let memberNickname = $('#memberNickname').val()
let stomp = Stomp.over(new SockJS("/stomp/chat"));

let chatroomId

// 리스트로 돌아가기
$(".chat-list-btn").click(function() {;
    $('#chatList').css('z-index', 2)
    chatroomId = 0
    chatroomList()
})

// 채팅창 닫기
$(".chat-close-btn").click(function() {
    $('#chat-circle').toggle('scale');
    $('.chat-box').toggle('scale');
})

// 채팅방 리스트
$('#chat-circle').click(function() {

    chatroomList()

    $('#chatList').css('z-index', 2)
    $('#chat-circle').toggle('scale');
    $('.chat-box').toggle('scale');
})

// 채팅방 리스트 출력
function chatroomList() {
    $.ajax({
        url: "/chatroom/list",
        method: "GET",
        success: function (chatroomDTOList) {
            $('#listArea').html("")

            for (let i = 0; i < chatroomDTOList.length; i++) {
                let chatroomDTO = chatroomDTOList[i]
                let str =
                    '<div class="chat-room" onclick="chatroom(' + chatroomDTO.chatroomId + ')">' +
                    '   <span class="msg-avatar">' +
                    '       <img src="/img/product/' + chatroomDTO.productDTO.representativeImage + '">' +
                    '   </span>' +
                    '   <div class="cr-room-text">' +
                    chatroomDTO.productDTO.subject + "<br/>" +
                    (chatroomDTO.chatDTO != null
                        ? "<span id='chatMessage" + chatroomDTO.chatroomId + "'>" + chatroomDTO.chatDTO.message + "</span>"
                        : "메세지가 없습니다.") +
                    '   </div>' +
                    '<div style="border: 1px solid #ebebeb"></div>'
                $('#listArea').append(str);
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

// 채팅방 출력
function chatroom(num) { // num == chatroomId
    $('#chatList').css('z-index', 1)

    chatroomId = num

    $.ajax({
        url: "/chatroom/room/" + chatroomId,
        method: "GET",
        success: function (chatroomDTO) {
            $('#msgArea').html(
                '<div id="head" class="chat-msg-padding"></div>' +
                '<div id="foot" class="chat-msg-padding"></div>'
            )

            $('#title').html(chatroomDTO.productDTO.subject)

            let chatDTOList = chatroomDTO.chatDTOSlice.content

            $('#chatroomId').val(chatroomId)
            $('#page').val(chatroomDTO.chatDTOSlice.pageNumber)
            $('#targetId').val(chatroomDTO.memberDTO.id == memberId ? chatroomDTO.productDTO.memberDTO.id : chatroomDTO.memberDTO.id)

            for (let i = 0; i < chatDTOList.length; i++) {
                let chatDTO = chatDTOList[i]

                let str =
                    (chatDTO.memberId == memberId
                        ? '<div class="chat-msg self">'
                        : '<div class="chat-msg user">') +
                    '   <div class="cm-msg-text">' + chatDTO.message + '</div>' +
                    '</div>'

                $('#head').after(str)
            }
            $('#msgArea').scrollTop($('#msgArea')[0].scrollHeight);
            $('#msg').focus()
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

// 채팅방 생성
function createChatroom(num) { // num == productId
    $.ajax({
        url: "/chatroom/create",
        method: "POST",
        data: ({ productId: parseInt(num) }),
        beforeSend: function (jqXHR) {
            jqXHR.setRequestHeader(header, token);
        },
        success: function (chatroomId) {
            $('#chatList').css('z-index', 1)
            $('#chat-circle').toggle('scale');
            $('.chat-box').toggle('scale');
            chatroom(chatroomId)
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

// Enter -> 메세지 전송
$('#msg').keyup(function (event) {
    if (event.which == 13) {
        $('#button-send').click()
    }
})

// 메세지 전송
$('#button-send').click(function() {
    let msg = document.getElementById("msg");

    // console.log(memberId + ":" + msg.value);
    stomp.send(
        "/pub/chat/message",
        {},
        JSON.stringify({
            chatroomId: $('#chatroomId').val(),
            message: msg.value,
            memberId: memberId,
            memberNickname: memberNickname,
            targetId: $('#targetId').val()
        })
    );
    msg.value = "";
    msg.focus()
})

// 메세지 출력 //2. connection이 맺어지면 실행
stomp.connect({}, function () {
    // console.log("STOMP Connection")

    //4. subscribe(path, callback)으로 메세지를 받을 수 있음
    stomp.subscribe("/sub/chat/" + memberId, function (chat) {
        let chatDTO = JSON.parse(chat.body)

        // 채팅방 리스트에 있을 때
        if (chatroomId == null) {
            $('#chatMessage' + chatDTO.chatroomId).html(chatDTO.message)
        }
        // 메세지가 온 채팅방에 있을때
        else if (chatroomId == chatDTO.chatroomId) {
            let str =
                (chatDTO.memberNickname == memberNickname
                    ? '<div class="chat-msg self">'
                    : '<div class="chat-msg user">') +
                      '   <div class="cm-msg-text">' + chatDTO.message + '</div>' +
                      '</div>'

            $('#foot').before(str)
            $('#msgArea').scrollTop($('#msgArea')[0].scrollHeight)
        }
    })

})

