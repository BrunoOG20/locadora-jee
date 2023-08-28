package locadora;



import org.hibernate.Session;
import org.hibernate.Transaction;

import locadora.utils.PersistenceUtil;

public class TesteConexao {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        Session session = PersistenceUtil.getSession();

        Transaction t = session.beginTransaction();

        t.commit();

        session.close();


    }

}