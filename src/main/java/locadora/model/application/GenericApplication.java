package locadora.model.application;

import locadora.utils.PersistenceUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Transaction;

import java.util.List;

public class GenericApplication {

    public static int excluir(Object obj) throws HibernateException {
        Session sessao = null;
        Transaction t = null;

        try {
            sessao = PersistenceUtil.getSession();
            t = sessao.beginTransaction();

            sessao.delete(obj);

            t.commit();
            return 0;
        } catch( HibernateException erro) {
            t.rollback();
            erro.printStackTrace();
            return 1;
        }
    }

    public static List listar(Class classe) throws HibernateException {
        Session sessao = null;
        List lista = null;
        Transaction t = null;

        try {
            sessao = PersistenceUtil.getSession();
            t = sessao.beginTransaction();

            CriteriaQuery consulta = sessao.getCriteriaBuilder().createQuery(classe);
            consulta.from(classe);
            lista = sessao.createQuery(consulta).getResultList();

            t.commit();
        } catch( HibernateException erro) {
            if ( sessao != null ){
                t.rollback();
            }
        }
        return lista;
    }

    public static Object buscarPorId(Class classe, int id) throws HibernateException {
        Session sessao = null;
        Object objReturn = null;
        Transaction t = null;

        try {
            sessao = PersistenceUtil.getSession();
            t = sessao.beginTransaction();

            objReturn = sessao.get(classe, id );

            t.commit();
        } catch ( HibernateException ex) {
            if ( sessao != null) {
                t.rollback();
            }
            throw new HibernateException(ex);
        }
        return objReturn;

    }

    public static int salvar(Object obj) throws HibernateException {
        Session sessao = null;
        Transaction t = null;

        try {
            sessao = PersistenceUtil.getSession();
            t = sessao.beginTransaction();

            sessao.update(obj);

            t.commit();
            return 0;
        } catch( HibernateException erro) {
            t.rollback();
            erro.printStackTrace();
            return 1;
        }
    }



}
