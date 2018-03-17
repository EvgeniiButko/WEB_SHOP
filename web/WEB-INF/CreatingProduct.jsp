<%--
  Created by IntelliJ IDEA.
  User: Evgen
  Date: 19.02.2018
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
     <form name="CreatingPage" action="controller" method="post">
         <input type="hidden" name="command" value="AddProduct">
         <table border="1">
             <tr>
                 <td>Name</td>
                 <td>
                     <input type="text" name="name">
                 </td>
             </tr><tr>
                 <td>Information</td>
                 <td><input type="text" name="information"></td>
             </tr><tr>
                 <td>Url</td>
                 <td><input type="text" name="url"></td>
             </tr><tr>
                 <td>Prize</td>
                 <td>
                     <input type="text" name="prize">
                 </td>
             </tr>
             <tr>
                 <td><input type="submit"/></td>
             </tr>
         </table>
     </form>
</body>
</html>
