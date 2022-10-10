<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>문의 작성 페이지</title>

    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/ajaxUtil.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <style>
        .container {
            max-width: 1000px;
        }
        .field-error{
            border-color: #f07682;
            color: #dc3545;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="py-5 text-center">
        <h1>1:1 문의하기</h1>
    </div><hr/>

    <div>
        <h4 class="mb-3">* 회원 정보</h4>
        email : ${memberDTO.name} <br/>
        tel : ${memberDTO.pwd}
    </div>
    <hr/>

    <div>
        <h4>* 문의 정보</h4>


        <form:errors path="inquiryDTO" cssClass="field-error"/>

        <form:form method="post" action="/inquiry/update/${inquiry.id}" modelAttribute="inquiryDTO">

            <!-- 비밀글 체크 -->
            <div class="form-check form-check-inline mt-3">
                <c:if test="${inquiry.secret == true}">
                <input class="form-check-input" type="checkbox" name="secret" id="secret"
                       checked="checked"></c:if>
                <label class="form-check-label">비밀글 설정</label>
            </div><br/><br/>

            <div>
                <label for="inquiryType">문의 유형</label>
                <select id="inquiryType" name="inquiryType" onchange="selectType();" style="width: 300px;">
                    <option selected="selected" value="${inquiry.inquiryType}">${inquiry.inquiryType}</option>
                    <option value="회원정보">회원정보/계정</option>
                    <option value="거래">거래</option>
                    <option value="기타서비스">기타 서비스</option>
                </select>
            </div>

            <div>
                <span>상세 선택</span>
                <select id="type_회원정보" onchange="selectDetail(this);">
                    <option selected="selected"  value="${inquiry.detailType}">${inquiry.detailType}</option>
                    <option value="회원가입,정보수정">회원가입/정보수정</option>
                    <option value="아이디,비밀번호">아이디/비밀번호</option>
                    <option value="로그인">로그인</option>
                    <option value="회원등급">회원등급</option>
                </select>
                <select id="type_거래" style="display: none" onchange="selectDetail(this);">
                    <option selected="selected">선택해주세요</option>
                    <option value="거래방법">거래방법</option>
                    <option value="거래내역확인">거래내역확인</option>
                    <option value="상품찾기,문의">상품찾기/문의</option>
                    <option value="거래확정,후기">거래확정/후기</option>
                </select>
                <select id="type_기타서비스" style="display: none" onchange="selectDetail(this);">
                    <option selected="selected">선택해주세요</option>
                    <option value="이벤트">이벤트</option>
                    <option value="광고등록방법">광고등록방법</option>
                    <option value="양파페이,포인트">양파페이/포인트</option>
                    <option value="경매이용">경매이용</option>
                    <option value="채팅이용">채팅이용</option>
                </select>
                <input type="hidden" id="detail" name="detailType">
            </div><br/><br/>


            <div>
                <label for="inquirySubject">제목</label>
                <input type="text" id="inquirySubject" name="inquirySubject" value="${inquiry.inquirySubject}",
                       class="form-control" placeholder="제목을 입력하세요">
                <form:errors path="inquirySubject" cssClass="field-error"/>
            </div>
            <div>
                <label for="inquiryContent">내용</label>
                <input type="text" id="inquiryContent" name="inquiryContent" value="${inquiry.inquiryContent}",
                       class="form-control" placeholder="내용을 입력하세요">
                <form:errors path="inquiryContent" cssClass="field-error"/>
            </div>
            <hr class="my-4">
            <div class="row">
                <div class="col">
                    <button class="w-100 btn btn-success btn-lg" type="submit">수정하기</button>
                    <button class="w-100 btn btn-secondary btn-lg"
                            onclick="location.href='/'" type="button">취소</button>
                </div>
            </div>
        </form:form>
    </div>
</div> <!-- /container -->

<script>
    function selectType(){
        let inquiryType = $("#inquiryType").val();

        $("#type_회원정보").hide();
        $("#type_거래").hide();
        $("#type_기타서비스").hide();

        $("#type_" + inquiryType).show();
    }
    function selectDetail(e){
        let val = e.value;
        document.getElementById('detail').value = val;
    }
</script>

</body>
</html>