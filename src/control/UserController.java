package control;

import com.sun.xml.internal.rngom.parse.host.Base;
import model.BeanMyUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class UserController {
    public List<BeanMyUser> loadAll(){
        List<BeanMyUser> list = new ArrayList<BeanMyUser>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanMyUser";
        Query query = session.createQuery(hql);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public BeanMyUser findUserByName(String name) throws BaseException{
        BeanMyUser user = new BeanMyUser();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanMyUser b where b.userName = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        if(query.list().size() == 0){
            throw new BaseException("用户未找到");
        }else{
            user = (BeanMyUser) query.list().get(0);
        }
        tx.commit();
        session.close();
        return user;
    }

    public BeanMyUser findUserById(int id) {
        BeanMyUser user = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query  = session.createQuery("from BeanMyUser b where  b.userId = :id");
        query.setParameter("id",id);
        user = (BeanMyUser) query.list().get(0);
        tx.commit();
        session.close();
        return user;
    }

    public void addUser(BeanMyUser user) throws BaseException{
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanMyUser b where b.userName = :name");
        query.setParameter("name", user.getUserName());
        if(query.list().size() != 0){
            throw new BaseException("用户名已存在");
        }else {
            session.save(user);
        }
        tx.commit();
        session.close();
    }
}
