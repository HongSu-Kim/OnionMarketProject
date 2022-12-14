function remainedTime() {
    var dead = $('#dead').val();

    var open = new Date(); //현재시간을 구한다.
    var deadline = new Date(dead);

    var st = open.getTime(); // 현재의 시간
    var et = deadline.getTime(); // 경매기한
    if(st<et){ //현재시간이 경매기한보다 이르면 경매기한까지의 남은 시간을 구한다.
        sec = parseInt(et - st)/1000;
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
    var exBid = Number($('#exBid').val()); //이전 입찰가
    var now; //최소 입찰가
    if(exBid == 0) {
        now = price;
    }else if(exBid >= price) {
        now = exBid+1000;
    }else if(exBid < price) {
        now = price;
    }

    $('#productForm').validate({

        rules: {
            bid: {
                required:true,
                digits:true,
                min:now,
                max:2100000000
            }
        },
        messages: {
            bid: {
                required: "가격을 입력해주세요.",
                digits: "숫자만 입력 가능합니다.",
                min: "최소 입찰가보다 커야합니다",
                max: "최대 가격을 넘을 수 없습니다."
            }
        }
    });
});

$('.product__image').mouseover(function () {
	$('#big').attr("src", $(this).attr("src"))
})