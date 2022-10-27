<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<section class="spad">
    <div class="col-lg-12">
        <div class="section-title related-blog-title">
            <h2>LOGIN</h2>
        </div>
    </div>

    <div class="container">
        <form action="/member/loginProc" method="post" name="myForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="form-group">
                <label for="userId">아이디</label>
                <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 입력해주세요" style="width: 250px;">
            </div>

            <div class="form-group">
                <label for="pwd">비밀번호</label>
                <input type="password" class="form-control" id="pwd" name="pwd" placeholder="비밀번호를 입력해주세요" style="width: 250px;">
            </div>

            <c:if test="${not empty error}">
                <p id="valid" class="alert alert-danger">${exception}</p>
            </c:if>

            <button class="btn btn-primary bi bi-lock-fill">로그인</button>
            <a href="/member/join" role="button" class="btn btn-primary bi bi-lock-fill">회원가입</a>
            <a href="/member/findId" role="button" class="btn btn-primary bi bi-lock-fill">아이디 찾기</a>
            <a href="/member/findPwd" role="button" class="btn btn-primary bi bi-lock-fill">비밀번호 찾기</a>
        </form>
    </div>
</section>