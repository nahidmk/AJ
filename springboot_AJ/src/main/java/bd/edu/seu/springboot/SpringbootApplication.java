package bd.edu.seu.springboot;

import bd.edu.seu.springboot.model.Student;
import bd.edu.seu.springboot.repository.StudentRepository;
import bd.edu.seu.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

@SpringBootApplication
public class SpringbootApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);

    }


}
