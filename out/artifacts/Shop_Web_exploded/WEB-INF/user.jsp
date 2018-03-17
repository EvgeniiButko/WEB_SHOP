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
</body>
</html>
