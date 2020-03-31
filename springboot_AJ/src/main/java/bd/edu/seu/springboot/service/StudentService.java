package bd.edu.seu.springboot.service;

import bd.edu.seu.springboot.model.Student;
import bd.edu.seu.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import java.util.Optional;
@Controller
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

}
