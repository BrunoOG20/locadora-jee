package locadora.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import locadora.model.application.DiretorApplication;
import locadora.model.domain.Diretor;

import java.io.IOException;

@WebServlet("/DiretorController")
public class DiretorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DiretorController() {
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

                if (DiretorApplication.incluirDiretor(nome) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Diretor Cadastrado com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao incluir Diretor.'");
                }
                break;
            case "2":
                System.out.println("Excluir");

                id = request.getParameter("id").trim();

                if (DiretorApplication.excluirDiretor(Integer.parseInt(id)) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Diretor Excluido com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao Excluido Diretor.'");
                }
                break;
            case "3":
                System.out.println("Salvar Edicao");
                nome = request.getParameter("txt_nome");
                id = request.getParameter("id").trim();

                if (DiretorApplication.salvarDiretor(nome, Integer.parseInt(id)) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Diretor Alterado com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao Alterado Diretor.'");
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
