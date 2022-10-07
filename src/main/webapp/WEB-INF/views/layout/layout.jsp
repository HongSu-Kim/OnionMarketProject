<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<html>
<head>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" href="${cp}<tiles:getAsString name = 'css1'/>"/>
<link rel="stylesheet" href="${cp}<tiles:getAsString name = 'css2'/>"/>

<title>Onion</title>

</head>
<body>

	<div class="layout">
		<h5>Layout</h5>

        <%-- HEADER --%>
		<tiles:insertAttribute name="header"/>
        <%-- HEADER END--%>

        <%-- BODY --%>
		<tiles:insertAttribute name="body"/>
        <%-- BODY END--%>

        <%-- FOOTER --%>
		<tiles:insertAttribute name="footer"/>
        <%-- FOOTER END--%>

	</div>

<script src="${cp}<tiles:getAsString name = 'js1'/>"></script>
<script src="${cp}<tiles:getAsString name = 'js2'/>"></script>

</body>
</html>
