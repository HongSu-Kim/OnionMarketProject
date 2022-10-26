<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="<%=request.getContextPath()%>"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <style>
        #modal.modal-overlay {
            width: 100%;
            height: 100%;
            position: center;
            left: 0;
            top: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            /*background:#ebebeb;*/
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);



        }

        #modal .modal-window {
            overflow-y:auto;

            background-color: white;

            width: 650px;
            height: 500px;
            position: relative;
            top: -200px;
            padding: 10px;
        }

        #modal .title {
            padding-left: 10px;
            display: inline;

            color: white;
        }

        #modal .title h2 {
            display: inline;
        }

        #modal .close-area {
            display: inline;
            float: right;
            padding-right: 10px;
            cursor: pointer;

            color: white;
        }

        #modal .content {
            margin-top: 20px;
            padding: 10px;
            text-shadow: 1px 1px 2px gray;
            color: black;
        }

        /*a {*/
        /*    color: black;*/

        /*}*/


        p {
            color: black;

        }

        .js-load {
            display: none;
        }

    </style>

</head>


<br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<script type="text/javascript">

    function statusChange(statusItem) {


        var townName = $(statusItem).text();

        townName = $.trim(townName);
        $("#coordinateId").val(townName);

        if (confirm(townName + "으로 동네 설정하시겠습니까?") == true) {    //확인

            document.townadd.submit();

        } else {   //취소

            return false;

        }


    }


</script>


<div style=background-color:white;">
    <div id="container">

    </div>


    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">




                <strong>[동네설정은 최대 3곳 가능]</동네설정은> </strong>
                <br/>


                <form:form action="/town/townresult" method="post">

                    원하는 동네 검색: <input type="text" name="wishtown" value=""/>
                    <input type="hidden" name="memberId" value="${memberDTO.id}">
                    <input type="submit" value="동네검색하기"/><br/>
                </form:form>


                <form:form action="town" method="get">


                    <div style="float: right">
                        <button type="submit" class="btn btnEvent" name="" value=" ">
                            <img src="https://cdn-icons-png.flaticon.com/512/458/458594.png" alt="btnImages"
                                 class="btnImages"
                                 height="23" width="23" border="0">
                        </button>
                    </div>
                </form:form>
                <form:form action="town" name="townadd" method="post">
                    <input type="hidden" name="memberId" value="${memberDTO.id}"/>
                    <p>[원하는 동네를 선택하세요]</p> <input type="hidden" name="townName" id="coordinateId" style="width: 15%;">
                    [동네예시]
                    강남구/ 송파구/ 강동구
                </form:form>
                <div style="text-align: center">
                    <c:if test="${wishtown ==  '강남구'}">

                        <c:forEach var="Gangnam" items="${Gangnam}">




                            <a href="#" onclick="statusChange(this)"> ${Gangnam.townName} </a>

                            <br/>

                        </c:forEach>


                    </c:if>

                    <c:if test="${wishtown == '송파구'}">

                        <c:forEach var="Songpa" items="${Songpa}">
                            <br/>

                            <a href="#" onclick="statusChange(this)"> ${Songpa.townName}</a>
                        </c:forEach>

                    </c:if>


                    <c:if test="${wishtown ==  '강동구'}">

                        <c:forEach var="Gangdong" items="${Gangdong}">
                            <br/>

                            <a href="#" onclick="statusChange(this)"> ${Gangdong.townName}</a>
                        </c:forEach>

                    </c:if>


                </div>

            </div>
        </div>

            <script type="text/javascript">

                $(window).on('load', function () {
                    load('#js-load', '4');
                    $("#js-btn-wrap .button").on("click", function () {
                        load('#js-load', '4', '#js-btn-wrap');
                    })
                });
            </script>

        <script>




            const modal = document.getElementById("modal")

            function modalOn() {
                modal.style.display = "flex"
            }

            function isModalOn() {
                return modal.style.display === "flex"
            }

            function modalOff() {
                modal.style.display = "none"
            }

            const closeBtn = modal.querySelector(".close-area")
            closeBtn.addEventListener("click", e => {
                modalOff();
            })

        </script>

    </div>
    </body>
</html>