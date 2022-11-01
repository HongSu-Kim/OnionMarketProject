<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<section>
    <div class="container" style="width: 1000px; margin-top: 200px">
        <div class="MainBanner_1">
            <a class="MainBanner_2">Customer Service And Questions</a>
        </div>
    </div>

    <div>
        <div class="container-tight">
            <div data-duration-in="300" data-duration-out="100" data-current="Tab 1" data-easing="ease" class="w-tabs">

                <div class="tab-menu-round w-tab-menu">
                    <a href="/notice/list" data-w-tab="Tab 1" class="tab-link-round w-inline-block w-tab-link w--current">
                        <div>공지사항</div>
                    </a>
                    <a href="/notice/list" data-w-tab="Tab 2" class="tab-link-round w-inline-block w-tab-link">
                        <div>양파마켓 도움말</div>
                    </a>
                    <a data-w-tab="Tab 3" class="tab-link-round w-inline-block w-tab-link">
                        <div>1:1 문의하기</div>
                    </a>
                </div>


                <div class="w-tab-content">
                    <div data-w-tab="Tab 1" class="w-tab-pane w--tab-active">
                        <div class="tab-pane-wrap">

                            <!-- Shoping Cart Section Begin -->
                            <section>
                                <div class="container" style="width: 1000px;">

                                    <!-- 검색 -->
                                    <form action="/notice/list" class="d-flex" method="GET" style="margin-top: 10px; margin-bottom: 5px">
                                        <div class="col-lg-8">
                                            <div style="height: 45px">
                                                <input type="text" name="word" class="searchIn" placeholder="Search">
                                                <button class="site-btn" type="submit" style="height: 44px; border-radius: 20px; margin-left: 5px">
                                                    <span class="icon_search"></span></button>
                                            </div>
                                        </div>

                                        <c:if test="${memberDTO.role == 'ADMIN'}">
                                            <div class="col-lg-4">
                                                <div style="text-align: center; float: right">
                                                    <a href="/notice/created" class="site-btn" style="border-radius: 20px">공지 등록</a>
                                                </div>
                                            </div>
                                        </c:if>
                                    </form>
                                    <hr style="background-color: #47cd65; height: 1px"/>
                                    <!-- 검색 끝 -->

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="shoping__cart__table">
                                                <table>
                                                    <colgroup>
                                                        <col style="width:5%">
                                                        <col style="width:55%">
                                                        <col style="width:10%">
                                                        <col style="width:20%">
                                                        <col style="width:15%">
                                                    </colgroup>

                                                    <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>제목</th>
                                                        <th>작성자</th>
                                                        <th>등록일</th>
                                                        <th>조회수</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:if test="${empty noticelist.content}">
                                                        <tr>
                                                            <td colspan="5">등록된 공지가 없습니다.</td>
                                                        </tr>
                                                    </c:if>

                                                    <c:forEach var="dto" items="${noticelist.content }">
                                                        <tr>
                                                            <td>${noticelist.totalElements - (noticelist.number * noticelist.size) - noticelist.content.indexOf(dto)}</td>
                                                            <td><a href="/notice/article/${dto.noticeId}">${dto.noticeSubject}</a></td>
                                                            <td>${dto.memberDTO.userId}</td>
                                                            <td>${dto.noticeDate}</td>
                                                            <td>${dto.hitCount}</td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- 페이징 -->
                                <div class="text-xs-center" style="margin-bottom: 20px">
                                    <ul class="product__pagination text-center">
                                        <!-- 이전 -->
                                        <c:choose>
                                            <c:when test="${noticelist.first}"></c:when>
                                            <c:otherwise>
                                                <%--                    <a href="/notice/list/?field=${field}&word=${word}&page=0">처음으로</a>--%>
                                                <a href="/notice/list/?field=${field}&word=${word}&page=${noticelist.number-1}">◀</a>
                                            </c:otherwise>
                                        </c:choose>

                                        <!-- 페이지 그룹 -->
                                        <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                                            <c:choose>
                                                <c:when test="${noticelist.pageable.pageNumber+1 == i}">
                                                    <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${i-1}">${i}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                        <!-- 다음 -->
                                        <c:choose>
                                            <c:when test="${noticelist.last}"></c:when>
                                            <c:otherwise>
                                                <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${noticelist.number+1}">▶</a>
                                                <%--                    <a href="/notice/list/?field=${param.field}&word=${param.word}&page=${noticelist.totalPages-1}">끝으로</a>--%>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </div>
                                <!-- 페이징 끝 -->
                            </section>
                        </div>
                    </div>


                    <div data-w-tab="Tab 2" class="w-tab-pane">
                        <div class="tab-pane-wrap">
                            <section>
                                <%-- <div class="top-main-section">
                                    <div class="section-title" style="margin-top: 50px">
                                        <h2>frequently Question</h2>
                                    </div>
                                </div> --%>

                                <div>
                                    <div class="container-tight">
                                        <div data-duration-in="300" data-duration-out="100" data-current="Tab 1" data-easing="ease" class="w-tabs">

                                            <div class="w-tab-content">
                                                <div data-w-tab="Tab 1" class="w-tab-pane w--tab-active">
                                                    <div class="tab-pane-wrap">

                                                        <c:forEach var="dto" items="${qnaList}">
                                                            <div class="faq-question-wrap">
                                                                <a href="#" class="faq-question-bar w-inline-block">
                                                                    <div class="question-title">${dto.noticeSubject}</div>
                                                                    <img src="https://assets.website-files.com/5e865e09d8efa3310676b585/5e865e09d8efa3059f76b604_Plus.svg"
                                                                         alt="" class="plus"/></a>
                                                                <div class="faq-content">
                                                                    <p class="faq-paragraph">
                                                                        ${dto.noticeContent}
                                                                    </p>

                                                                    <c:if test="${memberDTO.role == 'ADMIN'}">
                                                                        <div style="float: right; margin-right: 20px">
                                                                            <button type="submit" onclick="location.href='/notice/update/${dto.noticeId}'"
                                                                                    class="adminbutton">수정
                                                                            </button>
                                                                            <button type="submit" onclick="location.href='/notice/delete/${dto.noticeId}'"
                                                                                    class="adminbutton" style="background-color: #7e828f">삭제
                                                                            </button>
                                                                        </div>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </c:forEach>

                                                    </div>
                                                </div>


                                                <div data-w-tab="Tab 2" class="w-tab-pane">
                                                    <div class="tab-pane-wrap">
                                                        <div>
                                                            <div class="second-tab"><h4>게시중단 요청 서비스란?</h4><br/></div>

                                                            <div class="service-content">
                                                                양파마켓 서비스 상에서 다른 회원의 게시물이 고객님의 저작물을 무단으로 도용하거나, 고객님의 명예를 훼손한다고 생각되는 경우, 또는 초상권 및 사생활을
                                                                침해한다고 판단되는 경우에 그 게시물을 임시로 게재 중단해줄 것을 요청하실 수 있는 서비스입니다.<br/> 양파마켓 게시중단요청서비스는 현행 저작권법과
                                                                정보통신망 이용촉진 및 정보보호 등에 관한 법률(이하 정통망법)을 준수하여 운영되고 있습니다.
                                                                <br/><br/>
                                                                [ 관련법규 내용보기 ]<br/><br/>
                                                                게시물이 실제로 고객님의 권리를 침해하였는지 여부는 양파마켓에서 임의로 판단할 수 없으며 적법한 자격을 갖춘 관련 기관을 통해 권리 침해 여부에 대한
                                                                판단 및 게시물 조치에 대한 결정을 얻으셔야 합니다. 양파마켓은 관련 기관에서 최종 판단을 내리기 전까지 해당 게시물을 임시 게재 중단함으로써 추가적인
                                                                권리 침해를 방지하기 위해 게시중단요청서비스를 마련 해놓고 있습니다.
                                                                게시중단요청서비스는 고객님을 보호하기 위한 임시적인 방법일 뿐 궁극적인 해결은 관련 기관을 통한 법률 상담 및 구제 절차를 통해 진행하실 수 있습니다.
                                                                관련기관 정보보기
                                                                신속하고 정확한 게시중단요청서비스 운영을 통해 양파마켓은 온라인 서비스 제공자로서의 책임을 다하고자 노력하고 있으며, 당사자 간의 분쟁이 적법한 절차를
                                                                통해 합리적으로 해결될 때까지 고객 모두를 보호하고자 합니다. <br/><br/>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div data-w-tab="Tab 3" class="w-tab-pane">
                                                    <div class="tab-pane-wrap">
                                                        <a href="/inquiry/created"><p class="createQna">1:1 문의하기 바로가기</p></a>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <script src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=5e865e09d8efa3310676b585"
                                        type="text/javascript" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
                                        crossorigin="anonymous"></script>
                                <script src="https://assets.website-files.com/5e865e09d8efa3310676b585/js/webflow.bad13ffae.js"
                                        type="text/javascript"></script>
                            </section>


                        </div>
                    </div>



                    <div data-w-tab="Tab 3" class="w-tab-pane">
                        <div class="tab-pane-wrap">
                            <a href="/inquiry/created"><p class="createQna">1:1 문의하기 바로가기</p></a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <script src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=5e865e09d8efa3310676b585"
            type="text/javascript" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>
    <script src="https://assets.website-files.com/5e865e09d8efa3310676b585/js/webflow.bad13ffae.js"
            type="text/javascript"></script>
</section>