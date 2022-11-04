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
    <title>게 시 판</title>
    <style type="text/css">


        .seek-bar {
            margin: 50px auto;
            position: relative;
            width:200px;
            height: 0px;


            border-radius: 15px;
            background-color: green;
            background: #00de63;
            border-radius: 15px;
        }

        .seek-bar > .circle {
            cursor: grab;
            position: absolute;
            left: 0;
            top: 50%;
            width: 300px;
            height: 300px;
            transform: translate(-50%, -50%);
        }

        .seek-bar > .circle > span {

            display: block;
            width: 30px;
            line-height: 130px;
            left: 150%;
            top:150%;
            transform: translate(-50%, -50%);
            background: #47cd65;
            border-radius: 100%;
            text-align: center;
        }

        #ex1 {
            background-color: #47cd65;
            border-radius: 150%
        }

        #ex1 {
            width: 200px;
            height: 200px;
            display: table-cell;
            vertical-align: middle;
            text-align: center;
        }

        *,
        *:before,
        *:after {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }


        output {
            display: block;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            margin: 30px 0;
            width: 100%;
        }

        .u-left {
            float: left;
        }

        .u-cf:before,
        .u-cf:after {
            content: "";
            display: table;
        }

        .u-cf:after {
            clear: both;
        }

        .u-text-left {
            text-align: left;
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
            /*overflow-y: auto;*/
            width: 850px;
            height: 600px;
            position: relative;
            top: -40px;
            padding: 10px;
            border-radius: 25px;
            background-color: whitesmoke;
            border: 1px black;

            box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);


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




        input {
            width: 100%;
            height: 46px;
            font-size: 16px;
            color: #6f6f6f;
            padding-left: 15px;
            border: 1px solid #e1e1e1;
            border-radius: 20px;


        }

        button {

            font-size: 16px;
            color: #6f6f6f;
            padding-left: 15px;
            border: 10px solid #e1e1e1;


        }




    </style>

</head>
<body>




<div style="text-align: center">

    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">

                <h3>동네 설정</h3>

                <div style="text-align: right">
                    <a href="/product/list"><span class="icon_close"></span></a>
                </div>

        <br/>






    <form:form action="/town/townresult" method="get">

     <input type="text" name="wishtown" value="" style="background-color: white "  placeholder="동네설정은 최대 3곳 가능"/>
    <input type="hidden" name="memberId" value="${memberDTO.id}"><br/><br/>
    <input type="submit" value="동네설정하기" style="background-color: #47cd65; color: white;"/><br/><br/>
    </form:form>

    <strong><현재 등록된 동네></strong><br/>
    <c:forEach var="list" items="${list}">

    <a href="#" onclick="statusChange(this)">
        <strong> ${list.townName}</strong><br/>


    </a>
    </c:forEach>
<br/>
    <strong> [동네예시]
        강남구/ 송파구/ 강동구

    </strong>
                <br/><br/>
                <c:if test="${empty range}">


                <form:form action="/product/wishRangeList" name="townadd" method="post">



                            <div id="js-example-disabled">
                       <input type="range" name="range" id="range" min="0" max="10" step="1" value="0" data-rangeslider
                        class="seek-bar"/>
                        </div>
                        <input type="hidden" name="townName" id="coordinateId" style="width: 15%;"/>

                        <input type="hidden" name="memberId" value="${memberDTO.id}">

                        <output> 거리설정</output>




                    <%--    <button type="submit">해당 범위 설정</button>--%>
                </form:form>

                </c:if>



                </div>
            </div>
        </div>
    </div>







    <script type="text/javascript">

        function statusChange(statusItem) {


            var townName = $(statusItem).text();
            var range = $("#range").val();


            townName = $.trim(townName);


            $("#coordinateId").val(townName);

            if (confirm(townName + " 전방 " + range + "km 이내의 주변 상품을 보시겠습니까?") == true) {    //확인

                document.townadd.submit();

            } else {   //취소

                return false;

            }


        }


    </script>




<%--    <button type="submit">해당 범위내 상품 찾기</button>--%>





    <script src="//localhost:8083"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="../dist/rangeslider.js"></script>
    <script>
        $(function () {

            var $document = $(document);
            var selector = '[data-rangeslider]';
            var $element = $(selector);


            var textContent = ('textContent' in document) ? 'textContent' : 'innerText';


            function valueOutput(element) {
                var value = element.value + "km";
                var output = element.parentNode.getElementsByTagName('output')[0] || element.parentNode.parentNode.getElementsByTagName('output')[0];
                output[textContent] = value;
            }

            $document.on('input', 'input[type="range"], ' + selector, function (e) {
                valueOutput(e.target);
            });


            $document.on('click', '#js-example-disabled button[data-behaviour="toggle"]', function (e) {
                var $inputRange = $(selector, e.target.parentNode);

                if ($inputRange[0].disabled) {
                    $inputRange.prop("disabled", false);
                } else {
                    $inputRange.prop("disabled", true);
                }
                $inputRange.rangeslider('update');
            });


            $document.on('click', '#js-example-change-value button', function (e) {
                var $inputRange = $(selector, e.target.parentNode);
                var value = $('input[type="number"]', e.target.parentNode)[0].value + "km";

                $inputRange.val(value).change();
            });


            $document.on('click', '#js-example-change-attributes button', function (e) {
                var $inputRange = $(selector, e.target.parentNode);
                var attributes = {
                    min: $('input[name="min"]', e.target.parentNode)[0].value,
                    max: $('input[name="max"]', e.target.parentNode)[0].value,
                    step: $('input[name="step"]', e.target.parentNode)[0].value
                };

                $inputRange.attr(attributes);
                $inputRange.rangeslider('update', true);
            });


            $document
                .on('click', '#js-example-destroy button[data-behaviour="destroy"]', function (e) {
                    $(selector, e.target.parentNode).rangeslider('destroy');
                })
                .on('click', '#js-example-destroy button[data-behaviour="initialize"]', function (e) {
                    $(selector, e.target.parentNode).rangeslider({polyfill: false});
                });


            $document
                .on('click', '#js-example-hidden button[data-behaviour="toggle"]', function (e) {
                    var $container = $(e.target.previousElementSibling);
                    $container.toggle();
                });


            $element.rangeslider({


                polyfill: false,

                // Callback function
                onInit: function () {
                    valueOutput(this.$element[0]);
                },

                // Callback function
                onSlide: function (position, value) {
                    console.log('onSlide');
                    console.log('position: ' + position, 'value: ' + value);
                },

                // Callback function
                onSlideEnd: function (position, value) {
                    console.log('onSlideEnd');
                    console.log('position: ' + position, 'value: ' + value);
                }
            });

        });
    </script>

</body>
</html>