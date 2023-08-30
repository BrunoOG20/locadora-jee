package locadora.model.application;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import locadora.model.domain.Ator;
import locadora.utils.PersistenceUtil;

import java.util.List;

public class AtorApplication {
    public AtorApplication() {
    }

    public static int incluirAtor(String nome) {
        if (nome != null && !nome.equals("")) {
            Ator a = new Ator();
            a.setNome(nome);
            Session s = PersistenceUtil.getSession();
            Transaction t = null;

            try {
                t = s.beginTransaction();
                s.save(a);
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

    public static int excluirAtor(int id) throws HibernateException {
        Object obj = AtorApplication.buscarAtorPorId(id);
        return GenericApplication.excluir(obj);
    }

    public static List listarAtor() throws HibernateException {
        return GenericApplication.listar(Ator.class);
    }

    public static Object buscarAtorPorId(int id) throws HibernateException {
        return GenericApplication.buscarPorId(Ator.class, id);
    }

    public static int salvarAtor(String nome, int id) throws HibernateException {
        Object obj = AtorApplication.buscarAtorPorId(id);
        Ator a = (Ator)obj;
        a.setNome(nome);
        return GenericApplication.salvar(a);
    }

}
