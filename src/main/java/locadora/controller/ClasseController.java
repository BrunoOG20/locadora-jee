package locadora.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import locadora.model.application.AtorApplication;
import locadora.model.application.ClasseApplication;

import java.io.IOException;

@WebServlet("/ClasseController")
public class ClasseController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClasseController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");

        String valor, dataDevolucao, nome, id;

        switch (tipo){
            case "1":
                System.out.println("Inserir");
                nome = request.getParameter("txt_nome").trim();
                valor = request.getParameter("txt_valor").trim();
                dataDevolucao = request.getParameter("txt_dataDev").trim();

                if (ClasseApplication.incluirClasse(nome, Double.parseDouble(valor), Integer.parseInt(dataDevolucao)) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Classe Cadastrada com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao cadastrar Classe.'");
                }
                break;
            case "2":
                System.out.println("Excluir");

                id = request.getParameter("id").trim();

                if (ClasseApplication.excluirClasse(Integer.parseInt(id)) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Classe Excluida com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao Excluir Classe.'");
                }
                break;
            case "3":
                System.out.println("Salvar Edicao");
                nome = request.getParameter("txt_nome");
                valor = request.getParameter("txt_valor").trim();
                dataDevolucao = request.getParameter("txt_dataDev").trim();
                id = request.getParameter("id").trim();

                if (ClasseApplication.salvarClasse(nome, Double.parseDouble(valor) , Integer.parseInt(dataDevolucao), Integer.parseInt(id) ) == 0) {
                    response.sendRedirect("mensagem.jsp?msg='Classe Alterada com sucesso!!!'");
                } else {
                    response.sendRedirect("mensagem.jsp?msg='ERRO ao Alterar Classe.'");
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
