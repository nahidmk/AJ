package com.example.demo.Controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class API_controller {


 @Autowired
 private Student_Service student_service;


 @RequestMapping("/students")
 public List<Student> getAllStudent()
 {
     return student_service.getALLStudent();
 }

 @RequestMapping("/student/{id}")
 public Student getStudent(@PathVariable long id)
 {
  return student_service.getStudent(id);
 }

 @RequestMapping(method = RequestMethod.POST,value = "/student")
public void addStudent(@RequestBody Student student)
{
  student_service.CreateStudent(student);
}

 @RequestMapping(method = RequestMethod.PUT,value = "/student/{id}")
 public void UpdateStudent(@RequestBody Student student,@PathVariable long id)
 {
    student_service.UpdateStudent(student,id);
 }

 @RequestMapping(method = RequestMethod.DELETE,value = "/student/{id}")
 public void DeleteStudent(@PathVariable long id)
 {
   student_service.DeleteStudent(id);
 }
}
