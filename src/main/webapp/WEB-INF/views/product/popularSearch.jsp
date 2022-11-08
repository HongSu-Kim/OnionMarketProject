<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<h2 class="head-title">오늘의 중고 인기 검색어</h2>


<ol id="top-keywords-list">

    <li>
        <a href="/search/%EC%BA%A0%ED%95%91">
            <p class="keyword-text">취직</p>
            <p class="rank">
                <span class="up">↑</span>
                <span class="changed_rank">50</span>
            </p>
        </a>      </li>

    <li>
        <a href="/search/%EC%9D%98%EC%9E%90">
            <p class="keyword-text">취업난</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">1</span>
            </p>
        </a>      </li>



    <c:forEach var="searchRank" items="${searchRank}">
    <li>

    <a href="/search/%EC%BA%A0%ED%95%91">
            <p class="keyword-text">${searchRank.searchName}</p> <p class="rank"> <span class="up">↑</span>
            <span class="changed_rank">2</span></p>

        </a>      </li>
    </c:forEach>


</ol>
