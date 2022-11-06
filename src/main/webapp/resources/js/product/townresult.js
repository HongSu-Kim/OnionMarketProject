function statusChange(statusItem) {


    var townName = $(statusItem).text();

    townName = $.trim(townName);
    $("#coordinateId").val(townName);

    if (confirm(townName + "으로 동네 설정하시겠습니까?") == true) {    //확인

        document.townadd.submit();

    } else {   //취소

        return false;

    }


}

const modal = document.getElementById("modal")

function modalOn() {
    modal.style.display = "flex"
}

function isModalOn() {
    return modal.style.display === "flex"
}

function modalOff() {
    modal.style.display = "none"
}

const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modalOff();
})


$(window).on('load', function () {
    load('#js-load', '4');
    $("#js-btn-wrap .button").on("click", function () {
        load('#js-load', '4', '#js-btn-wrap');
    })
});