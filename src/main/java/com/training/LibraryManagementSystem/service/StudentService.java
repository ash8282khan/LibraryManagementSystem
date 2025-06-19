package com.training.LibraryManagementSystem.service;

import com.training.LibraryManagementSystem.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    void deleteStudent(Long id);
}
