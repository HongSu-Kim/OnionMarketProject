<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setCharacterEncoding("UTF-8");
    String cp = request.getContextPath();
    //String userId = request.getParameter("userId");
    //Object userId = session.getAttribute("userId");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게 시 판</title>
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




<nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/main/${userId}"> ${userId} 어서오세요<br/> <br/></a>
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
                        </ul>
                        <form class="d-flex" role="search">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-success" type="submit">Search</button>
                        </form>
            </div>
        </div>
    </div>
</nav>
<br/><br/><br/><br/>
<form action="/town" method="get">
    <input type="submit" value="동네 다시고르기"/>
</form>


<form action="/towncreate/${userId}" method="post">
    <input type="hidden" name="userId" value="${userId}">
   번호입력하세요 <input type="text" name="coordinate" value="" style="width: 15%;">
   <input type="submit" value="동네설정하기"/>
</form>

<br/><br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<strong><${wishtown}></strong>




<c:if test="${wishtown == '강남구'}">

<c:forEach var="Gangnam" items="${Gangnam}">
    <br/>

  <input type="text" value="${Gangnam.id} ${Gangnam.townName}" style="width: 24%;"/>
</c:forEach>

<br/>

</c:if>

<c:if test="${wishtown == '송파구'}">

<c:forEach var="Songpa" items="${Songpa}">
    <br/>

    <input type="text" value="${Songpa.id} ${Songpa.townName}" style="width: 24%;"/>
</c:forEach>

</c:if>


<c:if test="${wishtown == '강동구'}">

    <c:forEach var="Gangdong" items="${Gangdong}">
        <br/>

        <input type="text" value="${Gangdong.id} ${Gangdong.townName}" style="width: 24%;"/>
    </c:forEach>

</c:if>






</body>
</html>