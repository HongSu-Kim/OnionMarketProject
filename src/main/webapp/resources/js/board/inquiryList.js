$(function () {
    $("#testDatepicker1").datepicker({
        changeYear: true,
        showButtonPanel: true,
        closeText: '닫기',
        dateFormat: "yy.mm.dd",
        maxDate: "+0D"
    });
    $("#testDatepicker2").datepicker({
        changeYear: true,
        showButtonPanel: true,
        closeText: '닫기',
        dateFormat: "yy.mm.dd",
        maxDate: "+0D"
    });
});

// 날짜 형식 체크 및 변경
function checkDateFormat(obj) {
    $("#searchForm [name='radioTabGuide']").attr("checked", false);
    $("#searchForm [name='period']").val('input');

    if ($(obj).val() != "") {
        var only_nos = $(obj).val().replace(/[^0-9]/g, '');

        if (only_nos.length == 8) {
            var year = only_nos.substr(0, 4);
            var month = only_nos.substr(4, 2);
            var day = only_nos.substr(6, 2);
            var chg_value = year + "." + month + "." + day;
            // var hidden_chg_value = year + "-" + month + "-" + day;

            if (month > 12 || day > 31) {
                alert("날짜 형식을 잘못 입력하셨습니다.");
                chg_value = "";
            }

            $(obj).val(chg_value);
        } else {
            alert("날짜 형식을 잘못 입력하셨습니다.");
            $(obj).val("");
        }
    }
}

// 시작 기간 얻기
function setStartDate(type) {
    var time_val = "";
    var now = new Date();

    if (type == "1week") {
        time_val = now.getTime() - (7 * 24 * 60 * 60 * 1000);
    } else if (type == "1month") {
        time_val = now.getTime() - (30 * 24 * 60 * 60 * 1000);
    } else if (type == "3month") {
        time_val = now.getTime() - (91 * 24 * 60 * 60 * 1000);
    }

    if (time_val != "") {
        now.setTime(time_val);
    }

    var year = now.getFullYear();
    var mon = (now.getMonth() + 1) > 9 ? '' + (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
    var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();

    return year + '.' + mon + '.' + day + "|" + year + '-' + mon + '-' + day;
}

// 기간 설정
function setPeriod(obj, type) {
    // Type Setting
    $("#searchForm [name='period']").val(type);

    // 기간 검색 부분 Init
    $("#searchForm [name='dt_fr']").val('');
    $("#searchForm [name='dt_to']").val('');

    // 기간 부분 조건에 따라 Setting
    var now = new Date();

    var year = now.getFullYear();
    var mon = (now.getMonth() + 1) > 9 ? '' + (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
    var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();

    var now_date = year + '.' + mon + '.' + day;

    if (type != '') {
        var start_date = setStartDate(type);
        var a_date = start_date.split('|');

        $("#searchForm [name='dt_to']").val(now_date);
        $("#searchForm [name='dt_fr']").val(a_date[0]);
    }
}

function search() {
    var f = document.searchForm;

    let fromDate = $("#searchForm [name='dt_fr']").val();
    let toDate = $("#searchForm [name='dt_to']").val();

    if (fromDate == '' || toDate == '') {
        alert("검색 기간을 설정해 주세요");
        return false;
    }
    if (fromDate > toDate) {
        alert("기간이 잘못되었습니다. 확인해주세요");
        return false;
    }

    f.submit();
}

function searchWord(){
    var f = document.searchForm;
    f.submit();
}