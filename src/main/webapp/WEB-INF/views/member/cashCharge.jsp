<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="spad">
    <div class="container">
        <div class="second">
            <img class="card-img-top" width="20" height="300" src="/files/kakao.jpg" alt="..." />
            <p style="font-weight: bold">카카오페이 현재 사용가능</p>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="5000"><span>5,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="10000"><span>10,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="15000"><span>15,000원</span></label><br/>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="20000"><span>20,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="25000"><span>25,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="30000"><span>30,000원</span></label><br/>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="35000"><span>35,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="40000"><span>40,000원</span></label>
            <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>50,000원</span></label>
            <p>카카오페이의 최소 충전금액은 5,000원이며 <br/>최대 충전금액은 50,000원 입니다.</p>
            <button type="button" class="btn btn-outline-dark" id="charge_kakao">충 전 하 기</button>
        </div>
    </div>
</section>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>