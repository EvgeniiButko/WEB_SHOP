<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 17.02.2018
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <form name="UserForm" method="post" action="controller">
        <input type="hidden" name = "command" value="CardRedirect">
        <input type="submit" value="Create Card">
        <br/>
             ${succesfuly}
        <br/>
    </form>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="MyCard">
        <input type="submit" value="My Cards">
    </form>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="ChooseCard">
        <input type="submit" value="Choose Card">
    </form>

    <c:forEach items="${requestScope.products}" var="prod">
        <div>
            <hr/>
            <div>${prod.name}</div>
            <div>${prod.information}</div>
            <div>${prod.url}</div>
            <div>${prod.prize}$</div>
            <div>
                <form method="post" action="controller">
                   <input type="hidden" name="command" value="UserBuying">
                   <input type="hidden" name="id" value=${prod.id}>
                    <input type="hidden" name = "cardNumb" value=${requestScope.cardNumb}>
                   <input type="submit" value="Buy">
                </form>
            </div>
            <hr/>
        </div>
    </c:forEach>

    <a href="controller?command=Logout">Logout</a>
</body>
</html>
