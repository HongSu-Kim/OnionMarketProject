<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품 리스트</title>
</head>
<body>


<div>
	<c:if test="${townList ne null}">
	<p>현재동네</p>
			<select onchange="if(this.value) location.href=(this.value);">
				<c:forEach var="townList" items="${townList}">
					<option value="/product/list">${townList.townName}</option>
				</c:forEach>
					<option value="/town/town">내 동네 설정하기</option>
			</select>
	</c:if>
</div>
<!-- Featured Section Begin -->
<section class="featured spad">
	<div class="container">
		<div class="row featured__filter">
			<c:forEach var="list" items="${list}">
			<div class="col-lg-3 col-md-4 col-sm-6" style="padding: 15px;">
				<div class="featured__item">
					<div class="featured__item__pic set-bg" data-setbg="/img/product/${list.productImageName}">
						<c:if test="${list.auctionDeadline ne null}">
							<h5 style="color: #47cd65;margin: 0px 0px 0px 50px">
								<경매 진행 중인 상품>
							</h5>
						</c:if>
						<ul class="featured__item__pic__hover">
							<li><a href="/wish/addWish/${productId}"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
						<div style="margin-top:93%;"><c:if test="${list.payStatus eq true}"><img src="/template/img/product/pay.png"></c:if></div>
					</div>
					<div class="featured__item__text">
						<h6><a href="/product/detail/${list.productId}">${list.subject}</a></h6>
						<fmt:parseDate var="uploadDate" value="${list.uploadDate}" pattern="yyyy-MM-dd'T'HH:mm"/>
						<h5 style="text-align: left"><fmt:formatNumber maxFractionDigits="3" value="${list.price}"/></h5>
						<h5 style="text-align: right"><fmt:formatDate value="${uploadDate}" pattern="MM/dd"/></h5>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</section>
<!-- Featured Section End -->

<div>

	<c:forEach var="searchList" items="${searchList}">


		<a href="/product/detail/${searchList.productId}">
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


		<a href="/product/detail/${categoryList1.productId}">
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


		<a href="/product/detail/${categoryList2.productId}">
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


		<a href="/product/detail/${categoryList3.productId}">
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


		<a href="/product/detail/${categoryList4.productId}">
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


		<a href="/product/detail/${categoryList5.productId}">
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


		<a href="/product/detail/${categoryList6.productId}">
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


		<a href="/product/detail/${categoryList7.productId}">
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


		<a href="/product/detail/${categoryList8.productId}">
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


		<a href="/product/detail/${categoryList9.productId}">
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


		<a href="/product/detail/${categoryList10.productId}">
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



		<a href="/product/detail/${categoryList11.productId}">
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


		<a href="/product/detail/${categoryList12.productId}">
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


		<a href="/product/detail/${categoryList13.productId}">
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


		<a href="/product/detail/${categoryList14.productId}">
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

</body>
</html>