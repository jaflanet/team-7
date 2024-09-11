package com.example.FinalProject.services.impl;

import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.entity.TransactionEntity;
import com.example.FinalProject.repository.TransactionRepository;
import com.example.FinalProject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    public TransactionEntity borrowBook(PatronEntity patron, BookEntity book) {
//        return transactionRepository.save(new TransactionEntity(patron, book, LocalDate.now(), LocalDate.now().plusDays(14)));
//    }
//
//    public double calculateFine(TransactionEntity transaction) {
//
//        return 0.0;
//    }

}
