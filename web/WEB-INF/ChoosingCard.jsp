<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 17.03.2018
  Time: 13:19
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
                   <td>
                       <form method="post" action="controller">
                         <input type="hidden" name="command" value="RedirectingCardNumb">
                         <input type="hidden" name="cardNumb" value=${card.cardNumb}>
                         <input type="submit" value="Choose">
                       </form>
                   </td>
               </tr>
           </c:forEach>
       </table>
</body>
</html>
