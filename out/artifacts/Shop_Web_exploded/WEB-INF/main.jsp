<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Welcome</title>
</head>
<a>
      <h3>Welcome</h3>
      <hr/>
               ${user}, hello!
      <hr/>

      <c:forEach items="${requestScope.products}" var="prod">
      <div>
          <hr/>
          <div>${prod.name}</div>
          <div>${prod.information}</div>
          <div>${prod.url}</div>
          <div>${prod.prize}</div>
          <hr/>
      </div>
      </c:forEach>


      <a href="controller?command=PRODUCTREDIRECT">New Product</a>

      <a href="controller?command=Logout">Logout</a>
</body>
</html>
