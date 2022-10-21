<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<html lang="zxx">
<head>

	<meta charset="UTF-8">
	<meta name="description" content="Ogani Template">
	<meta name="keywords" content="Ogani, unica, creative, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">

	<title>Onion | Template</title>

	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

	<!-- Css Styles -->
	<link rel="stylesheet" href="/template/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="/template/css/font-awesome.min.css" type="text/css">
	<link rel="stylesheet" href="/template/css/elegant-icons.css" type="text/css">
	<link rel="stylesheet" href="/template/css/nice-select.css" type="text/css">
	<link rel="stylesheet" href="/template/css/jquery-ui.min.css" type="text/css">
	<link rel="stylesheet" href="/template/css/owl.carousel.min.css" type="text/css">
	<link rel="stylesheet" href="/template/css/slicknav.min.css" type="text/css">
	<link rel="stylesheet" href="/template/css/style.css" type="text/css">
	<link rel="stylesheet" href="/css/onion.css" type="text/css">

	<link rel="stylesheet" href="${cp}<tiles:getAsString name = 'css1'/>"/>
	<link rel="stylesheet" href="${cp}<tiles:getAsString name = 'css2'/>"/>

</head>
<body>

	<div class="layout">

		<!-- HEADER -->
		<tiles:insertAttribute name="header"/>
		<!-- HEADER END-->

		<%-- BODY --%>
		<tiles:insertAttribute name="body"/>
		<%-- BODY END--%>

		<%-- FOOTER --%>
		<tiles:insertAttribute name="footer"/>
		<%-- FOOTER END--%>

	</div>

	<!-- Js Plugins -->
	<script src="/template/js/jquery-3.3.1.min.js"></script>
	<script src="/template/js/bootstrap.min.js"></script>
	<script src="/template/js/jquery.nice-select.min.js"></script>
	<script src="/template/js/jquery-ui.min.js"></script>
	<script src="/template/js/jquery.slicknav.js"></script>
	<script src="/template/js/mixitup.min.js"></script>
	<script src="/template/js/owl.carousel.min.js"></script>
	<script src="/template/js/main.js"></script>
<%--	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>--%>

	<!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
	<!-- 주소 api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<!-- 검증 -->
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js" ></script>

	<script src="${cp}<tiles:getAsString name = 'js1'/>"></script>
	<script src="${cp}<tiles:getAsString name = 'js2'/>"></script>


</body>
</html>
