<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/15
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MD5</title>
  </head>
  <body>
  <h1>MD5</h1>
  <form action="md5" method="post">
    <input type="text" name="encryptedPassword" size="64">
    <input type="submit" value="解密">
  </form>
  ${requestScope.plainPassword}
  ${requestScope.message}
  </body>
</html>
