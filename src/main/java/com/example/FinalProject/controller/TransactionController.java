package com.example.FinalProject.controller;

import com.example.FinalProject.dto.dtoBorrow;
import com.example.FinalProject.dto.dtoReturn;
import com.example.FinalProject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody dtoReturn returnRequest) {
            String result = transactionService.returnBook(returnRequest.getBook_id(),returnRequest.getPatron_id());
            return ResponseEntity.ok(result);
    }
}
