package locadora.model.application;

import jakarta.persistence.criteria.CriteriaQuery;
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
        Session sessao = null;
        Object obj = DiretorApplication.buscarDiretorPorId(id);

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

    public static List listarDiretor() throws HibernateException {
        Session sessao = null;
        List lista = null;

        try {
            sessao = PersistenceUtil.getSession();
            sessao.beginTransaction();

            CriteriaQuery consulta = sessao.getCriteriaBuilder().createQuery(Diretor.class);

            consulta.from(Diretor.class);
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

    public static Diretor buscarDiretorPorId(int id) throws HibernateException {
        Session sessao = null;
        Diretor objReturn = null;

        try   {
            sessao = PersistenceUtil.getSession();
            sessao.getTransaction().begin();

            objReturn = sessao.get(Diretor.class, id);

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

    public static int salvarDiretor(String nome, int id) throws HibernateException {
        Session sessao = null;

        Diretor obj = DiretorApplication.buscarDiretorPorId(id);

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