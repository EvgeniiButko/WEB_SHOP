<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 27.03.2018
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ForgotPass</title>
</head>
<body>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="forgotpass">
        <h3>Enter your login</h3>
        <input type="text" name="timelogin">
        <input type="submit" name="Submit">
    </form>
</body>
</html>
