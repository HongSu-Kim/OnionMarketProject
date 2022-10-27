//비밀번호 확인
$(function(){
    $("#alert-success").hide();
    $("#alert-danger").hide();
    $("input").keyup(function(){
        var withdrawpwd=$("#withdrawpwd").val();
        var pwdCheck=$("#pwdCheck").val();
        if(withdrawpwd != "" || pwdCheck != ""){
            if(withdrawpwd == pwdCheck){
                $("#alert-success").show();
                $("#alert-danger").hide();
                $("#btn-member-withdraw").removeAttr("disabled");
            }else{
                $("#alert-success").hide();
                $("#alert-danger").show();
                $("#btn-member-withdraw").attr("disabled", "disabled");
            }
        }
    });
});