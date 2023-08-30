<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    String m = request.getParameter("msg");

    out.println("<b>"+m+"</b>");
%>
<button><a href="index.jsp">Menu</a></button>
</body>
</html>
