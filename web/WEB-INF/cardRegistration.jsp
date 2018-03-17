<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 17.02.2018
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CardRegistration</title>
</head>
<body>
    <form name="CardRegForm" method="post" action="controller">
       <input type = "hidden" name="command" value="add_card">
       Card Number:<br/>
       <input value="" type="text" name="cardNumb"/>
       <h3>
            ${errorLoginPassMessage}
       </h3>
        <input type="submit" value="Add">
    </form>
</body>
</html>
