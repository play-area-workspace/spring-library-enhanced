package com.sashmitha.library.service;

import com.sashmitha.library.entity.Book;
import com.sashmitha.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Search books by author
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    // Search books by published year
    public List<Book> searchBooksByPublishedYear(int publishedYear) {
        return bookRepository.findByPublishedYear(publishedYear);
    }

    // Get books with available copies
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }
}
