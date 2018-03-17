<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 18.02.2018
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cards</title>
</head>
<body>
   <table border="1">
     <c:forEach items="${cards}" var="card">
         <tr>
             <td>${card.cardNumb}</td>
             <td>${card.money}</td>
         </tr>
     </c:forEach>
   </table>
</body>
</html>
