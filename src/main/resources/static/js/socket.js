
let chatroomId = $('#chatroomId').val()
let memberId = $('#memberId').val()
let memberNickname = $('#memberNickname').val()
// console.log(subject + ", " + chatroomId + ", " + memberId);

let sockJs = new SockJS("/stomp/chat");
//1. SockJS를 내부에 들고있는 stomp를 내어줌
let stomp = Stomp.over(sockJs);

//2. connection이 맺어지면 실행
stomp.connect({}, function () {
    // console.log("STOMP Connection")

    //4. subscribe(path, callback)으로 메세지를 받을 수 있음
    stomp.subscribe("/sub/chatroom/room/" + chatroomId, function (chat) {
        let content = JSON.parse(chat.body);

        let writer = content.memberNickname;
        let message = content.message;
        let str =
            (writer == memberNickname
                ? '<div id="cm-msg" class="chat-msg self">'
                : '<div id="cm-msg" class="chat-msg user">') +
                  '   <div class="cm-msg-text">' + message + '</div>' +
                  '</div>'

        $('#padding').before(str);
        $('#msgArea').scrollTop($('#msgArea')[0].scrollHeight);
    });

    //3. send(path, header, message)로 메세지를 보낼 수 있음
    // stomp.send('/pub/chat/enter', {}, JSON.stringify({chatroomId: chatroomId, memberId: memberId}))
});

$(function () {
    $('#msg').keyup(function (event) {
        if (event.which == 13) {
            $('#button-send').click()
        }
    })
})

$('#button-send').on("click", function(e){
    let msg = document.getElementById("msg");

    // console.log(memberId + ":" + msg.value);
    stomp.send("/pub/chat/message", {}, JSON.stringify({
        chatroomId: chatroomId,
        message: msg.value,
        memberId: memberId,
        memberNickname: memberNickname
    }));
    msg.value = "";
});

$('#chat-circle').click(function() {
    $('#chat-circle').toggle('scale');
    $('.chat-box').toggle('scale');
    setTimeout(function() {
        $('#msgArea').scrollTop($('#msgArea')[0].scrollHeight);
    }, 200);
})

$(".chat-box-toggle").click(function() {
    $('#chat-circle').toggle('scale');
    $('.chat-box').toggle('scale');
})

