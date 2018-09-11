package control;

import model.BeanCategory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class CategoryController {
    public List<BeanCategory> loadAll() {
        List<BeanCategory> list = new ArrayList<BeanCategory>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanCategory";
        Query query = session.createQuery(hql);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public void add(BeanCategory cate) throws BaseException{
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanCategory b where b.cateName = :name");
        query.setParameter("name", cate.getCateName());
        if(query.list().size()!=0)
            throw new BaseException("该分类名已存在");
        else
            session.save(cate);
        tx.commit();
        session.close();
    }

    public void delCategory(int id){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanCategory b where b.cateId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public BeanCategory findCategoryById(int id) {
        BeanCategory category = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanCategory b where b.cateId = :id");
        query.setParameter("id", id);
        category = (BeanCategory) query.list().get(0);
        tx.commit();
        session.close();
        return category;
    }

    public int getCategoryTotalCount(){
        return (int)getSession().createCriteria("BeanCategory")
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }


    public List<BeanCategory> search(String text) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BeanCategory b where b.cateName like :text");
        query.setParameter("text","%"+text+"%");
        List<BeanCategory> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
