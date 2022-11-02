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


        }

        #modal .modal-window {
            overflow-y: auto;

            width: 850px;
            height: 600px;
            position: relative;
            top: -100px;
            padding: 10px;
            border-radius: 25px;
            background-color: whitesmoke;
            border: 1px black;

            box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);;


        }

        #modal .title {
            padding-left: 10px;
            display: inline;

            color: black;
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

        a {
            color: black;
            text-decoration-line: none;
            text-decoration: none;

        }

        a:link {
            color: red;
            text-decoration: none;
        }

        a:visited {
            color: black;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }


        p {
            color: black;
            border-top: 1px solid #d7d7d7;
            border-left: 1px solid #e0e0e0;
            border-right: 1px solid #e0e0e0;
            border-bottom: 0 none;

        }

        input {
            width: 100%;
            height: 46px;
            font-size: 16px;
            color: #6f6f6f;
            padding-left: 15px;
            border: 1px solid #e1e1e1;
            border-radius: 20px;
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
                <div style="text-align: right">

                 <a href="/town/town">뒤로가기</a>
                </div>
                <div style="text-align: center">
                    <h3> [동네는 최대 3 지역 가능]</h3>
                </div>


                <br/>

                <form:form action="/town/townresult" method="post">

                    <input type="text" name="wishtown" value="" placeholder="  원하는 동네 검색"/>
                    <br/><br/>

                    <input type="hidden" name="memberId" value="${memberDTO.id}">

                    <input type="submit" value="동네검색하기" style="background-color: #47cd65; color: white"/><br/>
                </form:form>


                <form:form action="town" method="get">


                </form:form>
                <form:form action="town" name="townadd" method="post">
                    <input type="hidden" name="memberId" value="${memberDTO.id}"/>
                    <input type="hidden" name="townName" id="coordinateId" style="width: 15%;">

                </form:form><br/>
                <div style="text-align: center">
                    <c:if test="${wishtown ==  '강남구'}">

                        <c:forEach var="Gangnam" items="${Gangnam}">


                            <a href="#" onclick="statusChange(this)"><p
                                    style="margin-top: 10px; margin-bottom: 10px"> ${Gangnam.townName} </p></a>

                            <p></p>

                        </c:forEach>


                    </c:if>

                    <c:if test="${wishtown == '송파구'}">

                        <c:forEach var="Songpa" items="${Songpa}">


                            <a href="#" onclick="statusChange(this)"><p
                                    style="margin-top: 10px; margin-bottom: 10px"> ${Songpa.townName}</p></a>
                            <p></p>
                        </c:forEach>

                    </c:if>


                    <c:if test="${wishtown ==  '강동구'}">

                        <c:forEach var="Gangdong" items="${Gangdong}">


                            <a href="#" onclick="statusChange(this)"><p
                                    style="margin-top: 10px; margin-bottom: 10px"> ${Gangdong.townName}</p></a>
                            <p></p>
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