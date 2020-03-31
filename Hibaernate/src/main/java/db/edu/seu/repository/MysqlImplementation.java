package db.edu.seu.repository;

import db.edu.seu.model.product;
import db.edu.seu.util.HibernanateUitl;
import org.hibernate.Session;

public class MysqlImplementation  {
    HibernanateUitl hibernanateUitl  = new HibernanateUitl();
    Session session = hibernanateUitl.getSession().openSession();
    public product saveProduct(product p) {
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        return p;
    }

    public product read_a_Product(long id) {
        session.beginTransaction();
        product p = session.get(product.class,id);
        session.getTransaction().commit();
        return p;
    }

    public product delete_a_product(long id) {
        product p = read_a_Product(id);
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        return p;
    }

    public product updateProduct(long id, product p) {
        product p1 = read_a_Product(id);
        p1.setId(p.getId());
        p1.setName(p.getName());
        p1.setUnitprice(p.getUnitprice());
        p1.setUnitStock(p.getUnitStock());
        session.beginTransaction();
        session.update(p1);
        session.getTransaction().commit();
        return p;
    }
}
