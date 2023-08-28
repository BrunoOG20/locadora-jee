package locadora.model.application;

import jakarta.persistence.criteria.CriteriaQuery;
import locadora.model.domain.Ator;
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
        Session sessao = null;
        Object obj = ClasseApplication.buscarClassePorId(id);

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

    public static List listarClasse() throws HibernateException {
        Session sessao = null;
        List lista = null;

        try {
            sessao = PersistenceUtil.getSession();
            sessao.beginTransaction();

            CriteriaQuery consulta = sessao.getCriteriaBuilder().createQuery(Classe.class);

            consulta.from(Classe.class);
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

    public static Classe buscarClassePorId(int id) throws HibernateException {
        Session sessao = null;
        Classe objReturn = null;

        try   {
            sessao = PersistenceUtil.getSession();
            sessao.getTransaction().begin();

            objReturn = sessao.get(Classe.class, id);

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

    public static int salvarClasse(String nome, double valor, int dataDev, int id) throws HibernateException {
        Session sessao = null;

        Classe obj = ClasseApplication.buscarClassePorId(id);

        if (obj == null){
            return 2;
        }

        obj.setNome(nome);
        obj.setValor(valor);
        obj.setDataDevolucao(dataDev);

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