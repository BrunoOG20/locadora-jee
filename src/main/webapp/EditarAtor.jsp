<%@ page import="locadora.model.domain.Ator" %>
<%@ page import="java.util.List" %>
<%@ page import="locadora.model.application.AtorApplication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Ator</title>
    <link rel="stylesheet" href="styles/gerenciar.css">
</head>
<body>
<nav class="navbar">
    <div class="navbar-title">Editar Ator</div>
</nav>
<div class="content">
    <div class="form">
        <%
            String id = request.getParameter("id");
            Ator a = AtorApplication.buscarAtorPorId(Integer.parseInt(id));
        %>
        <form method="get" action="AtorController">
            <input type="hidden" name="tipo" value="3">
            <input type="hidden" name="id" value="<%= a.getId() %>">
            <input type="text" name="txt_nome"  value="<%= a.getNome() %>"/>
            <button id="enviar" type="submit">Salvar</button>
        </form>
    </div>
</div>
</body>
</html>
