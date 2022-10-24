<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>


</head>
<body>

<h2 class="head-title">오늘의 중고 인기 검색어</h2>


<ol id="top-keywords-list">
    <li>
        <a href="/search/%EC%9E%90%EC%A0%84%EA%B1%B0">
            <p class="keyword-text">자전거</p> <p class="rank"></p>

        </a>      </li>
    <li>
        <a href="/search/%ED%8F%AC%EC%BC%93%EB%AA%AC%EB%B9%B5">
            <p class="keyword-text">포켓몬빵</p>
            <p class="rank">
                -
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EC%BA%A0%ED%95%91">
            <p class="keyword-text">캠핑</p>
            <p class="rank">
                <span class="up">↑</span>
                <span class="changed_rank">2</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EC%95%84%EC%9D%B4%ED%8F%B0">
            <p class="keyword-text">아이폰</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">1</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EB%8B%B9%EA%B7%BC%EC%95%8C%EB%B0%94">
            <p class="keyword-text">당근알바</p>
            <p class="rank">
                <span class="up">↑</span>
                <span class="changed_rank">1</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EC%9D%98%EC%9E%90">
            <p class="keyword-text">의자</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">2</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EB%83%89%EC%9E%A5%EA%B3%A0">
            <p class="keyword-text">냉장고</p>
            <p class="rank">
                -
            </p>
        </a>      </li>
    <li>
        <a href="/search/%ED%85%90%ED%8A%B8">
            <p class="keyword-text">텐트</p>
            <p class="rank">
                <span class="up">↑</span>
                <span class="changed_rank">4</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EB%85%B8%ED%8A%B8%EB%B6%81">
            <p class="keyword-text">노트북</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">1</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%ED%95%A0%EB%A1%9C%EC%9C%88">
            <p class="keyword-text">할로윈</p>
            <p class="rank">
                <span class="up">↑</span>
                <span class="changed_rank">6</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EC%95%84%EC%9D%B4%ED%8C%A8%EB%93%9C">
            <p class="keyword-text">아이패드</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">2</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EC%95%A0%ED%94%8C%EC%9B%8C%EC%B9%98">
            <p class="keyword-text">애플워치</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">2</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EC%98%A4%ED%86%A0%EB%B0%94%EC%9D%B4">
            <p class="keyword-text">오토바이</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">2</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EA%B0%80%EB%B0%A9">
            <p class="keyword-text">가방</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">1</span>
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EC%87%BC%ED%8C%8C">
            <p class="keyword-text">쇼파</p>
            <p class="rank">
                -
            </p>
        </a>      </li>
    <li>
        <a href="/search/%EA%B3%A8%ED%94%84">
            <p class="keyword-text">골프</p>
            <p class="rank">
                <span class="down">↓</span>
                <span class="changed_rank">2</span>
            </p>
        </a>      </li>
</ol>

</body>
</html>