<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<title>Complete</title>

</head>
<body>
	<jsp:include page="orderNav.jsp"/>
	<div class="complete">
		<h1>complete</h1>
	</div>
</body>
</html>
