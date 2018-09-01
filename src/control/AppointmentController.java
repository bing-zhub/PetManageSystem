package control;

import model.BeanAppointment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

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
}
