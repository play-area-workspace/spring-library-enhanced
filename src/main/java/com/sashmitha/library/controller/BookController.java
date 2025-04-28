package com.sashmitha.library.controller;

import com.sashmitha.library.entity.Book;
import com.sashmitha.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(books);
    }

    // Search books by author
    @GetMapping("/api/books/search")
    public ResponseEntity<Page<Book>> searchBooks(
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "publishedYear", required = false) Integer publishedYear,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        if (author != null) {
            Page<Book> booksByAuthor = bookService.searchBooksByAuthor(author, pageable);
            return ResponseEntity.ok(booksByAuthor);
        } else if (publishedYear != null) {
            Page<Book> booksByYear = bookService.searchBooksByPublishedYear(publishedYear, pageable);
            return ResponseEntity.ok(booksByYear);
        } else {
            Page<Book> availableBooks = bookService.getAvailableBooks(pageable);
            return ResponseEntity.ok(availableBooks);
        }
    }
}
