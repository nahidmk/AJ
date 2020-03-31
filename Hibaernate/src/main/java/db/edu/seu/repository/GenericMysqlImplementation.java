package db.edu.seu.repository;

import db.edu.seu.model.Supplier;
import db.edu.seu.model.product;
import db.edu.seu.util.HibernanateUitl;
import org.hibernate.Session;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;


public class GenericMysqlImplementation<T,ID> implements GenericsDAO<T,ID> {

    private Class<T> aClass;

    public GenericMysqlImplementation(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Override
    public List<T> findAll() {
        Session session = HibernanateUitl.getSession().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from "+aClass.getSimpleName(),aClass);
        List<T> tList = q.getResultList();
        session.getTransaction().commit();
        return tList;
    }

    @Override
    public T findById(ID id) {
        Session session = HibernanateUitl.getSession().openSession();
        session.beginTransaction();
       T t =  session.get(aClass, (Serializable) id);
        session.getTransaction().commit();
        return t;
    }

    @Override
    public T save(T type) {
        Session session = HibernanateUitl.getSession().openSession();
        session.beginTransaction();
        session.save(type);
        session.getTransaction().commit();
        return type;
    }

    @Override
    public void deleteById(ID id) {
        Session session = HibernanateUitl.getSession().openSession();
        session.beginTransaction();
        T t = session.get(aClass, (Serializable) id);
        session.delete(t);
        session.getTransaction().commit();

    }

    @Override
    public void deleteAll() {
            Session session = HibernanateUitl.getSession().openSession();
            Query q = session.createQuery("delete from "+aClass.getSimpleName());
            session.beginTransaction();
            q.executeUpdate();
            session.getTransaction().commit();

    }

    @Override
    public T update(ID id, T type) {
        Session session = HibernanateUitl.getSession().openSession();
        session.beginTransaction();
        session.update(type);
        session.getTransaction().commit();
        T t = findById(id);
        return t;
    }
}
