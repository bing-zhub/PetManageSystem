package control;

import model.BeanMyOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

import java.util.List;

import static util.HibernateUtil.getSession;

public class OrderController {
    public List<BeanMyOrder> loadAll(){
        List<BeanMyOrder> lsit = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanMyOrder ");
        lsit = query.list();
        tx.commit();
        session.close();
        return lsit;
    }
}
