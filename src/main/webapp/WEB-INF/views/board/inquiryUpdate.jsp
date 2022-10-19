<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<style>
    .field-error{
        border-color: #f07682;
        color: #dc3545;
        font-weight: bold;
    }
    .detailSelect{
        display: none;
    }
</style>

<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">
            <div class="container">
                <div class="py-5 text-center">
                    <h1>1:1 문의하기</h1>
                </div><hr/>

                <div>
                    <h4 class="mb-3">* 회원 정보</h4>
                    아이디 : ${sessionDTO.userId}<br/>
                    닉네임 : ${sessionDTO.nickname}<br/>
                </div>
                <hr/>

                <div>
                    <h4>* 문의 정보</h4>


                    <form:errors path="inquiryDTO" cssClass="field-error"/>

                    <form:form method="post" action="/inquiry/update/${inquiryDTO.inquiryId}" modelAttribute="form">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" name="memberId" value="${sessionDTO.id}">

                        <!-- 비밀글 체크 -->
                        <div class="form-check form-check-inline mt-3">
                                <input class="form-check-input" type="checkbox" name="secret" id="secret" placeholder="
                                ${inquiryDTO.secret == true ? '<input class="form-check-input" type="checkbox" checked="checked" name="secret" id="secret"/>' : ' '}
                                비밀글 설정
                        </div><br/><br/>

                        <div>
                            <input type="hidden" name="inquiryType" value="${form.inquiryType}">
                            <label for="inquiryType">문의 유형</label><br/>
                            <select id="inquiryType" name="inquiryType" onchange="selectType();" style="width: 400px;">
                                <option selected="selected" value="${inquiryDTO.inquiryType}">${inquiryDTO.inquiryType}</option>
                                <option value="회원정보">회원정보/계정</option>
                                <option value="거래">거래</option>
                                <option value="기타서비스">기타 서비스</option>
                            </select>
                        </div><br/><br/>

                        <div>

                            <select id="type_회원정보" class="detailSelect" onchange="selectDetail(this);">
                                <option selected="selected">선택해주세요</option>
                                <option value="회원가입,정보수정">회원가입/정보수정</option>
                                <option value="아이디,비밀번호">아이디/비밀번호</option>
                                <option value="로그인">로그인</option>
                                <option value="회원등급">회원등급</option>
                            </select>
                            <select id="type_거래" class="detailSelect" style="display: none" onchange="selectDetail(this);">
                                <option selected="selected">선택해주세요</option>
                                <option value="거래방법">거래방법</option>
                                <option value="거래내역확인">거래내역확인</option>
                                <option value="상품찾기,문의">상품찾기/문의</option>
                                <option value="거래확정,후기">거래확정/후기</option>
                            </select>
                            <select id="type_기타서비스" class="detailSelect" style="display: none" onchange="selectDetail(this);">
                                <option selected="selected">선택해주세요</option>
                                <option value="이벤트">이벤트</option>
                                <option value="광고등록방법">광고등록방법</option>
                                <option value="양파페이,포인트">양파페이/포인트</option>
                                <option value="경매이용">경매이용</option>
                                <option value="채팅이용">채팅이용</option>
                            </select>
                            <input type="hidden" id="detail" name="detailType">
                            <form:errors path="detailType" cssClass="field-error"/>
                        </div><br/><br/>


                        <div>
                            <label for="inquirySubject">제목</label>
                            <input type="text" id="inquirySubject" name="inquirySubject"
                                   class="form-control" placeholder="${inquiryDTO.inquirySubject}">
                            <form:errors path="inquirySubject" cssClass="field-error"/>
                        </div>
                        <div>
                            <label for="inquiryContent">내용</label>
                            <input type="text" id="inquiryContent" name="inquiryContent"
                                   class="form-control" placeholder="${inquiryDTO.inquiryContent}">
                            <form:errors path="inquiryContent" cssClass="field-error"/>
                        </div>
                        <hr class="my-4">
                        <div class="row">
                            <div class="col">
                                <button class="w-100 btn btn-success btn-lg" type="submit">수정하기</button>
                                <button class="w-100 btn btn-secondary btn-lg"
                                        onclick="location.href='/inquiry/list'" type="button">취소</button>
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
            </div>
        </div>
    </div>
</section>