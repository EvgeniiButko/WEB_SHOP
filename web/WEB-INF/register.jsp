<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 15.02.2018
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form name = "RegisterForm" action="controller" method="post">
         <input type="hidden" name="command" value="Register"/>
         Your Login:<br/>
         <input type = "text" name="login" value=""><br/>
         Your Password:<br/>
         <input type = "text" name="password" value=""><br/>
         Your Mail:<br/>
         <input type="text" name="mail" value=""><br/>
         <br/>
               ${errorLoginPassMessage}
         <br/>
         <input type="submit" name="Register"/>
    </form>
</body>
</html>
