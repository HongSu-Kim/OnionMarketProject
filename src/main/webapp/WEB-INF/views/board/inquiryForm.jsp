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
                        <label for="inquiryType">문의 유형 &nbsp;<form:errors path="detailType" cssClass="field-error"
                                                                          cssStyle="margin-left: 12px"/></label><br/>
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
                        <form:textarea path="inquiryContent" placeholder="* 문의내용을 입력하세요"
                                       style="color: black; font-weight: bold"></form:textarea>
                    </div>
                </div>


                <div class="css-kd8yp0">
                    <div class="d-flex" style="height: 30px">
                        <input type="checkbox" id="agreement" oninvalid="this.setCustomValidity('개인정보 수집에 동의해주세요')"
                        style="width: 20px; height: 20px" required/>
                        <label for="agreement" class="checkTitle">개인정보 수집 및 이용동의</label>
                    </div>

                    <p class="css-478t8i">1. 수집하는 개인정보 항목 : 이름, 이메일<br/>2. 수집 목적 : 문의자 확인, 문의에 대한 회신 등의 처리<br/>3.
                        보유 기간 : <em>목적 달성 후 파기</em>, 단, 관계법령에 따라 또는 회사 정책에 따른 정보보유사유가 발생하여 보존할 필요가 있는 경우에는 필요한 기간 동안 해당
                        정보를 보관합니다. 전자상거래 등에서의 소비자 보호에 관한 법률, 전자금융거래법, 통신비밀보호법 등 법령에서 일정기간 정보의 보관을 규정하는 경우, 이 기간 동안 법령의
                        규정에 따라 개인정보를 보관하며, 다른 목적으로는 절대 이용하지 않습니다. (개인정보처리방침 참고)<br/>4. 귀하는 회사의 정보수집에 대해 동의하지 않거나 거부할 수
                        있습니다. 다만, 이때 원활한 문의 및 서비스 이용 등이 제한될 수 있습니다.</p>
                </div>
            </div>
            <div style="text-align: center; margin-top: 20px">
                <button type="submit" class="site-btn" onclick="inquirysend();">SEND INQUIRY</button>
                <button class="site-btn" style="background-color: #5a6268" onclick="location.href='/inquiry/list'"
                        type="button">CANCEL
                </button>
            </div>

        </form:form>
    </div>
</div>

<!-- Contact Form End -->
