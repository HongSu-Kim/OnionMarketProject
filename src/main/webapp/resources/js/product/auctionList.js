function remainedTime() {

    var dead = document.getElementsByName("dead")
    var dHour = document.getElementsByName("d-day-hour")
    var dMin = document.getElementsByName("d-day-min")
    var dSec = document.getElementsByName("d-day-sec")

    for(let i = 0; i<dead.length; i++) {
        var open = new Date(); //현재시간을 구한다.
        var deadline = new Date(dead[i].value);

        var start = new Date(open.getFullYear(), open.getMonth(), open.getDay(), open.getHours(), open.getMinutes(), open.getSeconds());
        var end = new Date(deadline.getFullYear(), deadline.getMonth(), deadline.getDay(), deadline.getHours(), deadline.getMinutes(), deadline.getSeconds());

        var st = start.getTime(); // 현재의 시간
        var et = end.getTime(); // 경매기한
        if (st < et) { //현재시간이 경매기한보다 이르면 경매기한까지의 남은 시간을 구한다.
            sec = parseInt(et - st) / 1000;
            hour = parseInt(sec / 60 / 60);
            sec = (sec - (hour * 60 * 60));
            min = parseInt(sec / 60);
            sec = parseInt(sec - (min * 60));
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (min < 10) {
                min = "0" + min;
            }
            if (sec < 10) {
                sec = "0" + sec;
            }

            // $("#d-day").html(day);
            dHour[i].innerHTML=hour;
            dMin[i].innerHTML=min;
            dSec[i].innerHTML=sec;
        } else { //현재시간이 종료시간보다 크면
            // $("#d-day").html('00');
            dHour[i].innerHTML='00';
            dMin[i].innerHTML='00';
            dSec[i].innerHTML='00';
        }
    }
}
setInterval(remainedTime,1000);