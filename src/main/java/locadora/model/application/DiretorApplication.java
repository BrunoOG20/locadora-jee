package locadora.model.application;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import locadora.model.domain.Diretor;
import locadora.utils.PersistenceUtil;

import java.util.List;

public class DiretorApplication {
    public DiretorApplication() {
    }

    public static int incluirDiretor(String nome) {
        if (nome != null && !nome.equals("")) {
            Diretor d = new Diretor();
            d.setNome(nome);
            Session s = PersistenceUtil.getSession();
            Transaction t = null;

            try {
                t = s.beginTransaction();
                s.save(d);
                t.commit();
                return 0;
            } catch (Exception var5) {
                t.rollback();
                System.out.println(var5.getMessage());
                var5.printStackTrace();
                return 2;
            }
        } else {
            return 1;
        }
    }
    public static int excluirDiretor(int id) throws HibernateException {
        Object obj = DiretorApplication.buscarDiretorPorId(id);
        return GenericApplication.excluir(obj);
    }

    public static List listarDiretor() throws HibernateException {
        return GenericApplication.listar(Diretor.class);
    }

    public static Object buscarDiretorPorId(int id) throws HibernateException {
        return GenericApplication.buscarPorId(Diretor.class, id);
    }

    public static int salvarDiretor(String nome, int id) throws HibernateException {
        Object obj = DiretorApplication.buscarDiretorPorId(id);
        Diretor d = (Diretor)obj;
        d.setNome(nome);
        return GenericApplication.salvar(d);
    }
}