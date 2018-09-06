package control;

import model.BeanPet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class PetController {
    public List<BeanPet> loadAll(int owner_id){
        List<BeanPet> list = new ArrayList<BeanPet>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanPet b where b.petOwner = :owner";
        Query query = session.createQuery(hql);
        query.setParameter("owner", owner_id);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public BeanPet findPetByName(String name){
        BeanPet pet = new BeanPet();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanPet b where b.petNikename = :name");
        query.setParameter("name", name);
        pet = (BeanPet) query.list().get(0);
        tx.commit();
        session.close();
        return pet;
    }

    public BeanPet findPetById(int id) {
        BeanPet pet = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanPet  b where b.petId = :id ");
        query.setParameter("id",id);
        pet = (BeanPet) query.list().get(0);
        tx.commit();
        session.close();
        return pet;
    }

    public List<BeanPet> loadAll() {
        List<BeanPet> list = new ArrayList<BeanPet>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanPet";
        Query query = session.createQuery(hql);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public void delPet(Integer petId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanPet pet where pet.petId = :id");
        query.setParameter("id",petId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}
