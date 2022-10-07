<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<title>Order</title>

</head>
<body>
	<div class="order">
		<h1>order</h1>
		<form:form action="" method="post" modelAttribute="orderAddDTO">
			<p>order</p>
			<input type="text" name="memberId" value="${orderAddDTO.memberId}" placeholder="회원번호"/><br/>
			<input type="text" name="productId" value="${orderAddDTO.productId}" placeholder="상품번호"/><br/>
			<input type="text" name="orderPrice" value="${orderAddDTO.orderPrice}" placeholder="주문가격"/><br/>
			<p>delivery</p>
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br/>
			<input type="text" id="postcode" name="postcode" value="${orderAddDTO.postcode}" placeholder="우편번호">
		&nbsp;&nbsp;<form:errors path="postcode"/><br/>
			<input type="text" id="address" name="address" value="${orderAddDTO.address}" placeholder="주소">
		&nbsp;&nbsp;<form:errors path="address"/><br/>
			<input type="text" id="detailAddress" name="detailAddress" value="${orderAddDTO.detailAddress}" placeholder="상세주소"><br/>
			<input type="text" id="extraAddress" name="extraAddress" value="${orderAddDTO.extraAddress}" placeholder="참고항목">
		&nbsp;&nbsp;<form:errors path="extraAddress"/><br/>
			<input type="text" name="deliveryCost" value="${orderAddDTO.deliveryCost}" placeholder="배송비"/><br/>
			<input type="text" name="request" value="${orderAddDTO.request}" placeholder="요구사항"/><br/>
			<button>입력</button>
		</form:form>
	</div>

	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function sample6_execDaumPostcode() {
			new daum.Postcode({
				oncomplete: function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 각 주소의 노출 규칙에 따라 주소를 조합한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var addr = ''; // 주소 변수
					var extraAddr = ''; // 참고항목 변수

					//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						addr = data.roadAddress;
					} else { // 사용자가 지번 주소를 선택했을 경우(J)
						addr = data.jibunAddress;
					}

					// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
					if(data.userSelectedType === 'R'){
						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
							extraAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if(data.buildingName !== '' && data.apartment === 'Y'){
							extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if(extraAddr !== ''){
							extraAddr = ' (' + extraAddr + ')';
						}
						// 조합된 참고항목을 해당 필드에 넣는다.
						document.getElementById("extraAddress").value = extraAddr;

					} else {
						document.getElementById("extraAddress").value = '';
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('postcode').value = data.zonecode;
					document.getElementById("address").value = addr;
					// 커서를 상세주소 필드로 이동한다.
					document.getElementById("detailAddress").focus();
				}
			}).open();
		}
	</script>
</body>
</html>
