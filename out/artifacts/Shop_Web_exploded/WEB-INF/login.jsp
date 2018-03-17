<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 14.02.2018
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
      <form name="LoginForm" method="post" action="controller">
          <input type="hidden" name="command" value="Login">
          Login:<br/>
          <input type="text" name="Login" value=""/>
          <br/>Password:<br/>
          <input type="password" name = "password" value=""/>
                  <br/>
                              ${errorLoginPassMessage}
                  <br/>
                              ${wrongAction}
                  <br/>
                              ${nullPage}
                  <br/>
          <input type = "submit" value="Log in"/>
      </form>
      <form name="LoginForm" method="post" action="controller">
          <input type="hidden" name="command" value="REGISTERREDIRECT"/>
          <input type = "submit" value="Register"/>
      </form>
</body>
</html>
