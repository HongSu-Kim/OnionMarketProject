<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<!-- Checkout Section Begin -->
<section class="spad">
    <div class="container">
        <div class="checkout__form">
            <form:form action="/member/joinProc" method="post" name="myForm" modelAttribute="memberJoinDTO" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row">
                    <div class="col-lg-3 col-md-3"></div>
                    <div class="col-lg-6 col-md-6">
                    <h4>회원가입</h4>
                        <div class="checkout__input">
                            <p>아이디<span>*</span></p>
                            <input type="text" name="userId" value="${memberJoinDTO.userId}" placeholder="아이디를 입력해 주세요." autofocus/>
                            <span id="valid_userId">${valid_userId}</span>
                        </div>
                        <div class="checkout__input">
                            <p>비밀번호<span>*</span></p>
                            <input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력해 주세요."/>
                            <span id="valid_pwd">${valid_pwd}</span>
                            <input type="password" name="pwdCheck" id="pwdCheck" placeholder="비밀번호 재확인" class="checkout__input pwdCheck"/>

                            <div class="alert alert-success" id="alert-success" >비밀번호가 일치합니다.</div>
                            <div class="alert alert-danger" id="alert-danger" >비밀번호가 일치하지 않습니다.</div>
                        </div>
                        <div class="checkout__input">
                            <p>이름<span>*</span></p>
                            <input type="text" name="name" value="${memberJoinDTO.name}" placeholder="이름을 입력해 주세요."/>
                            <span id="valid_name">${valid_name}</span>
                        </div>
                        <div class="checkout__input">
                            <p>닉네임<span>*</span></p>
                            <input type="text" name="nickname" value="${memberJoinDTO.nickname}" placeholder="닉네임을 입력해 주세요."/>
                            <span id="valid_nickname">${valid_nickname}</span>
                        </div>
                        <div class="checkout__input">
                            <p>생년월일<span>*</span></p>
                            <input type="date" name="birth" value="${memberJoinDTO.birth}" placeholder="생년월일을 입력해 주세요."/>
                            <span id="valid_birth">${valid_birth}</span>
                        </div>
                        <div class="checkout__input">
                            <p>휴대폰 번호<span>*</span></p>
                            <input type="text" name="tel" value="${memberJoinDTO.tel}" placeholder="- 없이 숫자만 입력해 주세요."/>
                            <span id="valid_tel">${valid_tel}</span>
                        </div>
                        <div class="checkout__input">
                            <p>주소<span>*</span> <button type="button" id="postcodeBtn" class="site-btn-post" onclick="sample6_execDaumPostcode()">우편번호 찾기</button></p>
                            <input type="text" name="postcode" id="postcode" value="${memberJoinDTO.postcode}" class="address" placeholder="우편번호를 입력해 주세요." readonly="readonly"/>
                            <span id="valid_postcode">${valid_postcode}</span>
                            <input type="text" name="address" id="address" value="${memberJoinDTO.address}" class="address" placeholder="주소를 입력해 주세요." readonly="readonly"/>
                            <span id="valid_address">${valid_address}</span>
                            <input type="text" name="detailAddress" id="detailAddress" value="${memberJoinDTO.detailAddress}" class="address" placeholder="상세주소를 입력해 주세요."/>
                            <span id="valid_detailAddress">${valid_detailAddress}</span>
                            <input type="text" name="extraAddress" id="extraAddress" value="${memberJoinDTO.extraAddress}" placeholder="참고항목을 입력해 주세요." readonly="readonly"/>
                        </div>
                        <div class="checkout__input">
                            <p>이메일<span>*</span></p>
                            <input type="text" name="email" value="${memberJoinDTO.email}" placeholder="이메일을 입력해 주세요."/>
                            <span id="valid_email">${valid_email}</span>
                        </div>
                        <div class="checkout__input">
                            <p>프로필</p>
                            <img class="rounded-circle mt-2 preview" id="preview"/>
                            <input type="file" name="profileImg" onchange="readURL(this);"/>
                        </div>
                        <button type="submit" id="submit" class="site-btn">가입하기</button>
                        <button type="button" class="site-btn" onclick="location.href='/member/login'">돌아가기</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</section>
<!-- Checkout Section End -->
