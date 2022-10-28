function remainedTime() {
    var dead = $('#dead').val();

    var open = new Date(); //현재시간을 구한다.
    var deadline = new Date(dead);

    var start = new Date(open.getFullYear(),open.getMonth(),open.getDay(),open.getHours(),open.getMinutes(),open.getSeconds());
    var end = new Date(deadline.getFullYear(),deadline.getMonth(),deadline.getDay(),deadline.getHours(),deadline.getMinutes(),deadline.getSeconds());

    var st = start.getTime(); // 현재의 시간
    var et = end.getTime(); // 경매기한

    if(st<et){ //현재시간이 경매기한보다 이르면 경매기한까지의 남은 시간을 구한다.
        sec = parseInt(et-st)/1000;
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
setInterval(remainedTime,1000);

$(document).ready(function () {

    var price = Number($('#nowPrice').val()); //시작가
    var nowBid = Number($('#nowBid').val()); //현재 입찰가
    var exBid = Number($('#exBid').val()); //이전 입찰가
    var now;
    if(exBid == null) {
        now = price;
    }else {
        now = exBid+10000;
    }

    $('#productForm').validate({

        rules: {
            bid: {
                required:true,
                digits:true,
                min:now,
                max:999999
            }
        },
        messages: {
            bid: {
                required: "가격을 입력해주세요.",
                digits: "숫자만 입력 가능합니다.",
                min: "최소 입찰가보다 커야합니다",
                max: "최대 가격은 999,999원입니다."
            }
        }
    });
});
