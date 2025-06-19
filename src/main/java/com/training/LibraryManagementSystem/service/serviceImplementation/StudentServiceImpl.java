package com.training.LibraryManagementSystem.service.serviceImplementation;

import com.training.LibraryManagementSystem.entity.Student;
import com.training.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.training.LibraryManagementSystem.repository.StudentRepository;
import com.training.LibraryManagementSystem.repository.BorrowRecordRepository;
import com.training.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;



    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Override
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }
}
