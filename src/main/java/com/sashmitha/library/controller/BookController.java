package com.sashmitha.library.controller;

import com.sashmitha.library.entity.Book;
import com.sashmitha.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Get all books
    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Search books by author
    @GetMapping("/api/books/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publishedYear) {

        if (author != null) {
            List<Book> booksByAuthor = bookService.searchBooksByAuthor(author);
            return ResponseEntity.ok(booksByAuthor);
        } else if (publishedYear != null) {
            List<Book> booksByYear = bookService.searchBooksByPublishedYear(publishedYear);
            return ResponseEntity.ok(booksByYear);
        } else {
            List<Book> availableBooks = bookService.getAvailableBooks();
            return ResponseEntity.ok(availableBooks);
        }
    }
}
