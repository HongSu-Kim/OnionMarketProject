<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<!-- Contact Form Begin -->
<div class="contact-form spad">
    <div class="container" style="width: 1100px">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>Leave Question</h2>
                </div>
            </div>
        </div>

        <form:form method="post" action="/inquiry/created" id="inquiryForm" modelAttribute="form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" name="memberId" value="${memberDTO.id}">

            <div class="row">
                <div class="col-lg-4">
                    ID <input type="text" readonly="readonly" value="${memberDTO.userId}"
                              placeholder="${memberDTO.userId}">
                </div>
                <div class="col-lg-4">
                    NAME <input type="text" readonly="readonly" value="${memberDTO.name}"
                                placeholder="${memberDTO.name}">
                </div>
                <div class="col-lg-4">
                    EMAIL <input type="text" readonly="readonly" value="${memberDTO.email}"
                                 placeholder="${memberDTO.email}">
                </div>
            </div>
            <hr/>

            <div class="row">
                <h4>*<span class="inquiryline"> 문의 정보</span></h4><br/><br/>

                <div class="col-lg-12">
                    <!-- 비밀글 체크 -->
                    <div class="form-check" style="display: flex">
                        <input class="form-check-input" type="checkbox" name="secret" id="secretBox"
                               placeholder="${form.secret == true ? '<input class="form-check-input" type="checkbox" checked="checked" name="secret" id="secret"/>' : ' '}">
                        <div style="margin-left: 5px; margin-top: 3px">비밀글 설정</div>
                    </div>
                    <br/>
                </div>

                <div class="col-lg-12" style="margin-bottom: 40px;">
                    <div><!-- 문의유형 선택 -->
                        <label for="inquiryType">문의 유형 &nbsp;<form:errors path="detailType" cssClass="field-error" cssStyle="margin-left: 12px"/></label><br/>
                        <form:select class="form-select" id="inquiryType" path="inquiryType" onchange="selectType();"
                                     style="width: 300px;">
                            <form:option selected="selected" value="">문의유형을 선택해주세요</form:option>
                            <form:option value="회원정보">회원정보/계정</form:option>
                            <form:option value="거래">거래</form:option>
                            <form:option value="기타서비스">기타 서비스</form:option>
                        </form:select>
                    </div>

                    <select id="formD" class="dtoValue detailSelect nice-select" onchange="selectDetail(this);">
                        <option selected="selected">${form.detailType}</option>
                    </select>

                    <div>
                        <select id="type_회원정보" class="detailSelect nice-select" onchange="selectDetail(this);">
                            <option selected="selected" value="">상세유형을 선택해주세요</option>
                            <option value="회원가입,정보수정">회원가입,정보수정</option>
                            <option value="아이디,비밀번호">아이디,비밀번호</option>
                            <option value="로그인">로그인</option>
                            <option value="회원등급">회원등급</option>
                        </select>

                        <select id="type_거래" class="detailSelect nice-select" onchange="selectDetail(this);">
                            <option selected="selected" value="">상세유형을 선택해주세요</option>
                            <option value="거래방법">거래방법</option>
                            <option value="거래내역확인">거래내역확인</option>
                            <option value="상품찾기">상품찾기</option>
                            <option value="거래확정,후기">거래확정,후기</option>
                        </select>
                        <select id="type_기타서비스" class="detailSelect nice-select" onchange="selectDetail(this);">
                            <option selected="selected" value="">상세유형을 선택해주세요</option>
                            <option value="양파페이,포인트">양파페이,포인트</option>
                            <option value="경매이용">경매이용</option>
                            <option value="채팅이용">채팅이용</option>
                        </select>
                        <form:input type="hidden" id="detail" path="detailType"/>
                    </div>
                </div>

                <div class="col-lg-12">
                    <div>문의제목 &nbsp;&nbsp;&nbsp;<form:errors path="inquirySubject" cssClass="field-error"/></div>
                    <form:input type="text" path="inquirySubject" style="color: black; font-weight: bold"
                                placeholder="* 문의제목을 입력해주세요"/>
                </div>

                <div class="col-lg-12 text-center">
                    <div class="text-left">문의내용 &nbsp;&nbsp;<form:errors path="inquiryContent"
                                                                         cssClass="field-error"/></div>
                    <div>
                        <form:textarea path="inquiryContent" placeholder="* 문의내용을 입력하세요" style="color: black; font-weight: bold"></form:textarea>
                    </div>
                    <br/>

                    <button type="submit" class="site-btn" onclick="inquirysend();">SEND INQUIRY</button>
                    <button class="site-btn" style="background-color: #5a6268" onclick="location.href='/inquiry/list'"
                            type="button">CANCEL
                    </button>
                </div>

            </div>
        </form:form>
    </div>
</div>
<!-- Contact Form End -->
