

function check_submit() {

    if (isc.keywordName.value == "") {
        alert("키워드를 입력해주세요!");
        isc.keywordName.focus();
        return false;
    }


}