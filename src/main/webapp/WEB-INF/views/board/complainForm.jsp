<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>

<section class="hero hero-normal">
    <div class="container" style="width: 1200px;">
        <div class="py-5 text-center">
            <h3>신고 양식</h3>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <form method="post" action="/complain/created" id="myForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                        신고하기
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-scrollable" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">신고하기</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>

                                <input type="hidden" name="memberId" value="${memberDTO.id}">
                                <input type="hidden" name="productId" value="${productDTO.productId}">

                                <div class="modal-body">
                                    <div>
                                        <p>신고자 : ${memberDTO.name}</p>
                                        <p>신고대상 회원 : ${targetDTO.userId}</p>
                                        <p>상품명 : ${productDTO.subject}</p>
                                    </div>
                                    <hr/>
                                    <div style="height: 80px;">
                                        <h5>사유선택</h5>
                                        <select name="complainType" id="complainType" required>
                                            <option selected="selected" value="">신고유형을 골라주세요</option>
                                            <option value="0">스팸홍보/도배글입니다</option>
                                            <option value="1">음란물입니다</option>
                                            <option value="2">욕설과 불쾌한 표현이 있습니다</option>
                                            <option value="3">청소년에게 유해한 내용입니다</option>
                                            <option value="4">불법정보를 포함하고 있습니다</option>
                                            <option value="5">개인정보 노출 게시물입니다</option>
                                        </select>
                                    </div>
                                    <br/>

                                    <div>
                                        <label>신고 내용을 입력해주세요</label>
                                        <textarea class="form-control" placeholder="신고 내용을 입력해주세요" id="complainContent"
                                                  name="complainContent" style="height: 100px"
                                        minlength="10" maxlength="100"></textarea>
                                    </div>
                                </div>

                                <div class="modal-footer"><!-- 버튼부분 -->
                                    <button type="button" class="site-btn" style="background-color: #7e828f"
                                            data-dismiss="modal">취소
                                    </button>
                                    <button type="submit" class="site-btn" onclick="addComplain();">제출하기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>


            </div>
        </div>
    </div>
</section>
