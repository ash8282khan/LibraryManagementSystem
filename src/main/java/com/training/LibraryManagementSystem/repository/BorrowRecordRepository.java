package com.training.LibraryManagementSystem.repository;

import com.training.LibraryManagementSystem.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
}