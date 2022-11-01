<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<!-- Checkout Section Begin -->
<section class="spad">
    <div class="container">
        <div class="checkout__form">
            <form action="/member/modifyProfileImg" method="post" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row">
                    <div class="col-lg-3 col-md-3"></div>
                    <div class="col-lg-6 col-md-6">
                    <h4>프로필 수정</h4>

                        <div class="checkout__input">
                            <p>프로필</p>
                            <img class="rounded-circle mt-2 preview" id="preview"/>
                            <input type="file" name="profileImg" onchange="readURL(this);"/>
                        </div>
                        <button type="submit" id="submit" class="site-btn">수정하기</button>
                        <button type="button" class="site-btn" onclick="location.href='/member/mypage'">돌아가기</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</section>
<!-- Checkout Section End -->
