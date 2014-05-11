<%@ page import="org.springframework.cglib.core.CollectionUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.springframework.util.IdGenerator" %>
<%@ page import="org.springframework.util.LinkedMultiValueMap" %>
<%@ page import="com.quanix.app.integration.core.ClientPrintOut" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
      Hello Jack

   <%
       CollectionUtils.getIndexMap(new ArrayList());

       LinkedMultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<String, String>();

       multiValueMap.add("ss","xxx");
       multiValueMap.add("ss","uy");

       out.println(multiValueMap.size()+"========="+ ClientPrintOut.printout());

   %>
</body>
</html>
