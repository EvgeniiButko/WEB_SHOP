<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 17.03.2018
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
  <table border="1">
      <tr>
          <td>ID</td>
          <td>PHONE</td>
          <td>USER LOGIN</td>
      </tr>
      <c:forEach items = "${requestScope.orders}" var="order">
          <tr>
              <td>${order.productid}</td>
              <td>${order.phone}</td>
              <td>${order.userName}</td>
              <td>
                  <form method="post" action="controller">
                      <input type="hidden" name="command" value="deleteOrder">
                      <input type="hidden" name="ID" value="${order.id}">
                      <input type="submit" value="Delete">
                  </form>
              </td>
          </tr>
      </c:forEach>
  </table>
</body>
</html>
