package com.example.FinalProject.services;

import com.example.FinalProject.dto.dtoOverdue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {
    String borrowBook(Long patronId, Long bookId);

<<<<<<< HEAD
    List<dtoOverdue> getOverdueBooks();
=======
    String returnBook(Long bookId, Long patronId);
>>>>>>> 5080677eec6d662420feddf7eff82d5882105e5a
}
