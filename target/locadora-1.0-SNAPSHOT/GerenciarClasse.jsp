<%@ page import="java.util.List" %>
<%@ page import="locadora.model.application.ClasseApplication" %>
<%@ page import="locadora.model.domain.Classe" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Gerenciar Classe</title>
    <link rel="stylesheet" href="styles/gerenciar.css">
</head>
<body>
<nav class="navbar">
    <div class="navbar-title">Classe</div>
</nav>
<div class="content">
    <div class="form">
        <form method="get" action="ClasseController">
            <input type="hidden" name="tipo" value="1">
            <input type="text" name="txt_nome" placeholder="Nome da Classe" required/>
            <input type="number" min="0" name="txt_valor" placeholder="Valor" required/>
            <input type="number" min="0" name="txt_dataDev" placeholder="Data de Devolução" required/>

            <button id="enviar" type="submit">Enviar</button>
            <a href="index.jsp"><button type="button">Voltar</button></a>
        </form>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome da Classe</th>
            <th>Valor</th>
            <th>Data Devolucao</th>
            <th>Ações</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <%
            List<Classe> classes = ClasseApplication.listarClasse();
            for (Classe classe : classes) {
        %>
        <tr>
            <td><%= classe.getId() %></td>
            <td><%= classe.getNome() %></td>
            <td><%= classe.getValor() %></td>
            <td><%= classe.getDataDevolucao() %></td>
            <td><a href="<%= request.getContextPath() %>/EditarClasse.jsp?id=<%= classe.getId() %>"><button class="edit-button">Editar</button></a></td>
            <td><a href="<%= request.getContextPath() %>/ClasseController?tipo=2&id=<%= classe.getId() %>"><button onclick="return confirm('Deseja excluir o Ator <%= classe.getNome()%>? ')" class="delete-button">Remover</button></a></td>
        </tr>
        <%
            }
        %>

    </table>
</div>
</body>
</html>