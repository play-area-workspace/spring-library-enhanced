package com.sashmitha.library.service;

import com.sashmitha.library.entity.Book;
import com.sashmitha.library.entity.BorrowRecord;
import com.sashmitha.library.entity.User;
import com.sashmitha.library.repository.BookRepository;
import com.sashmitha.library.repository.BorrowRecordRepository;
import com.sashmitha.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;

    public String borrowBook(Long userId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            return "Book not available right now";
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setUser(user);
        record.setBorrowedAt(LocalDateTime.now());
        borrowRecordRepository.save(record);

        return "Book borrowed successfully";
    }

    public String returnBook(Long userId, Long bookId) {
        BorrowRecord record = borrowRecordRepository
                .findByUserIdAndBookIdAndReturnedAtIsNull(userId, bookId)
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        record.setReturnedAt(LocalDateTime.now());
        borrowRecordRepository.save(record);

        return "Book returned successfully";
    }

    public List<BorrowRecord> getUserHistory(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }
}

