<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="spad">
    <div class="container">
        <div class="checkout__form">
            <form action="/member/preModify" method="post" name="myForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row">
                    <div class="col-lg-3 col-md-3"></div>
                    <div class="col-lg-6 col-md-6">
                        <div class="checkout__input">
                            <p>비밀번호<span>*</span></p>
                            <input type="password" class="form-control" name="preModifypwd" placeholder="비밀번호를 입력해주세요">
                        </div>
                        <button type="submit" class="site-btn-modify">확인</button>
                        <a href="/member/mypage" role="button" class="site-btn-modify">마이 페이지</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
