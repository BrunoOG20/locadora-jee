package locadora.model.application;

import locadora.utils.PersistenceUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class GenericApplication {

    public static int excluir(Object obj) throws HibernateException {
        Session sessao = null;

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

    public static List listar(Class classe) throws HibernateException {
        Session sessao = null;
        List lista = null;

        try {
            sessao = PersistenceUtil.getSession();
            sessao.beginTransaction();

            CriteriaQuery consulta = sessao.getCriteriaBuilder().createQuery(classe);
            consulta.from(classe);
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

    public static Object buscarPorId(Class classe, int id) throws HibernateException {
        Session sessao = null;
        Object objReturn = null;

        try {
            sessao = PersistenceUtil.getSession();
            sessao.getTransaction().begin();

            objReturn = sessao.get(classe, id );

            sessao.getTransaction().commit();
        } catch ( HibernateException ex) {
            if ( sessao != null) {
                sessao.getTransaction().rollback();
            }
            throw new HibernateException(ex);
        }
        return objReturn;

    }

    public static int salvar(Object obj) throws HibernateException {
        Session sessao = null;

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
