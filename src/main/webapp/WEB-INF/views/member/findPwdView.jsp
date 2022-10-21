<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

    <title>비밀번호 찾기</title>

    <script>
        $("#checkEmail").click(function () {
            const email = $("#email").val();
            const sendEmail = document.forms["sendEmail"];
            $.ajax({
                type: 'post',
                url: 'emailDuplication',
                data: {
                    'email': email
                },
                dataType: "text",
                success: function (result) {
                    if(result == "no"){
                        // 중복되는 것이 있다면 no == 일치하는 이메일이 있다!
                        alert('임시비밀번호를 전송 했습니다.');
                        sendEmail.submit();
                    }else {
                        alert('가입되지 않은 이메일 입니다. 이메일을 다시 확인해 주세요.');
                    }

                },error: function () {
                    console.log('에러 발생!')
                }
            })
        });
    </script>

</head>

<body>

    <div class="posts_list">
        <h1 class="h4 text-gray-900 mb-2">비밀번호 찾기</h1>
    </div>

    <form action="/member/sendEmail" method="post" name="sendEmail">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form-group">
            <label>이메일</label>
            <input type="email" name="email" class="form-control" placeholder="이메일을 입력해주세요"/>
        </div>
        <button type="button" id="checkEmail" class="btn btn-primary bi bi-lock-fill">확인</button>
        <a href="/member/login" class="btn btn-primary bi bi-lock-fill">로그인</a>
    </form>

    <hr>
    <div class="text-center">
        <a class="small" href="#">메인페이지</a>
    </div>

</body>

</html>
