package control;

import model.BeanMyOrder;
import model.BeanOrderDetail;
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

    public void delOrder(int id) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanMyOrder b where b.orderId = :id");
        query.setParameter("id",id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public BeanMyOrder findOrderById(int id) throws BaseException{
        BeanMyOrder order = new BeanMyOrder();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanMyOrder b where b.orderId = :id");
        query.setParameter("id", id);
        if(query.list().size()==0){
            throw new BaseException("¶©µ¥²»´æÔÚ");
        }
        order = (BeanMyOrder) query.list().get(0);
        tx.commit();
        session.close();
        return  order;
    }

    public List<BeanOrderDetail> loadAllDetails() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOrderDetail");
        List<BeanOrderDetail> list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public List<BeanOrderDetail> loadDetailByOrderId(int orderId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOrderDetail b where b.order.orderId = :id");
        query.setParameter("id", orderId);
        List<BeanOrderDetail> list = query.list();
        tx.commit();
        session.close();
        return list;
    }
}
