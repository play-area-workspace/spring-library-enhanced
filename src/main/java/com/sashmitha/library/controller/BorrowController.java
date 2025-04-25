package com.sashmitha.library.controller;

import com.sashmitha.library.entity.BorrowRecord;
import com.sashmitha.library.repository.UserRepository;
import com.sashmitha.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;
    private final UserRepository userRepository;

    @PostMapping("/{bookId}")
    public ResponseEntity<String> borrowBook(
            @PathVariable Long bookId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserIdFromUsername(userDetails.getUsername());
        return ResponseEntity.ok(borrowService.borrowBook(userId, bookId));
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(
            @PathVariable Long bookId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserIdFromUsername(userDetails.getUsername());
        return ResponseEntity.ok(borrowService.returnBook(userId, bookId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<BorrowRecord>> history(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserIdFromUsername(userDetails.getUsername());
        return ResponseEntity.ok(borrowService.getUserHistory(userId));
    }

    // Utility method: You can also inject user ID directly if you store it in token claims
    private Long getUserIdFromUsername(String username) {
        return userRepository.findByEmail(username) // or findByUsername
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                .getId();
    }
}

