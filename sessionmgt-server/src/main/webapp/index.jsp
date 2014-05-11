<%@ page import="com.quanix.app.integration.remote.PermissionContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
      Hello Jack

   <%
       PermissionContext context = new PermissionContext();
       out.println(context.toString());
   %>
</body>
</html>
