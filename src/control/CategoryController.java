package control;

import model.BeanCategory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class CategoryController {
    public List<BeanCategory> loadAll() throws BaseException{
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

    public static void main(String[] args) {
        try {
            CategoryController categoryController = new CategoryController();
            for(BeanCategory b: categoryController.loadAll()){
                System.out.println(b.getCateName());
            }
        }catch (BaseException e){
            e.printStackTrace();
        }
    }
}
