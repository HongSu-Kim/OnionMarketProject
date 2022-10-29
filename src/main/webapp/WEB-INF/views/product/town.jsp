<%@ page import="javax.validation.constraints.NotEmpty" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var = "cp" value = "<%=request.getContextPath()%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게 시 판</title>
<style type="text/css">

    p {
        color: #1c1c1c;
        font-weight: 700;
        border-bottom: 1px solid #e1e1e1;
        padding-bottom: 20px;
        margin-bottom: 25px;
    }
    .seek-bar{margin:50px auto;position:relative;width:calc(100% - 100px);height:10px;background:#222;border-radius:5px;}
    .seek-bar>.circle{cursor:grab;position:absolute;left:0;top:50%;width:30px;height:30px;transform:translate(-50%,-50%);}
    .seek-bar>.circle>span{position:absolute;display:block;width:30px;line-height:30px;left:50%;top:50%;transform:translate(-50%,-50%);background:#2196f3;border-radius:100%;text-align:center;}

    #ex1 {
        background-color : #ebebeb;
        border-radius: 50%
    }

    #ex1 {
        width: 200px;
        height: 200px;
        display: table-cell;
        vertical-align: middle;
        text-align:center;
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
        font-size: 30px;
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


</style>

</head>
<body>

<h4>동네 설정</h4>


    <div style="text-align: center">

    <p></p>

    <strong>[동네설정은 최대 3곳 가능]</동네설정은> </strong>
    <br/>

<%--    <button type="submit"  class="btn btnEvent" name="" value=" ">--%>
<%--        <img src="https://cdn-icons-png.flaticon.com/512/5735/5735394.png" alt="btnImages" class="btnImages"--%>
<%--             height="23" width="23" border="0" align="left">--%>
<%--    </button>--%>

<form:form action="/town/townresult" method="get">

원하는 동네 검색: <input type="text" name="wishtown" value="" />
    <input type="hidden" name="memberId" value="${memberDTO.id}">
   <input type="submit" value="동네설정하기"/><br/><br/>
</form:form>

        <strong>현재 등록된 동네</strong><br/>
        <c:forEach var="list" items="${list}">

        <a href="#" onclick="statusChange(this)">
            동네이름:  <strong> [${list.townName}]</strong><br/></a>
<%--            위도: <strong> [${list.latitude}]</strong><br/>--%>
<%--            경도: <strong> [${list.longitude}]</strong> <br/> </a>--%>

        </c:forEach>
<br/>
        <strong> [동네예시]<br/>
       강남구/  송파구/ 강동구
<%--        <a href="/town/townresult?wishtown=1 ">강남구</a>/<a href="/town/townresult?wishtown=2 ">송파구</a>--%>
<%--        <a href="/town/townresult?wishtown=3 ">/강동구</a>--%>
    </strong>

<br/><br/>
        <c:if test="${empty range}">

        <h2>거리설정</h2>


        <script type="text/javascript">

            function statusChange(statusItem) {


                var townName = $(statusItem).text();
                var range = $("#range").val();


                townName = $.trim(townName);
                 $("#coordinateId").val(townName);
                $("#latitude").val(townName);
                $("#longitude").val(townName);

                if (confirm( townName + "전방"+range+ "km 이내의 주변 상품을 보시겠습니까?") == true) {    //확인

                    document.townadd.submit();

                } else {   //취소

                    return false;

                }


            }


        </script>


            <form:form action="/town/rangeProduct" name="townadd" method="post">
        <div id="js-example-disabled">

            <input type="range" name="range" id="range" min="0" max="10" step="2" value="0" data-rangeslider/>
            <c:forEach var="list" items="${list}">
            <input type="hidden" name="townName" id="coordinateId" value="${list.townName}" style="width: 15%;">
            <input type="hidden" name="latitude" id="latitude" value="${list.latitude}" style="width: 15%;">
            <input type="hidden" name="longitude" id="longitude" value="${list.longitude}" style="width: 15%;">
            <output></output>
            </c:forEach>

        </div>

        <button type="submit">해당 범위 설정</button>
        </form:form>

        </c:if>


        <c:if test="${!empty range}">
        <c:forEach var="range" items="${range}">

        <h2>거리설정</h2>

       전방 ${range}km 주변 물건 찾기
        <form:form action="/town/rangeProduct" method="post">
        <div id="js-example-disabled">


            <input type="range" name="range" min="0" max="10" step="2" value="${range}" data-rangeslider  />
            <output></output>

        </div>

        <button type="submit">해당 범위내 상품 찾기</button>



        </form:form>

        </c:forEach>
        </c:if>

        <script src="//localhost:8083"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="../dist/rangeslider.js"></script>
        <script>
            $(function() {

                var $document = $(document);
                var selector = '[data-rangeslider]';
                var $element = $(selector);

                // For ie8 support
                var textContent = ('textContent' in document) ? 'textContent' : 'innerText';

                // Example functionality to demonstrate a value feedback
                function valueOutput(element) {
                    var value = element.value+"km";
                    var output = element.parentNode.getElementsByTagName('output')[0] || element.parentNode.parentNode.getElementsByTagName('output')[0];
                    output[textContent] = value;
                }

                $document.on('input', 'input[type="range"], ' + selector, function(e) {
                    valueOutput(e.target);
                });

                // Example functionality to demonstrate disabled functionality
                $document .on('click', '#js-example-disabled button[data-behaviour="toggle"]', function(e) {
                    var $inputRange = $(selector, e.target.parentNode);

                    if ($inputRange[0].disabled) {
                        $inputRange.prop("disabled", false);
                    }
                    else {
                        $inputRange.prop("disabled", true);
                    }
                    $inputRange.rangeslider('update');
                });

                // Example functionality to demonstrate programmatic value changes
                $document.on('click', '#js-example-change-value button', function(e) {
                    var $inputRange = $(selector, e.target.parentNode);
                    var value = $('input[type="number"]', e.target.parentNode)[0].value +"km";

                    $inputRange.val(value).change();
                });

                // Example functionality to demonstrate programmatic attribute changes
                $document.on('click', '#js-example-change-attributes button', function(e) {
                    var $inputRange = $(selector, e.target.parentNode);
                    var attributes = {
                        min: $('input[name="min"]', e.target.parentNode)[0].value,
                        max: $('input[name="max"]', e.target.parentNode)[0].value,
                        step: $('input[name="step"]', e.target.parentNode)[0].value
                    };

                    $inputRange.attr(attributes);
                    $inputRange.rangeslider('update', true);
                });

                // Example functionality to demonstrate destroy functionality
                $document
                    .on('click', '#js-example-destroy button[data-behaviour="destroy"]', function(e) {
                        $(selector, e.target.parentNode).rangeslider('destroy');
                    })
                    .on('click', '#js-example-destroy button[data-behaviour="initialize"]', function(e) {
                        $(selector, e.target.parentNode).rangeslider({ polyfill: false });
                    });

                // Example functionality to test initialisation on hidden elements
                $document
                    .on('click', '#js-example-hidden button[data-behaviour="toggle"]', function(e) {
                        var $container = $(e.target.previousElementSibling);
                        $container.toggle();
                    });

                // Basic rangeslider initialization
                $element.rangeslider({

                    // Deactivate the feature detection
                    polyfill: false,

                    // Callback function
                    onInit: function() {
                        valueOutput(this.$element[0]);
                    },

                    // Callback function
                    onSlide: function(position, value) {
                        console.log('onSlide');
                        console.log('position: ' + position, 'value: ' + value);
                    },

                    // Callback function
                    onSlideEnd: function(position, value) {
                        console.log('onSlideEnd');
                        console.log('position: ' + position, 'value: ' + value);
                    }
                });

            });
        </script>

</body>
</html>