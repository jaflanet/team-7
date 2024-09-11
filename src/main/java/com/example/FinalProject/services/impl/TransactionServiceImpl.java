package com.example.FinalProject.services.impl;

import com.example.FinalProject.dto.dtoOverdue;
import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.entity.TransactionEntity;
import com.example.FinalProject.repository.BookRepository;
import com.example.FinalProject.repository.PatronRepository;
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

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    private static final int BORROWING_LIMIT_REGULAR = 5;  // Example limit for REGULAR members
    private static final int BORROWING_LIMIT_PREMIUM = 10;  // Example limit for PREMIUM members
    private static final int BORROW_DAYS = 14;  // Example borrow duration in days

    @Override
    public String borrowBook(Long patronId, Long bookId) {
        PatronEntity patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Invalid patron ID"));

        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Invalid book ID"));


        if (book.getAvailable_copies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        book.setAvailable_copies(book.getAvailable_copies() - 1);
        bookRepository.save(book);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setPatron(patron);
        transaction.setBook(book);
        transaction.setBorrow_date(LocalDate.now());
        transaction.setDue_date(LocalDate.now().plusDays(BORROW_DAYS));
        transactionRepository.save(transaction);
        return "Book borrowed successfully";
    }
    @Override
    public List<dtoOverdue> getOverdueBooks() {
        return transactionRepository.findOverdueBooks();
    }

}
