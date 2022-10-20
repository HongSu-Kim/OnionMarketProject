<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품 리스트</title>
</head>
<body>


<div>
	<h3><상품 리스트입니다></h3><br/>
	<c:forEach var="list" items="${list}">



		<a href="/product/detail?productId=${list.productId}">
			제목: ${list.subject}<br/><br/>
		</a>
		<img src="/img/product/${list.productImageName}"/><br/>
		<c:if test="${list.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${list.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>


</div>


<div>

	<c:forEach var="searchList" items="${searchList}">


		<a href="/product/detail?productId=${searchList.productId}">
			제목: ${searchList.subject}<br/><br/>
		</a>
		<img src="/img/product/${searchList.productImageName}"/><br/>
		<c:if test="${searchList.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${searchList.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>


</div>

<div>

	<c:forEach var="categoryList1" items="${categoryList1}">


		<a href="/product/detail?productId=${categoryList1.productId}">
			제목: ${categoryList1.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList1.productImageName}"/><br/>
		<c:if test="${categoryList1.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList1.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>


</div>


<div>

	<c:forEach var="categoryList2" items="${categoryList2}">


		<a href="/product/detail?productId=${categoryList2.productId}">
			제목: ${categoryList2.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList2.productImageName}"/><br/>
		<c:if test="${categoryList2.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList2.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList3" items="${categoryList3}">


		<a href="/product/detail?productId=${categoryList3.productId}">
			제목: ${categoryList3.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList3.productImageName}"/><br/>
		<c:if test="${categoryList3.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList3.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList4" items="${categoryList4}">


		<a href="/product/detail?productId=${categoryList4.productId}">
			제목: ${categoryList4.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList4.productImageName}"/><br/>
		<c:if test="${categoryList4.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList4.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList5" items="${categoryList5}">


		<a href="/product/detail?productId=${categoryList5.productId}">
			제목: ${categoryList5.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList5.productImageName}"/><br/>
		<c:if test="${categoryList5.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList5.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList6" items="${categoryList6}">


		<a href="/product/detail?productId=${categoryList6.productId}">
			제목: ${categoryList6.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList6.productImageName}"/><br/>
		<c:if test="${categoryList6.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList6.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList7" items="${categoryList7}">


		<a href="/product/detail?productId=${categoryList7.productId}">
			제목: ${categoryList7.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList7.productImageName}"/><br/>
		<c:if test="${categoryList7.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList7.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList8" items="${categoryList8}">


		<a href="/product/detail?productId=${categoryList8.productId}">
			제목: ${categoryList8.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList8.productImageName}"/><br/>
		<c:if test="${categoryList8.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList8.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList9" items="${categoryList9}">


		<a href="/product/detail?productId=${categoryList9.productId}">
			제목: ${categoryList9.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList9.productImageName}"/><br/>
		<c:if test="${categoryList9.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList9.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList10" items="${categoryList10}">


		<a href="/product/detail?productId=${categoryList10.productId}">
			제목: ${categoryList10.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList10.productImageName}"/><br/>
		<c:if test="${categoryList10.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList10.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList11" items="${categoryList11}">



		<a href="/product/detail?productId=${categoryList11.productId}">
			제목: ${categoryList11.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList11.productImageName}"/><br/>
		<c:if test="${categoryList11.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList11.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList12" items="${categoryList12}">


		<a href="/product/detail?productId=${categoryList12.productId}">
			제목: ${categoryList12.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList12.productImageName}"/><br/>
		<c:if test="${categoryList12.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList12.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList13" items="${categoryList13}">


		<a href="/product/detail?productId=${categoryList13.productId}">
			제목: ${categoryList13.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList13.productImageName}"/><br/>
		<c:if test="${categoryList13.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList13.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>

<div>

	<c:forEach var="categoryList14" items="${categoryList14}">


		<a href="/product/detail?productId=${categoryList14.productId}">
			제목: ${categoryList2.subject}<br/><br/>
		</a>
		<img src="/img/product/${categoryList14.productImageName}"/><br/>
		<c:if test="${categoryList14.auctionDeadline ne null}">
			<p style="font-style: italic">
				<경매 진행 중인 상품>
			</p>
		</c:if>
		상품가격: ${categoryList14.price}
		<%--      <p>등록일: ${list.uploadDate}</p><br/>--%>
		<br/><br/><br/>

	</c:forEach>
</div>


<form action="/product/add">
	<input type="submit" value=" 상품 등록하기 " class="btn2"/>
</form>
<form action="/login">
	<input type="submit" value=" 로그인/로그아웃 "/>
</form>

</body>
</html>