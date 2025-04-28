package com.sashmitha.library.service;

import com.sashmitha.library.entity.Book;
import com.sashmitha.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // Search books by author
    public Page<Book> searchBooksByAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthor(author, pageable);
    }

    // Search books by published year
    public Page<Book> searchBooksByPublishedYear(int publishedYear, Pageable pageable) {
        return bookRepository.findByPublishedYear(publishedYear, pageable);
    }

    // Get books with available copies
    public Page<Book> getAvailableBooks(Pageable pageable) {
        return bookRepository.findByAvailableCopiesGreaterThan(0, pageable);
    }
}
