package com.example.demo.Controller;


import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Student_Service {
    
    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getALLStudent()
    {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public Student getStudent(long id)
    {
        return studentRepository.findById(id).get();
    }

    public void CreateStudent(Student student)
    {
         studentRepository.save(student);
    }

    public void UpdateStudent(Student student, long id)
    {
        studentRepository.save(student);
    }

    public void DeleteStudent(long id)
    {
        studentRepository.deleteById(id);
    }

}
