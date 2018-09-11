package control;

import model.BeanCategory;
import model.BeanProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
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

    public void delProduct(Integer prodId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanProduct b where b.prodId = :id");
        query.setParameter("id",prodId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public static void main(String[] args) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanProduct b where b.prodId = 1");
        BeanCategory category = ((BeanProduct) query.list().get(0)).getProdCategory();
        tx.commit();
        session.close();
        System.out.println(category.getCateName());
    }

    public List<BeanProduct> search(String text) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BeanProduct b where b.prodName like :text");
        query.setParameter("text","%"+text+"%");
        List<BeanProduct> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
