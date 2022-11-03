<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<div class="site-wrap">
    <div class="site-section bg-white">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title related-blog-title">
                    <h2>Post You May Like</h2>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-7 mb-5">

                    <form:form method="post" action="/review/created/${form == null ? orderDTO.orderId : form.orderId}"
                               enctype="multipart/form-data" modelAttribute="form">
                    <h1 class="h4 text-black mb-5" style="font-weight: bold">* 후기를 작성해주세요</h1>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                    <input type="hidden" name="memberId" value="${orderDTO.memberDTO.id}">
                    <input type="hidden" name="orderId" value="${form == null ? orderDTO.orderId : form.orderId}">

                    <div class="row form-group">
                        <div class="col-md-6 mb-3 mb-md-0">
                            <label class="text-black" style="font-size: 13pt;" for="fname">ID</label>
                            <input type="text" id="fname" readonly="readonly" value="${orderDTO.memberDTO.userId}"
                                   class="form-control" style="background-color:#fefefe">
                        </div>
                        <div class="col-md-6">
                            <label class="text-black" style="font-size: 13pt;" for="lname">Name</label>
                            <input type="text" id="lname" readonly="readonly" value="${orderDTO.memberDTO.name}"
                                   class="form-control" style="background-color:#fefefe">
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <label class="text-black" style="font-size: 13pt;" for="email">Email</label>
                            <input type="email" id="email" readonly="readonly" value="${orderDTO.memberDTO.email}"
                                   class="form-control" style="background-color:#fefefe">
                        </div>
                    </div>
                    <hr/>
                    <br/>

                    <!-- 사진 첨부 -->
                    <div class="shoping__cart__btns">
                        <label class="primary-btn cart-btn" for="reviewImageName">사진 첨부하기</label>
                        <input type="file" id="reviewImageName" name="reviewImageName" multiple="multiple"
                               style="display: none" onchange="setDetailImage(event);"/>
                        <div id="images_container"></div>
                    </div>
                    <p id="review_span"><span>상품과 무관한 사진/동영상을 첨부한 리뷰는 통보없이 삭제 및 적립 혜택이 회수됩니다.</span></p><br/>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <label class="text-black" style="font-size: 22pt; font-weight: bold"
                                   for="message">Content</label>
                            <textarea name="reviewContent" id="message" cols="30" rows="7" class="form-control"
                                      placeholder="후기를 남겨주세요. 포토 후기를 남기면 150포인트 지급!"></textarea>
                            <form:errors path="reviewContent" cssClass="field-error"/>
                        </div>
                    </div>


                </div>
                <div class="col-md-5">
                    <div class="p-4 mb-3 bg-white">
                        <div style="display: flex">
                        <span><p class="mb-0 font-weight-bold">Product Name</p>
                        <p class="mb-4">상품이름 ${orderDTO.productDTO.subject}</p></span>
                            <span style="margin-left: 100px;">
                                <img src="/img/product/${orderDTO.productDTO.representativeImage}"/></td>
                        </span>
                        </div>

                        <p class="mb-0 font-weight-bold">Price</p>
                        <p class="mb-4">상품가격 ${orderDTO.productDTO.price}</p>

                        <p class="mb-0 font-weight-bold">Product Content</p>
                        <p class="mb-0">상품내용 ${orderDTO.productDTO.content}</p>

                    </div>

                    <div class="p-4 mb-3 bg-transparent">
                        <div align="center" id="myform">
                            <fieldset>
                                <legend class="text-bold">별점을 선택해주세요</legend>
                                <input type="radio" name="grade" value="5" id="rate1">
                                <label for="rate1">★</label>
                                <input type="radio" name="grade" value="4" id="rate2">
                                <label for="rate2">★</label>
                                <input type="radio" name="grade" value="3" id="rate3">
                                <label for="rate3">★</label>
                                <input type="radio" name="grade" value="2" id="rate4">
                                <label for="rate4">★</label>
                                <input type="radio" name="grade" value="1" id="rate5">
                                <label for="rate5">★</label>
                            </fieldset>
                            <br/>
                            <form:errors path="grade" cssClass="field-error"/>
                        </div>
                        <hr/>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-12" align="right">
                            <input type="submit" value="complete" class="site-btn">
                            <button class="site-btn" style="background-color: #5a6268"
                                    onclick="location.href='/'" type="button">CANCEL
                            </button>
                        </div>
                    </div>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>