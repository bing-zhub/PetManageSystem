package control;

import model.BeanProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static util.HibernateUtil.getSession;

public class ProductController {
    public List<BeanProduct> loadAll() {
        List<BeanProduct> list = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanProduct ");
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public BeanProduct findProductById(int id) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanProduct b where b.prodId = :id");
        query.setParameter("id", id);
        BeanProduct beanProduct = (BeanProduct) query.list().get(0);
        tx.commit();
        session.close();
        return beanProduct;
    }
}
