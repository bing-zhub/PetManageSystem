package control;

import model.BeanAppointment;
import model.BeanAppointmentDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

import java.sql.Date;
import java.util.List;

import static util.HibernateUtil.getSession;

public class AppointmentController {
    public List<BeanAppointment> loadAll(){
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


    public void delAppointment(int app_id){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanAppointment  b where b.appId = :id");
        query.setParameter("id", app_id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }


    public BeanAppointment findAppointmentById(int id) throws BaseException{
        BeanAppointment appointment = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanAppointment  b where b.appId = :id");
        query.setParameter("id",id);
        if(query.list().size() == 0)
            throw new BaseException("Ô¤Ô¼²»´æÔÚ");
        appointment = (BeanAppointment) query.list().get(0);
        tx.commit();
        session.close();
        return appointment;
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

    public List<BeanAppointmentDetail> loadAllDetails() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanAppointmentDetail ");
        List<BeanAppointmentDetail> list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public List<BeanAppointmentDetail> loadDetailByAppointmentId(int appId){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanAppointmentDetail b where b.appointment.appId = :id");
        query.setParameter("id", appId);
        List<BeanAppointmentDetail> list = query.list();
        tx.commit();
        session.close();
        return list;
    }
}
