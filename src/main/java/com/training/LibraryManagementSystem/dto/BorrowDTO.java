package com.training.LibraryManagementSystem.dto;

import com.training.LibraryManagementSystem.entity.Book;
import com.training.LibraryManagementSystem.entity.Student;
import java.time.LocalDateTime;

public class BorrowDTO {

    private Long id;

    private Student student;


    private Book book;

    private final LocalDateTime borrowDate = LocalDateTime.now();
    private LocalDateTime returnDate;
    private Boolean isReturned = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

}