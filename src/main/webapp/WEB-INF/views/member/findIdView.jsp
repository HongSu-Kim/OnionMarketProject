<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="spad">
    <div class="container">
        <div class="checkout__form">
            <form action="/member/findId" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row">
                    <div class="col-lg-3 col-md-3"></div>
                    <div class="col-lg-6 col-md-6">
                        <h4>아이디 찾기</h4>
                        <div class="checkout__input">
                            <p>이메일</p>
                            <input type="email" class="form-control" id="email" name="email" placeholder="회원가입 시 등록한 이메일 주소를 입력해 주세요." autofocus>
                        </div>
                        <button class="site-btn find">확인</button>
                        <a href="/member/login" class="site-btn find">로그인</a>
                        <a href="/member/join" role="button" class="site-btn find">회원가입</a>
                        <a href="/member/findPwd" role="button" class="site-btn find">비밀번호 찾기</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>