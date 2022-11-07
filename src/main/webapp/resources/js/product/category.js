function statusChange(statusItem) {


    //   var townName = $(statusItem).text();
    var topCategoryName = $("#topCategoryName option:selected").val();
    //townName = $.trim(townName);
    $("#TopName").val(topCategoryName);

    if (confirm(topCategoryName + "카테고리 등록하시겠습니까?") == true) {    //확인

        document.categoryAdd.submit();

    } else {   //취소

        return false;

    }


}


function check_submit() {

    if (isc.categoryName.value == "선택하세요") {
        alert("상위카테고리를 선택해 주세요!.");
        isc.categoryName.focus();
        return false;
    }

    if (isc.subCategoryName.value == "") {
        alert("하위카테고리 입력해 주세요!.");
        isc.subCategoryName.focus();
        return false;
    }


}