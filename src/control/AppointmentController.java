package control;

import model.BeanAppointment;
import model.BeanService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

import java.sql.Date;
import java.util.List;

import static util.HibernateUtil.getSession;

public class AppointmentController {
    public List<BeanAppointment> loadAll() throws BaseException{
        List<BeanAppointment> list = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanAppointment";
        Query query = session.createQuery(hql);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public void addAppointment(BeanAppointment appointment) throws BaseException{
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(appointment);
        tx.commit();
        session.close();
    }

    public BeanAppointment findAppointmentByName(String name){
        BeanAppointment beanAppointment = new BeanAppointment();
//        Session session = getSession();
//        Transaction tx = session.beginTransaction();
//        Query query = session.createQuery("from BeanAppointment b where b.app");

        return beanAppointment;
    }

    public void delAppointment(int app_id){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanAppointment  b where b.appId = :id");
        query.setParameter("id", app_id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public static void main(String[] args) {
        try{
            AppointmentController appointmentController = new AppointmentController();
            for(BeanAppointment b : appointmentController.loadAll()){
                System.out.println(b.getAppId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public BeanAppointment findAppointmentById(int id) {
        BeanAppointment appointment = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanAppointment  b where b.appId = :id");
        query.setParameter("id",id);
        appointment = (BeanAppointment) query.list().get(0);
        return appointment;
    }

    public void update(BeanAppointment objAppointmnet) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(objAppointmnet);
        tx.commit();
        session.close();
    }

    public void finishAppointment(BeanAppointment appointment){
        appointment.setAppDoneDate(new Date(System.currentTimeMillis()));
        appointment.setAppState("finished");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(appointment);
        tx.commit();
        session.close();
    }
}
