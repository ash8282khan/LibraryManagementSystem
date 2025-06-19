package com.training.LibraryManagementSystem.controller;

import com.training.LibraryManagementSystem.entity.BorrowRecord;
import com.training.LibraryManagementSystem.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public List<BorrowRecord> getAllRecords() {
        return borrowService.getAllRecords();
    }

    @PostMapping("/issue")
    public BorrowRecord issueBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        return borrowService.borrowBook(studentId, bookId);
    }

    @PostMapping("/return/{id}")
    public BorrowRecord returnBook(@PathVariable Long id) {
        return borrowService.returnBook(id);
    }
}
