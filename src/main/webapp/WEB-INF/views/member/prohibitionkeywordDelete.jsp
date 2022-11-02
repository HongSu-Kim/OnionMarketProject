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
            /*overflow-y: auto;*/

            width: 800px;
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

        button {

            font-size: 16px;
            color: #6f6f6f;
            padding-left: 15px;
            border: 10px solid #e1e1e1;


        }
    </style>

</head>
<body>



<form:form action="" method="post">
<div id="modal" class="modal-overlay">
    <div class="modal-window">
        <div class="title">
            <div style="text-align: right">

                <a href="/prohibitionkeyword/prohibitionkeyword">뒤로가기</a>
            </div>
            <div style="text-align: center">


                <h3>금지 키워드 설정</h3></div>
            <br/><br/>
        </div>
        <input type="text" name="prohibitionKeywordName"/>
        <br/>  <br/>

        <input type="submit" value="금지 키워드 삭제" style="background-color: #47cd65; color: white"/>
        </form:form>

<br/>
        <div style="text-align: center" >
        [금지키워드 리스트]


            <c:forEach var="prohibitionKeywordFindDTOList" items="${prohibitionKeywordFindDTOList}">
               <br/>
              ${prohibitionKeywordFindDTOList.prohibitionKeywordName}
                </c:forEach>
        </div>


    </div> </div> </div>


</body>
</html>