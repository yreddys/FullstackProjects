package com.studentportal.studentportalapi.repository;

import com.studentportal.studentportalapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
