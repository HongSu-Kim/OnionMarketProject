<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!-- Checkout Section Begin -->
<section class="checkout spad">

    <div style=background-color:white;">
        <div id="container">

        </div>


        <div id="modal" class="modal-overlay">
            <div class="modal-window">
                <div class="title">
                    <div style="text-align: right">

                        <a href="/product/wishRangeList"><span class="icon_close"></span></a>
                    </div>
                    <div style="text-align: center">

                    </div>
                    <div style="text-align: center">
                        <div style="position: center"><h3>카테고리 조회 및 수정</h3></div>
                        <br/><br/>
                    </div>



                        <input type="hidden" name="memberId" value="${memberDTO.id}">


                        <select id="topCategory">
                            <option value="">선택하세요</option>
                            <c:forEach var="topCategory" items="${topCategoryList}">
                                <option value="${topCategory.categoryId}">${topCategory.categoryName}</option>
                            </c:forEach>
                        </select>






<%--                        <strong> <input type="submit" value=" 등록"--%>
<%--                                        style="background-color: #90C8AC; color: white"/><br/></strong>--%>

                    <br/>  <br/>
<%--                    <form:form action="/category/categoryDelete" method="get">--%>
                     <button  id="subCategory" name="categoryId"></button>

<%--                    </form:form>--%>



                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>





</section>
<!-- Checkout Section End -->

