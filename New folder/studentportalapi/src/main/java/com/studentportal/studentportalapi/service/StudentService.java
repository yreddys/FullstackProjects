package com.studentportal.studentportalapi.service;

import com.studentportal.studentportalapi.model.Student;
import com.studentportal.studentportalapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentServiceInterface{

    @Autowired
    StudentRepository studentRepository;
    @Override
    public String addStudent(Student student) {
        Student result = studentRepository.save(student);
        return "Successful";
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
