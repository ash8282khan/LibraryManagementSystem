package com.training.LibraryManagementSystem.service.serviceImplementation;

import com.training.LibraryManagementSystem.entity.BorrowRecord;
import com.training.LibraryManagementSystem.entity.Book;
import com.training.LibraryManagementSystem.entity.Student;
import com.training.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.training.LibraryManagementSystem.repository.BorrowRecordRepository;
import com.training.LibraryManagementSystem.repository.BookRepository;
import com.training.LibraryManagementSystem.repository.StudentRepository;
import com.training.LibraryManagementSystem.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BorrowRecord> getAllRecords() {
        return borrowRecordRepository.findAll();
    }

    @Override
    public BorrowRecord borrowBook(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));


        boolean alreadyBorrowed = borrowRecordRepository.findById(studentId).stream()
                .anyMatch(record ->
                        record.getBook().getId().equals(bookId) && !record.getReturned()
                );

        if (alreadyBorrowed) {
            throw new RuntimeException("Student has already borrowed this book and not returned it yet.");
        }


        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies for this book");
        }


        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);


        BorrowRecord record = new BorrowRecord();
        record.setStudent(student);
        record.setBook(book);
        record.setBorrowDate(LocalDateTime.now());
        record.setReturned(false);

        return borrowRecordRepository.save(record);
    }


    @Override
    public BorrowRecord returnBook(Long borrowId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found with id: " + borrowId));

        if (record.getReturned()) {
            throw new RuntimeException("Book already returned");
        }

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        record.setReturned(true);
        record.setReturnDate(LocalDateTime.now());
        return borrowRecordRepository.save(record);
    }
}
