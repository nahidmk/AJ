package bd.edu.seu.springboot.repository;

import bd.edu.seu.springboot.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {

    List<Student> findAllByName(String Name);
    List<Student> findAllByNameContains(String partial);
    List<Student> findByCgpaGreaterThanEqual(double Cgpa);
    List<Student> findByLocalDateBetween(LocalDate localDate1, LocalDate localDate2);
}
