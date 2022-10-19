<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
            <div class="container" style="width: 1200px">
                <div class="py-5 text-center">
                    <h1>1:1 문의하기</h1>
                </div><hr/>

                <div>

                <form:errors path="inquiryDTO" cssClass="field-error"/>

                <form:form method="post" action="/inquiry/created" modelAttribute="form">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div>
                        <h4 class="mb-3">* 회원 정보</h4>
                        아이디 : ${sessionDTO.userId} <br/>
                        닉네임 : ${sessionDTO.nickname}
                    </div><hr/>

                    <input type="hidden" name="memberId" value="${sessionDTO.id}">

                    <h4>* 문의 정보</h4>
                    <!-- 비밀글 체크 -->
                    <div class="form-check form-check-inline mt-3">
                        <input class="form-check-input" type="checkbox" name="secret" id="secret" placeholder="${form.secret}">
                        <label class="form-check-label">비밀글 설정</label>
                    </div><br/><br/>

                    <div>
                        <label for="inquiryType">문의 유형</label><br/>
                        <select id="inquiryType" name="inquiryType" onchange="selectType();" style="width: 300px;">
                            <c:choose>
                                <c:when test="${form != null}">
                                    <option selected="selected" value="${form.inquiryType}">${form.inquiryType}</option>
                                </c:when>
                                <c:otherwise>
                                    <option selected="selected">문의유형을 선택해주세요</option>
                                </c:otherwise>
                            </c:choose>
                            <option value="회원정보">회원정보/계정</option>
                            <option value="거래">거래</option>
                            <option value="기타서비스">기타 서비스</option>
                        </select>
                        <form:errors path="inquiryType" cssClass="field-error"/>
                    </div><br/><br/>

                    <div>
                        <select id="type_회원정보" class="detailSelect" onchange="selectDetail(this);">
                            <option selected="selected">상세유형을 선택해주세요</option>
                            <option value="회원가입,정보수정">회원가입/정보수정</option>
                            <option value="아이디,비밀번호">아이디/비밀번호</option>
                            <option value="로그인">로그인</option>
                            <option value="회원등급">회원등급</option>
                        </select>

                        <select id="type_거래" class="detailSelect" onchange="selectDetail(this);">
                            <option selected="selected">상세유형을 선택해주세요</option>
                            <option value="거래방법">거래방법</option>
                            <option value="거래내역확인">거래내역확인</option>
                            <option value="상품찾기,문의">상품찾기/문의</option>
                            <option value="거래확정,후기">거래확정/후기</option>
                        </select>
                        <select id="type_기타서비스" class="detailSelect" onchange="selectDetail(this);">
                            <option selected="selected">상세유형을 선택해주세요</option>
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
                        <input type="text" id="inquirySubject" name="inquirySubject" class="form-control"
                               value="${form.inquirySubject}" placeholder="제목을 입력하세요">
                        <form:errors path="inquirySubject" cssClass="field-error"/>
                    </div>
                    <div>
                        <label for="inquiryContent">내용</label>
                        <input type="text" id="inquiryContent" name="inquiryContent" class="form-control"
                               value="${form.inquiryContent}" placeholder="내용을 입력하세요">
                        <form:errors path="inquiryContent" cssClass="field-error"/>
                    </div>
                    <hr class="my-4">
                    <div class="row">
                        <div class="col">
                            <button class="w-100 btn btn-success btn-lg" type="submit">등록하기</button>
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
                    // $("#type_기타서비스").css("visibility", "hidden");
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

