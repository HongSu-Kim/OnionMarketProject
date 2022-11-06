<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script type="text/javascript"
            src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yjm3h9nu79&submodules=geocoder"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>




</head>

<body><br/>
<div style=" text-align:center ">

    <input id="address" name="" type="text" placeholder="검색할 주소">


    <input id="submit" type="button" value="주소검색" style=" background-color:#90C8AC;  color:white; width: 5%; height: 40px;">

    <a href="/coordinate/coordinate">
        <input type="submit" value="다시입력" style=" background-color:#90C8AC; color: white; width: 5%; height: 40px;"/>
    </a>
</div>

<div style=" text-align:left ">

<div id="townName" ></div>


<div id="Latitude"></div>


<div id="Longitude"></div>

</div>

<br/>

<div id="map" style="width:1800px;height:500px; margin: 0 auto;  "></div>

<br/><br/>


<form:form action="" method="post">

    <div style=" text-align:center ">

        동네입력<input type="text" name="townName">
        위도입력<input type="text" name="latitude">
        경도입력<input type="text" name="longitude">

        <input type="submit" value="주소등록"  style="width: 5%; height: 40px;"/>
    </div>
</form:form>

<br/><br/><br/><br/>

</body>
</html>