<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="tab-content align-content-center" style="max-width: 1200px; margin: 10px;">
    <div class="tab-content">
        <h2>문의글</h2><hr/>
    </div>

    <div>
        제목 : ${inquiryDTO.inquirySubject}
    </div><hr/>
    <div>
        내용 : <span readonly>${inquiryDTO.inquiryContent}</span>
    </div>

    <hr class="my-4">
    <div class="row">
        <div class="col">
            <button class="w-100 btn btn-primary btn-lg"
                    onclick="location.href='/inquiry/update/${inquiryDTO.inquiryId}'" type="button">문의글 수정</button>
        </div>
        <div class="col">
            <button class="w-100 btn btn-secondary btn-lg"
                    onclick="location.href='/inquiry/list?field=${param.field}&word=${param.word}&page=${param.page}'" type="button">목록으로</button>
        </div>
    </div>
</div> <!-- /container -->
</body>
</html>