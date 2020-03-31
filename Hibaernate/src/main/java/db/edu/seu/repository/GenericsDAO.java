package db.edu.seu.repository;

import db.edu.seu.model.product;
import db.edu.seu.util.HibernanateUitl;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public interface GenericsDAO<T,ID> {

    List<T> findAll();
    T findById(ID id);
    T save(T type);
    void deleteById(ID id);
    void deleteAll();
    T update(ID id, T type);
}
