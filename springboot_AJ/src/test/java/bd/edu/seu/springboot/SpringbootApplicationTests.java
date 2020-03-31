package bd.edu.seu.springboot;

import bd.edu.seu.springboot.model.Student;
import bd.edu.seu.springboot.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private StudentRepository crudRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void  saveStudent()
    {
        Student student = new Student(123,"abul",3.45, LocalDate.of(1991,04,15));
        crudRepository.save(student);
        Student student1 = new Student(124,"babul",3.67,LocalDate.of(1990,01,01));
        crudRepository.save(student1);
        Student student2 = new Student(125,"karim",3.88,LocalDate.of(2000,12,31));
        crudRepository.save(student2);
        Student student3 = new Student(126,"rahim",3.92,LocalDate.of(1995,07,19));
        crudRepository.save(student3);
        Student student4 = new Student(127,"mafiz",3.75,LocalDate.of(2002,07,12));
        crudRepository.save(student4);
    }

    @Test
    void ReadStudent()
    {
        Optional<Student> Student = crudRepository.findById(123L);
        Student.ifPresent(System.out::println);
    }

    @Test
    void ReadStudents()
    {
        Iterable<Student> allStudent = crudRepository.findAll();
        List<Student>studentList  = new ArrayList<>();
        allStudent.forEach(studentList::add);
        studentList.forEach(System.out::println);
    }

    @Test
    void DeleteStudent()
    {
        Student student = crudRepository.findById(123L).orElse(null);
        if(student!=null)
        {
            crudRepository.delete(student);
            System.out.println(student);
        }

    }

    @Test
    void DeleteStudents()
    {
        crudRepository.deleteAll();
    }

    @Test
    void UpdateStudent()
    {
        Student student = crudRepository.findById(123L).orElse(null);
        if(student!=null)
        {
            student.setName("Manik");
            student.setCgpa(3.74);
            crudRepository.save(student);
        }
        crudRepository.findById(123L).ifPresent(System.out::println);
    }

    @Test
    void findByName()
    {
        List<Student> studentList = crudRepository.findAllByName("babul");
        studentList.forEach(System.out::println);
    }

    @Test
    void findAllByNameContain()
    {
        crudRepository.findAllByNameContains("M").forEach(System.out::println);
    }

    @Test
    void findAllByGivenRangeDateOfBirth()
    {
        crudRepository.findByLocalDateBetween(LocalDate.of(1990,01,01),
                LocalDate.of(2000,12,31))
                .forEach(System.out::println);
    }

    @Test
    void findAllByRangeOfCgpa()
    {
        crudRepository.findByCgpaGreaterThanEqual(3.75).forEach(System.out::println);
    }

}
