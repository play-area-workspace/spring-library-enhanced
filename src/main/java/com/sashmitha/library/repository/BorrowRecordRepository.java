package com.sashmitha.library.repository;

import com.sashmitha.library.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserId(Long userId);
    Optional<BorrowRecord> findByUserIdAndBookIdAndReturnedAtIsNull(Long userId, Long bookId);
}
