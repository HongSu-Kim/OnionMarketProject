<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<style>
    .n-section-title {
        border-bottom: 3px solid #47cd65;
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

<section class="hero hero-normal">
    <div class="container" style="width: 800px;">
        <div style="margin-bottom: 20px">
            <div class="n-section-title">
                <h1 class="tit">게시글</h1>
            </div>

            <div>
                제목 : ${noticeDTO.noticeSubject}
                <span style="float: right">조회수 : ${noticeDTO.hitCount}</span>
            </div>
            <hr/>
            <div>작성일자:${noticeDTO.noticeDate}</div>

            <div>${noticeDTO.noticeContent}</div>

            <div>
                <c:forEach items="${noticeDTO.noticeImageList}" var="noticeImageDTO">
                    <img src="/img/notice/${noticeImageDTO.noticeImageName}" width="770" height="500"/>
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
