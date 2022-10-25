function remainedTime() {
    // var upload = $("#upload"). val();
    var auctionDeadline = $("#deadline").val();

    var open = new Date(); //현재시간을 구한다.
    var deadline = new Date(auctionDeadline);
    var start = new Date(open.getFullYear(),open.getMonth(),open.getDate(),open.getHours(),open.getMinutes(),open.getSeconds());
    var end = new Date(deadline.getFullYear(),deadline.getMonth(),deadline.getDay(),deadline.getHours(),deadline.getMinutes(),deadline.getSeconds());
    var st = open.getTime(); // 현재의 시간만 가져온다
    var et = end.getTime(); // 오픈시간만 가져온다

    if(st<et){ //현재시간이 오픈시간보다 이르면 오픈시간까지의 남은 시간을 구한다.
        sec = parseInt(et-st)/1000;
        // day  = parseInt(sec/60/60/24);
        // sec = (sec - (day * 60 * 60 * 24));
        hour = parseInt(sec/60/60);
        sec = (sec - (hour*60*60));
        min = parseInt(sec/60);
        sec = parseInt(sec-(min*60));

        if(hour<10){hour="0"+hour;}
        if(min<10){min="0"+min;}
        if(sec<10){sec="0"+sec;}

        // $("#d-day").html(day);
        $("#d-day-hour").html(hour);
        $("#d-day-min").html(min);
        $("#d-day-sec").html(sec);
    } else{ //현재시간이 종료시간보다 크면
        // $("#d-day").html('00');
        $("#d-day-hour").html('00');
        $("#d-day-min").html('00');
        $("#d-day-sec").html('00');
    }
}
setInterval(remainedTime,1000); //1초마다 검사를 해주면 실시간으로 시간을 알 수 있다.

$(document).ready(function () {
    //$('#registerForm').validate(); //유효성 검사를 적용
    // validate signup form on keyup and submit
    $('#productForm').validate({

        rules: {
            bid: {
                required:true,
                digits:true,
                min:100,
                max:999999
            }
        },
        messages: {
            bid: {
                required: "가격을 입력해주세요.",
                digits: "숫자만 입력 가능합니다.",
                min: "최소 가격은 100원 이상입니다.",
                max: "최대 가격은 999,999원입니다."
            }
        }

    });
});
