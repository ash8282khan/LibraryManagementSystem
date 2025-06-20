package com.training.LibraryManagementSystem.controller;

import com.training.LibraryManagementSystem.entity.Book;
import com.training.LibraryManagementSystem.entity.Student;
import com.training.LibraryManagementSystem.service.BookService;
import com.training.LibraryManagementSystem.service.BorrowService;
import com.training.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class ViewController {

    @Autowired
    private  StudentService studentService;
    @Autowired
    private  BookService bookService;
    @Autowired
    private  BorrowService borrowService;

    public ViewController() {
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/students/view";
    }

    @GetMapping("/students/view")
    public String viewStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping("/students/save")
    public String addStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students/view";
    }

    @GetMapping("/books/view")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book());
        return "books";
    }

    @PostMapping("/books/save")
    public String addBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books/view";
    }
    @GetMapping("/books/view/search")
    public String viewBooks(@RequestParam(value = "title", required = false) String title, Model model) {
        if (title != null && !title.isEmpty()) {
            model.addAttribute("books", bookService.searchBooks(title));
        } else {
            model.addAttribute("books", bookService.getAllBooks());
        }
        model.addAttribute("book", new Book());
        return "books";
    }

    @GetMapping("/borrow-records")
    public String viewBorrowRecords(Model model) {
        model.addAttribute("records", borrowService.getAllRecords());
        return "borrow-records";
    }


    @GetMapping("/issue")
    public String issueBookForm() {
        return "issue-book";
    }

    @PostMapping("/issue")
    public String issueBook(@RequestParam Long studentId, @RequestParam Long bookId, Model model) {
        try {
            borrowService.borrowBook(studentId, bookId);
            model.addAttribute("message", "Book issued successfully!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "issue-book";
    }

    @GetMapping("/return")
    public String returnBookForm() {
        return "return-book";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Long recordId, Model model) {
        try {
            borrowService.returnBook(recordId);
            model.addAttribute("message", "Book returned successfully!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "return-book";
    }
}
