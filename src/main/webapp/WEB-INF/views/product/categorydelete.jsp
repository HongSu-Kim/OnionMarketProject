<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setCharacterEncoding("UTF-8");
    String cp = request.getContextPath();
    String userId = request.getParameter("userId");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>카테고리 생성</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>
</head>
<body>


<br/>
<br/>
<br/>


<nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/main/${userId}/"> ${userId} 카테고리 설정 하세요<br/> <br/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar"
                aria-controls="offcanvasDarkNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar"
             aria-labelledby="offcanvasDarkNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">Dark offcanvas</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas"
                        aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        유니폼

                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">


                        </a>

                        <ul class="dropdown-menu dropdown-menu-dark">
                            <c:forEach var="finduniform" items="${finduniform}">


                                <li><a class="dropdown-item" href="#"> ${finduniform.categoryName}</a></li>
                            </c:forEach>
                        </ul>


                    <li class="nav-item dropdown">
                        축구화

                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">


                        </a>

                        <ul class="dropdown-menu dropdown-menu-dark">
                            <c:forEach var="footballboot" items="${footballboot}">


                            <li><a class="dropdown-item" href="#"> ${footballboot.categoryName}</a></li>
                            </c:forEach>
                            <form class="d-flex" role="search">
                                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                <button class="btn btn-success" type="submit">Search</button>
                            </form>
            </div>
        </div>
    </div>
</nav>
<br/><br/><br/>

변경할 부모카테고리번호:




        <br/> <br/>


        <strong>유니폼</strong><br/>

    <c:forEach var="uniformPARENT_ID" items="${uniformPARENT_ID}">
    <input type="text" name="id" value="${uniformPARENT_ID.id} ${uniformPARENT_ID.categoryName}"/><br/>
    </c:forEach>


        <select name="categoryName">
            <c:forEach var="uniformPARENT_ID" items="${uniformPARENT_ID}">

            <option value="${uniformPARENT_ID.categoryName}">${uniformPARENT_ID.categoryName} </option>
                <br/>

            </c:forEach>
        </select>
                <br/>  <br/>  <br/>

                <strong>축구화</strong><br/>

                <c:forEach var="footballbootPARENT_ID" items="${footballbootPARENT_ID}">
                <input type="text" name="id" value="${footballbootPARENT_ID.id} ${footballbootPARENT_ID.categoryName}"/><br/>
                </c:forEach>


                <select name="categoryName">
                    <c:forEach var="footballbootPARENT_ID" items="${footballbootPARENT_ID}">

                        <option value="${footballbootPARENT_ID.categoryName}">${footballbootPARENT_ID.categoryName} </option>


                    </c:forEach>
                </select>

                    <br/>  <br/>  <br/> <br/>  <br/>  <br/>



<form action="/main/categoryUpdate/{userId}"  method="post">
    <input type="text" name="category_id" value="삭제할 번호를 입력!"/>
    <input type="submit"  value="삭제하기"/>


    <br/>

</form>




</body>
</html>