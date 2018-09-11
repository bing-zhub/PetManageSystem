package control;

import model.BeanService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class ServiceController {
    public List<BeanService> loadAll(){
        List<BeanService> list = new ArrayList<BeanService>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanService ");
        list = query.list();
        tx.commit();
        session.close();
        return  list;
    }

    public BeanService findServiceByName(String name){
        BeanService beanService = new BeanService();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanService b where b.servName = :name");
        query.setParameter("name", name);
        beanService = (BeanService) query.list().get(0);
        tx.commit();
        session.close();
        return beanService;
    }

    public BeanService findServiceById(int id) {
        BeanService service = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanService b where b.servId = :id");
        query.setParameter("id",id);
        service = (BeanService) query.list().get(0);
        tx.commit();
        session.close();
        return service;
    }

    public void delService(Integer servId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanService b where b.servId = :id ");
        query.setParameter("id",servId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public int getServiceTotalCount(){
        return (int)getSession().createCriteria("BeanService")
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
