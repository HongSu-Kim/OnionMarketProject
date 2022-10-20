<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

    <section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="row">
            <div class="col-lg-12">

                <div class="section-title" style="margin-top: 20px">
                    ${point}포인트가 적립되었습니다. 양파마켓과 즐거운 시간이 되시길 바랍니다 ^^.
                </div>
                <div>
                    <a href="/member/home" type="button">메인페이지로</a>
                </div>


            </div>
        </div>
    </div>
</section>