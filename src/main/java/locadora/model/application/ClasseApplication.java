package locadora.model.application;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import locadora.model.domain.Classe;
import locadora.utils.PersistenceUtil;

import java.util.List;

public class ClasseApplication {
    public ClasseApplication() {
    }

    public static int incluirClasse(String nome, double valor, int dataDev) {
        if (nome != null && !nome.equals("") || valor < 0) {
            Classe c = new Classe();
            c.setNome(nome);
            c.setValor(valor);
            c.setDataDevolucao(dataDev);

            Session s = PersistenceUtil.getSession();
            Transaction t = null;

            try {
                t = s.beginTransaction();
                s.save(c);
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

    public static int excluirClasse(int id) throws HibernateException {
        Object obj = ClasseApplication.buscarClassePorId(id);
        return GenericApplication.excluir(obj);
    }

    public static List listarClasse() throws HibernateException {
        return GenericApplication.listar(Classe.class);
    }

    public static Object buscarClassePorId(int id) throws HibernateException {
        return GenericApplication.buscarPorId(Classe.class, id);
    }

    public static int salvarClasse(String nome, double valor, int dataDev, int id) throws HibernateException {
        Object obj = ClasseApplication.buscarClassePorId(id);
        Classe c = (Classe)obj;

        c.setNome(nome);
        c.setValor(valor);
        c.setDataDevolucao(dataDev);

        return GenericApplication.salvar(c);
    }
}