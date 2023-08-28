<%@ page import="locadora.model.application.ClasseApplication" %>
<%@ page import="locadora.model.domain.Classe" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/gerenciar.css">
</head>
<body>
<nav class="navbar">
    <div class="navbar-title">Editar Classe</div>
</nav>
<div class="content">
    <div class="form">
        <%
            String id = request.getParameter("id");
            Classe c = ClasseApplication.buscarClassePorId(Integer.parseInt(id));
        %>
        <form method="get" action="ClasseController">
            <input type="hidden" name="tipo" value="3">
            <input type="hidden" name="id" value="<%= c.getId() %>">
            <input type="text" name="txt_nome" value="<%= c.getNome() %>"/>
            <input type="text" name="txt_valor"  value="<%= c.getValor() %>"/>
            <input type="text" name="txt_dataDev"  value="<%= c.getDataDevolucao()%>"/>
            <button id="enviar" type="submit">Salvar</button>
        </form>
    </div>
</div>
</body>
</html>
