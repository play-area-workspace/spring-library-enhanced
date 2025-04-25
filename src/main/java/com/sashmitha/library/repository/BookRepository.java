package com.sashmitha.library.repository;

import com.sashmitha.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by author
    List<Book> findByAuthor(String author);

    // Find books by published year
    List<Book> findByPublishedYear(int publishedYear);

    // Find books with available copies
    List<Book> findByAvailableCopiesGreaterThan(int availableCopies);
}
