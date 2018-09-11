package control;

import com.sun.xml.internal.rngom.parse.host.Base;
import model.BeanOperator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
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
        if(query.list().size()==0){
            throw new BaseException("用户不存在");
        }
        BeanOperator beanOperator = (BeanOperator) query.list().get(0);
        if(beanOperator.getOpPwd().equals(pwd)){
           return beanOperator;
        }else{
            throw new BaseException("账号密码不匹配");
        }
    }

    public BeanOperator register(String username, String pwd1, String pwd2) throws BaseException{

        if(username.length() < 5){
            throw  new BaseException("用户名长度小于5");
        }

        if(!pwd1.equals(pwd2)){
            System.out.println(pwd1+" "+pwd2);
            throw new BaseException("两次密码输入不一致");
        }

        if(pwd1.length() < 6){
            throw new BaseException("密码长度小于6");
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
            throw new BaseException("改用户名已被使用");
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

    public void delOperator(int id) throws BaseException{
        if(BeanOperator.currentOperator.getOpId() == id){
            throw new BaseException("当前管理员活动中不可删除");
        }
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanOperator b where b.opId = :id");
        query.setParameter("id",id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public BeanOperator findOperatorById(int id) {
        BeanOperator operator = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOperator b where b.opId = :id");
        query.setParameter("id", id );
        operator = (BeanOperator) query.list().get(0);
        tx.commit();
        session.close();
        return operator;
    }

    public void addOperator(BeanOperator operator) throws BaseException{
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOperator b where b.opName = :name");
        query.setParameter("name", operator.getOpName());
        if(query.list().size()!=0)
            throw new BaseException("用户名已被占用");
        else
            session.save(operator);
        tx.commit();
        session.close();
    }

    public static void main(String[] args) {
        try{
            BeanOperator b = new BeanOperator();
            b.setOpLevel(1);
            b.setOpPwd("12345678");
            b.setOpName("amdin");
            new OperatorController().addOperator(b);
        }catch (BaseException e){
            System.out.println(e.getMessage());
        }
    }

    public void levOperator(BeanOperator operator,int i) throws BaseException{
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        int j = operator.getOpLevel()+i;
        operator.setOpLevel(j);
        if(j==0)
            throw new BaseException("最低为1");
        else
            session.update(operator);

        tx.commit();
        session.close();
    }

    public BeanOperator findOperatorByName(String userName) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOperator b where b.opName = :name");
        query.setParameter("name",userName);
        BeanOperator operator = (BeanOperator) query.list().get(0);
        tx.commit();
        session.close();
        return operator;
    }

}
