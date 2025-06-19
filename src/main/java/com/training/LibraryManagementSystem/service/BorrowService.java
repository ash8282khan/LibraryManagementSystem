package com.training.LibraryManagementSystem.service;

        import com.training.LibraryManagementSystem.entity.BorrowRecord;
        import java.util.List;

public interface BorrowService {
    List<BorrowRecord> getAllRecords();
    BorrowRecord borrowBook(Long studentId, Long bookId);
    BorrowRecord returnBook(Long borrowId);
}