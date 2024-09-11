package com.example.FinalProject.services;

import org.springframework.stereotype.Component;

@Component
public interface TransactionService {

    String borrowBook(Long patronId, Long bookId);
    String returnBook(Long bookId, Long patronId);
}
