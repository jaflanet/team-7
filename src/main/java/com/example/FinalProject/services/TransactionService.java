package com.example.FinalProject.services;

import com.example.FinalProject.dto.dtoOverdue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {
    String borrowBook(Long patronId, Long bookId);
    String returnBook(Long bookId, Long patronId);
    List<dtoOverdue> getOverdueBooks();
}
