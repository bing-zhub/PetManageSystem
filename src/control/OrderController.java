package control;

import model.BeanMyOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

import java.awt.*;
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

    public void delOrder(int id) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanMyOrder b where b.orderId = :id");
        query.setParameter("id",id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public BeanMyOrder findOrderById(int id) {
        BeanMyOrder order = new BeanMyOrder();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanMyOrder b where b.orderId = :id");
        query.setParameter("id", id);
        order = (BeanMyOrder) query.list().get(0);
        tx.commit();
        session.close();
        return  order;
    }
}
