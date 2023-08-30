<%@ page import="java.util.List" %>
<%@ page import="locadora.model.application.AtorApplication" %>
<%@ page import="locadora.model.domain.Ator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gerenciar Ator</title>
    <link rel="stylesheet" href="styles/gerenciar.css">
</head>
<body>
<nav class="navbar">
    <div class="navbar-title">Ator</div>
    </nav>
    <div class="content">
        <div class="form">
            <form method="get" action="AtorController">
                <input type="hidden" name="tipo" value="1">
                <input type="text" name="txt_nome" placeholder="Nome do Ator" required/>
                <button id="enviar" type="submit">Enviar</button>
                <a href="index.jsp"><button type="button">Voltar</button></a>
            </form>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Nome do Ator</th>
                <th>Ações</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <%
                List<Ator> atores = AtorApplication.listarAtor();
                for (Ator ator : atores) {
            %>
            <tr>
                <td><%= ator.getId() %></td>
                <td><%= ator.getNome() %></td>
                <td><a href="<%= request.getContextPath() %>/EditarAtor.jsp?id=<%= ator.getId() %>"><button class="edit-button">Editar</button></a></td>
                <td><a href="<%= request.getContextPath() %>/AtorController?tipo=2&id=<%= ator.getId() %>"><button onclick="return confirm('Deseja excluir o Ator <%= ator.getNome()%>? ')" class="delete-button">Remover</button></a></td>
            </tr>
            <%
                }
            %>

        </table>
    </div>
</body>
</html>