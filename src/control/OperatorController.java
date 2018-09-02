package control;

import model.BeanOperator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;


import java.util.List;

import static util.HibernateUtil.getSession;

// login resetpwd sigup
public class OperatorController {

    public BeanOperator login(String username, String pwd) throws BaseException{

        Session session = getSession();
        String hql = "from BeanOperator b where b.opName = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name",username);
        BeanOperator beanOperator = (BeanOperator) query.list().get(0);
        if(beanOperator.getOpPwd().equals(pwd)){
           return beanOperator;
        }else{
            throw new BaseException("�˺����벻ƥ��");
        }
    }

    public BeanOperator register(String username, String pwd1, String pwd2) throws BaseException{

        if(username.length() < 5){
            throw  new BaseException("�û�������С��5");
        }

        if(!pwd1.equals(pwd2)){
            System.out.println(pwd1+" "+pwd2);
            throw new BaseException("�����������벻һ��");
        }

        if(pwd1.length() < 6){
            throw new BaseException("���볤��С��6");
        }

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanOperator b where b.opName = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name",username);
        BeanOperator beanOperator = (BeanOperator) query.uniqueResult();
        BeanOperator beanOperator1 = null;
        if(beanOperator == null){
            beanOperator1 = new BeanOperator();
            beanOperator1.setOpLevel(1);
            beanOperator1.setOpName(username);
            beanOperator1.setOpPwd(pwd1);
            session.save(beanOperator1);
        }else{
            throw new BaseException("���û����ѱ�ʹ��");
        }
        tx.commit();
        session.close();
        return beanOperator1;
    }

    public List<BeanOperator> loadAll() {
        List<BeanOperator> list = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOperator ");
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }
}
