package com.training.LibraryManagementSystem.repository;

import com.training.LibraryManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

