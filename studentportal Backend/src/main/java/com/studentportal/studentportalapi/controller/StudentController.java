package com.studentportal.studentportalapi.controller;

import com.studentportal.studentportalapi.model.Student;
import com.studentportal.studentportalapi.service.StudentService;
import com.studentportal.studentportalapi.service.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent( @RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> findALlStudents(){
        return studentService.findAllStudent();
    }
}
