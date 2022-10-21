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
    #secretBox{
        zoom: 1;
        width: 20px;
        height: 20px;
    }
</style>

<!-- Contact Form Begin -->
<div class="contact-form spad" style="background-color: oldlace">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="contact__form__title">
                    <h2>Leave Question</h2>
                </div>
            </div>
        </div>

        <form:form method="post" action="/inquiry/created" modelAttribute="form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" name="memberId" value="${memberDTO.id}">

            <div class="row">
                <div class="col-lg-4">
                    ID <input type="text" readonly="readonly" value="${memberDTO.userId}" placeholder="${memberDTO.userId}">
                </div>
                <div class="col-lg-4">
                    NAME <input type="text" readonly="readonly" value="${memberDTO.name}" placeholder="${memberDTO.name}">
                </div>
                <div class="col-lg-4">
                    EMAIL <input type="text" readonly="readonly" value="${memberDTO.email}" placeholder="${memberDTO.email}">
                </div>
            </div><hr/>

            <div class="row">
                <h4>* 문의 정보</h4><br/><br/>

                <div class="col-lg-12">
                    <!-- 비밀글 체크 -->
                    <div class="form-check" style="display: flex">
                        <input class="form-check-input" type="checkbox" name="secret" id="secretBox" placeholder="${form.secret == true ? '<input class="form-check-input" type="checkbox" checked="checked" name="secret" id="secret"/>' : ' '}">
                        <div style="margin-left: 5px; margin-top: 3px">비밀글 설정</div>
                    </div><br/>
                </div>

                <div class="col-lg-12">
                    <div><!-- 문의유형 선택 -->
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
                </div>



                    <div class="col-lg-12">
                        <div>문의제목</div>
                        <input type="text" name="inquirySubject" value="${form.inquirySubject}"
                               placeholder="문의제목을 입력해주세요">
                        <form:errors path="inquirySubject" cssClass="field-error"/><br/><br/>
                    </div>

                    <div class="col-lg-12 text-center">
                        <div class="text-left">문의내용</div>
                        <div>
                            <textarea name="inquiryContent" placeholder="문의내용을 입력하세요"></textarea>
                            <form:errors path="inquiryContent" cssClass="field-error"/>
                        </div><br/>

                        <button type="submit" class="site-btn">SEND MESSAGE</button>
                        <button class="site-btn" style="background-color: #5a6268" onclick="location.href='/inquiry/list'" type="button">CANCEL</button>
                    </div>

            </div>
        </form:form>
    </div>
</div>
<!-- Contact Form End -->







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
