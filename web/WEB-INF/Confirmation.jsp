<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 27.03.2018
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="confirmationpassword">
        <h3>Enter the code from your mail</h3>
        <input type="text" name="confirm">
        <input type="submit" value="submit">
        <br/>
            ${wrongInput}
    </form>
</body>
</html>
