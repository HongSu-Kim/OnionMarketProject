<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="spad">
    <div class="container">
        <div class="checkout__form">
            <form>
                <div class="row">
                    <div class="col-lg-3 col-md-3"></div>
                    <div class="col-lg-6 col-md-6">
                        <h4>아이디 찾기</h4>
                        <div class="checkout__input">
                            <p>${member.email}님의 아이디</p>
                            <span><input type="text" class="checkout__input findId" value="${member.userId}" readonly="readonly"></span>
                        </div>
                        <a href="/member/login" class="site-btn find">로그인</a>
                        <a href="/member/findPwd" class="site-btn find">비밀번호 찾기</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>