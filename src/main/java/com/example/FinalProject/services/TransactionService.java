package com.example.FinalProject.services;

import com.example.FinalProject.dto.dtoOverdue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {

    String borrowBook(Long patronId, Long bookId);

    List<dtoOverdue> getOverdueBooks();
}
