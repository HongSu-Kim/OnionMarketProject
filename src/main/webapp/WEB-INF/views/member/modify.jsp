<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<style>
    .site-btn-post {
        font-size: 8px;
        color: #ffffff;
        font-weight: 800;
        text-transform: uppercase;
        display: inline-block;
        padding: 5px 10px 5px;
        background: #47cd65;
        border: none;
    }

    .site-btn {
        font-size: 14px;
        color: #ffffff;
        font-weight: 800;
        text-transform: uppercase;
        display: inline-block;
        padding: 13px 30px 12px;
        background: #47cd65;
        border: none;
    }

    .checkout__input input {
        width: 100%;
        height: 46px;
        border: 1px solid #b7b7b7;
        padding-left: 20px;
        font-size: 16px;
        color: #000000;
        border-radius: 4px;
    }

    .alert-success {
        margin-top: 1px;
        background-color: #ffffff;
        color: #17b93a;
        border: none;
    }
    .alert-danger {
        margin-top: 1px;
        background-color: #ffffff;
        color: red;
        border: none;
    }

    span {
        color: #ff0000;
    }
</style>

<section class="spad">
    <div class="container">
        <div class="checkout__form">
            <form>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row">
                    <div class="col-lg-3 col-md-3"></div>
                    <div class="col-lg-6 col-md-6">
                        <h4 style="text-align: center">회원정보 수정</h4>
                        <input type="hidden" id="id" value="${memberDTO.id}"/>
                        <div class="checkout__input">
                            <p>아이디<span>*</span></p>
                            <input type="text" id="userId" value="${memberDTO.userId}" class="form-control" readonly/>
                        </div>
                        <div class="checkout__input">
                            <p>비밀번호<span>*</span></p>
                            <input type="password" name="pwd" id="pwd" placeholder="수정할 비밀번호를 입력해주세요"/>
                            <span id="valid_pwd">${valid_pwd}</span>
                            <input type="password" name="pwdCheck" id="pwdCheck" placeholder="비밀번호를 입력해주세요" style="margin-top: 5px;"/>

                            <div class="alert alert-success" id="alert-success" >비밀번호가 일치합니다.</div>
                            <div class="alert alert-danger" id="alert-danger" >비밀번호가 일치하지 않습니다.</div>
                        </div>
                        <div class="checkout__input">
                            <p>이름<span>*</span></p>
                            <input type="text" id="name" value="${memberDTO.name}" class="form-control" readonly/>
                            <span id="valid_name">${valid_name}</span>
                        </div>
                        <div class="checkout__input">
                            <p>닉네임<span>*</span></p>
                            <input type="text" id="nickname" value="${memberDTO.nickname}" placeholder="수정할 닉네임을 입력해 주세요."/>
                            <span id="valid_nickname">${valid_nickname}</span>
                        </div>
                        <div class="checkout__input">
                            <p>생년월일<span>*</span></p>
                            <input type="date" name="birth" value="${memberDTO.birth}" class="form-control" readonly/>
                            <span id="valid_birth">${valid_birth}</span>
                        </div>
                        <div class="checkout__input">
                            <p>휴대폰 번호<span>*</span></p>
                            <input type="text" id="tel" value="${memberDTO.tel}" placeholder="- 없이 숫자만 입력해 주세요."/>
                            <span id="valid_tel">${valid_tel}</span>
                        </div>
                        <div class="checkout__input">
                            <p>주소<span>*</span> <button type="button" id="postcodeBtn" class="site-btn-post" onclick="sample6_execDaumPostcode()">우편번호 찾기</button></p>
                            <input type="text" id="postcode" value="${memberDTO.postcode}" class="form-control" placeholder="수정할 우편번호를 입력해 주세요." readonly="readonly"/>
                            <input type="text" id="address" value="${memberDTO.address}" class="form-control" placeholder="수정할 주소를 입력해 주세요." readonly="readonly" style="margin-top: 5px;"/>
                            <input type="text" id="detailAddress" value="${memberDTO.detailAddress}" placeholder="수정할 상세주소를 입력해 주세요." style="margin-top: 5px;"/>
                            <span id="valid_detailAddress">${valid_detailAddress}</span>
                            <input type="text" id="extraAddress" value="${memberDTO.extraAddress}" class="form-control" placeholder="참고항목을 입력해 주세요." readonly="readonly" style="margin-top: 5px;"/>
                        </div>
                        <div class="checkout__input">
                            <p>이메일<span>*</span></p>
                            <input type="email" id="email" value="${memberDTO.email}" placeholder="수정할 이메일을 입력해 주세요."/>
                            <span id="valid_email">${valid_email}</span>
                        </div>
                    </div>
                </div>
            </form>
            <button id="btn-member-modify" class="site-btn" oninput="memberModify()" style="margin-left: 291px;">완료</button>
            <a href="/member/mypage" role="button" class="site-btn"> 마이 페이지</a>
        </div>
    </div>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</section>