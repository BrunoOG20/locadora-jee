<%@ page import="locadora.model.domain.Diretor" %>
<%@ page import="java.util.List" %>
<%@ page import="locadora.model.application.DiretorApplication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Diretor</title>
    <link rel="stylesheet" href="styles/gerenciar.css">
</head>
<body>
<nav class="navbar">
    <div class="navbar-title">Editar Diretor</div>
</nav>
<div class="content">
    <div class="form">
        <%
            String id = request.getParameter("id");
            Diretor d = DiretorApplication.buscarDiretorPorId(Integer.parseInt(id));
        %>
        <form method="get" action="DiretorController">
            <input type="hidden" name="tipo" value="3">
            <input type="hidden" name="id" value="<%= d.getId() %>">
            <input type="text" name="txt_nome"  value="<%= d.getNome() %>"/>
            <button id="enviar" type="submit">Salvar</button>
        </form>
    </div>
</div>
</body>
</html>
