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


</head>
<body>


        </div>
    </div>
</nav>
<br/><br/><br/><br/>



      <strong><금지키워드 리스트> </strong>



            <form:form action="" method="post">
               <br/>
                <select name="updatekeyword">
                    <c:forEach var="prohibitionKeywordFindDTOList" items="${prohibitionKeywordFindDTOList}">
                    <option selected value="${prohibitionKeywordFindDTOList.prohibitionKeywordName}">
                            ${prohibitionKeywordFindDTOList.prohibitionKeywordName}
                        </option>



                </c:forEach>


                </select>

                <input type="text" name="prohibitionKeywordName"/>
                <input type="submit" value="금지 키워드 수정"/>

                </form:form>
<br/><br/>


</body>
</html>