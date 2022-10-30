<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<section class="spad">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">Edogaru</span><span class="text-black-50">edogaru@mail.com.my</span><span> </span></div>
                <div>
                    <c:if test="${!memberDTO.followCheck}">
                        <button type="button" onclick="location.href='/follow/addFollow/${memberDTO.id}'">팔로우</button>
                    </c:if>
                    <c:if test="${memberDTO.followCheck}">
                        <button type="button" onclick="location.href='/follow/removeFollow/${memberDTO.id}'">언팔로우</button>
                    </c:if>
                    <c:if test="${!memberDTO.blockCheck}">
                       <button type="button" onclick="location.href='/block/addBlock/${memberDTO.id}'">차단</button>
                    </c:if>
                    <c:if test="${memberDTO.blockCheck}">
                       <button type="button" onclick="location.href='/block/removeBlock/${memberDTO.id}'">차단해제</button>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-item s-center mb-3">
                        <h4 class="text-right">프로필</h4>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-12"><label class="labels">Name</label><input type="text" class="form-control" placeholder="first name" value=""></div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" class="form-control" placeholder="enter phone number" value=""></div>
                        <div class="col-md-12"><label class="labels">Address Line 1</label><input type="text" class="form-control" placeholder="enter address line 1" value=""></div>
                        <div class="col-md-12"><label class="labels">Address Line 2</label><input type="text" class="form-control" placeholder="enter address line 2" value=""></div>
                        <div class="col-md-12"><label class="labels">Postcode</label><input type="text" class="form-control" placeholder="enter address line 2" value=""></div>
                        <div class="col-md-12"><label class="labels">State</label><input type="text" class="form-control" placeholder="enter address line 2" value=""></div>
                        <div class="col-md-12"><label class="labels">Area</label><input type="text" class="form-control" placeholder="enter address line 2" value=""></div>
                        <div class="col-md-12"><label class="labels">Email ID</label><input type="text" class="form-control" placeholder="enter email id" value=""></div>
                        <div class="col-md-12"><label class="labels">Education</label><input type="text" class="form-control" placeholder="education" value=""></div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6"><label class="labels">Country</label><input type="text" class="form-control" placeholder="country" value=""></div>
                        <div class="col-md-6"><label class="labels">State/Region</label><input type="text" class="form-control" value="" placeholder="state"></div>
                    </div>
<%--                    <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">Save Profile</button></div>--%>
                </div>
            </div>
        </div>
    </div>
</section>