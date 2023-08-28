package locadora.model.application;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import locadora.model.domain.Ator;
import locadora.utils.PersistenceUtil;
import jakarta.persistence.criteria.CriteriaQuery;

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
        Session sessao = null;
        Object obj = AtorApplication.buscarAtorPorId(id);

        try {
            sessao = PersistenceUtil.getSession();
            sessao.beginTransaction();

            sessao.delete(obj);

            sessao.getTransaction().commit();
            return 0;

        } catch( HibernateException erro) {
            sessao.getTransaction().rollback();
            erro.printStackTrace();
            return 1;
        }
    }

    public static List listarAtor() throws HibernateException {
        Session sessao = null;
        List lista = null;

        try {
            sessao = PersistenceUtil.getSession();
            sessao.beginTransaction();

            CriteriaQuery consulta = sessao.getCriteriaBuilder().createQuery(Ator.class);

            consulta.from(Ator.class);
            lista = sessao.createQuery(consulta).getResultList();

            sessao.getTransaction().commit();
        } catch( HibernateException erro) {
            if ( sessao != null ){
                sessao.getTransaction().rollback();
                sessao.close();
            }
        }
        return lista;
    }

    public static Ator buscarAtorPorId(int id) throws HibernateException {
        Session sessao = null;
        Ator objReturn = null;

        try   {
            sessao = PersistenceUtil.getSession();
            sessao.getTransaction().begin();

            objReturn = sessao.get(Ator.class, id);

            sessao.getTransaction().commit();
        } catch ( HibernateException ex) {
            if ( sessao != null) {
                sessao.getTransaction().rollback();
                sessao.close();
            }
            throw new HibernateException(ex);
        }
        return objReturn;

    }

    public static int salvarAtor(String nome, int id) throws HibernateException {
        Session sessao = null;

        Ator obj = AtorApplication.buscarAtorPorId(id);

        if (obj == null){
            return 2;
        }

        obj.setNome(nome);

        try {
            sessao = PersistenceUtil.getSession();
            sessao.beginTransaction();

            sessao.update(obj);

            sessao.getTransaction().commit();
            return 0;
        } catch( HibernateException erro) {
            sessao.getTransaction().rollback();
            erro.printStackTrace();
            return 1;
        }
    }

}
