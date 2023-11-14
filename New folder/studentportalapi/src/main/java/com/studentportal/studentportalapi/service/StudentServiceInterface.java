package com.studentportal.studentportalapi.service;

import com.studentportal.studentportalapi.model.Student;

import java.util.List;

public interface StudentServiceInterface {
    public String addStudent(Student student);
    public List<Student> findAllStudent();
}
