package locadora.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import locadora.model.application.AtorApplication;


@WebServlet("/AtorController")
public class AtorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AtorController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        String nome;
        String id;

        switch (tipo){
            case "1":
                System.out.println("Inserir");
                nome = request.getParameter("txt_nome");

                if (AtorApplication.incluirAtor(nome) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Ator Cadastrado com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao incluir Ator.'");
                }
                break;
            case "2":
                System.out.println("Excluir");

                id = request.getParameter("id").trim();

                if (AtorApplication.excluirAtor(Integer.parseInt(id)) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Ator Excluido com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao Excluido Ator.'");
                }
                break;
            case "3":
                System.out.println("Salvar Edicao");
                nome = request.getParameter("txt_nome");
                id = request.getParameter("id").trim();

                if (AtorApplication.salvarAtor(nome, Integer.parseInt(id)) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Ator Alterado com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao Alterado Ator.'");
                }
                break;
            default:
                System.out.println("Err");
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
