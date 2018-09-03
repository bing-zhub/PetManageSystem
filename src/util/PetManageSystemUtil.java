package util;

import control.*;
import model.BeanCategory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static util.HibernateUtil.getSession;

public class PetManageSystemUtil {
    public static AppointmentController appointmentController = new AppointmentController();
    public static CategoryController categoryController = new CategoryController();
    public static OperatorController operatorController = new OperatorController();
    public static OrderController orderController = new OrderController();
    public static PetController petController = new PetController();
    public static ProductController productController = new ProductController();
    public static ServiceController serviceController = new ServiceController();
    public static UserController userController = new UserController();


    public static void update(Object o) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(o);
        tx.commit();
        session.close();
    }

    public static void save(Object o) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(o);
        tx.commit();
        session.close();
    }
}
