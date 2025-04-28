package com.sashmitha.library.repository;

import com.sashmitha.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by author
    Page<Book> findByAuthor(String author, Pageable pageable);

    // Find books by published year
    Page<Book> findByPublishedYear(int publishedYear, Pageable pageable);

    // Find books with available copies
    Page<Book> findByAvailableCopiesGreaterThan(int availableCopies, Pageable pageable);
}
