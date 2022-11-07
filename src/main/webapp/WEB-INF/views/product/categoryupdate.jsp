<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Checkout Section Begin -->
<section class="checkout spad">


    <div class="container">
        <div class="checkout__form" style="width: 800px; margin: auto">
            <br/><br/>

            <h4>카테고리조회<span style="font-size: small;color:#FF5058;margin: 0px 0px 0px 32px">*상위카테고리 선택 </span></h4>

            <div style="text-align: right ">
                <form:form action="/category/category" method="get">
                <input type="submit" value="뒤로가기" onclick="select();"
                       style="font-size: 16px; width: 15%; height: 43px; margin-top: -30px;
                 border-style: solid ; border-color: #90C8AC; background-color: #90C8AC; color: white"/>
            </div>
            </form:form>

            <div class="row">
                <div class="col-lg-12">
                    <div class="checkout__input">
                        <p> 상위 카테고리 <span>*</span></p>

                        <select id="topCategory">
                            <c:forEach var="topCategory" items="${topCategoryList}">
                                <option value="${topCategory.categoryId}">${topCategory.categoryName}</option>
                            </c:forEach>
                        </select>

                        <br/> <br/>

                        <a type="button" id="subCategory"></a>


                        <br/><br/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <br/><br/><br/><br/>

    </body>
    </html>

</section>


