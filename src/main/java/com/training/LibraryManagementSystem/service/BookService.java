package com.training.LibraryManagementSystem.service;

import com.training.LibraryManagementSystem.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book getBookById(Long id);
    Book updateBook(Long id, Book updatedBook);
    void deleteBook(Long id);
    List<Book> searchBooks(String keyword);
}
