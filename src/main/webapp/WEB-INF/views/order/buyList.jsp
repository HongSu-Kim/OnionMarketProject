<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<title>buyList</title>

</head>
<body>
	<div class="buyList">
		<c:forEach var="orderDTO" items="${page.content}">
			<div>
				<p>주문번호 : ${orderDTO.imp_uid}</p>
				<p>결제금액 : ${orderDTO.orderPayment}</p>
				<p>주문상태 : ${orderDTO.orderState.kor}</p>
				<p>주문날짜 : ${orderDTO.orderDate}</p>
			</div>
		</c:forEach>
	</div>
</body>
</html>
