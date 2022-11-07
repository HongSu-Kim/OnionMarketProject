<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<style>
    .n-section-title {
        border-bottom: 3px solid #90C8AC;
        padding-bottom: 10px;
        margin-top: 200px;
        line-height: 1.5;
        font-size: 20px;
        position: relative;
    }
    .n-section-title .tit {
        display: inline-block;
        font-size: 20pt;
        font-weight: bolder;
    }
</style>

<section class="hero hero-normal" style="margin-top: 240px">
    <div class="container" style="width: 800px;">
        <div style="margin-bottom: 150px">
            <div class="n-section-title" style="margin-bottom: 30px">
            </div>

            <div style="margin-bottom: 35px">
                <h4 style="margin-bottom: 5px; font-weight: bold"> ${noticeDTO.noticeSubject}</h4>
                <div style="font-size: 12pt; color: #5a6268">작성일자:${noticeDTO.noticeDate}</div>
                <span style="float: right">조회수 : ${noticeDTO.hitCount}</span>
            </div>
            <hr/>



            <div>${noticeDTO.noticeContent}</div>

            <div>
                <c:forEach items="${noticeDTO.noticeImageList}" var="noticeImageDTO">
                    <img src="/img/notice/${noticeImageDTO.noticeImageName}" width="770" height="500"
                    style="margin-top: 30px; margin-bottom: 30px"/>
                </c:forEach>
            </div>
        </div>


        <div align="center">
            <c:if test="${memberDTO.role == 'ADMIN'}">
                <button class="site-btn" onclick="location.href='/notice/update/${id}'">수정하기</button>
                <button class="site-btn" onclick="location.href='/notice/delete/${id}'">삭제</button>
            </c:if>
            <button class="site-btn" onclick="location.href='/notice/list'">돌아가기</button>
        </div>

    </div>
</section>
