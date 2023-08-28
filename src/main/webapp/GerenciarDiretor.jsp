<%@ page import="java.util.List" %>
<%@ page import="locadora.model.application.DiretorApplication" %>
<%@ page import="locadora.model.domain.Diretor" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gerenciar Diretor</title>
    <link rel="stylesheet" href="styles/gerenciar.css">
</head>
<body>
<nav class="navbar">
    <div class="navbar-title">Diretor</div>
</nav>
<div class="content">
    <div class="form">
        <form method="get" action="DiretorController">
            <input type="hidden" name="tipo" value="1">
            <input type="text" name="txt_nome" placeholder="Nome do Diretor" required/>
            <button id="enviar" type="submit">Enviar</button>
            <a href="index.jsp"><button type="button">Voltar</button></a>
        </form>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nome do Diretor</th>
            <th>Ações</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <%
            List<Diretor> diretores = DiretorApplication.listarDiretor();
            for (Diretor diretor : diretores) {
        %>
        <tr>
            <td><%= diretor.getId() %></td>
            <td><%= diretor.getNome() %></td>
            <td><a href="<%= request.getContextPath() %>/EditarDiretor.jsp?id=<%= diretor.getId() %>"><button class="edit-button">Editar</button></a></td>
            <td><a href="<%= request.getContextPath() %>/DiretorController?tipo=2&id=<%= diretor.getId() %>"><button class="delete-button">Remover</button></a></td>
        </tr>
        <%
            }
        %>

    </table>
</div>
</body>
</html>