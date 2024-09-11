package com.example.FinalProject.controller;

import com.example.FinalProject.dto.dtoBorrow;
import com.example.FinalProject.dto.dtoOverdue;
import com.example.FinalProject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody dtoBorrow borrowRequest) {
        String result = transactionService.borrowBook(borrowRequest.getPatron_id(), borrowRequest.getBook_id());
        return ResponseEntity.ok(result);
    }

    public static class BorrowRequest {
        private Long patronId;
        private Long bookId;

        // Getters and Setters
    }

    public static class BorrowResponse {
        private String message;
        private String dueDate;

        public BorrowResponse(String message, String dueDate) {
            this.message = message;
            this.dueDate = dueDate;
        }

        // Getters and Setters
    }

    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        // Getters and Setters
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<dtoOverdue>> getOverdueBooks() {
        List<dtoOverdue> overdueBooks = transactionService.getOverdueBooks();
        return ResponseEntity.ok(overdueBooks);
    }
}
