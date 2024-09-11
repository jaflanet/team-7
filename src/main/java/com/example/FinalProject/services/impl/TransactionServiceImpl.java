package com.example.FinalProject.services.impl;

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

    private static final int BORROWING_LIMIT_REGULAR = 5;
    private static final int BORROWING_LIMIT_PREMIUM = 10;
    private static final int BORROW_DAYS = 14;

    @Override
    public String borrowBook(Long patronId, Long bookId) {
        PatronEntity patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Invalid patron ID"));
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Invalid book ID"));
        if (book.getAvailable_copies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        int borrowingLimit = getBorrowingLimit(patron);
        List<TransactionEntity> transactions = transactionRepository.findAll();
        int activeBorrowCount = 0;
        for (TransactionEntity transaction : transactions) {
            if (transaction.getPatron().getId().equals(patronId) && transaction.getReturn_date() == null) {
                activeBorrowCount++;
            }
        }
        if (activeBorrowCount >= borrowingLimit) {
            throw new RuntimeException("Patron has reached borrowing limit");
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
    public String returnBook(Long bookId, Long patronId) {

        List<TransactionEntity> transactions = transactionRepository.findAll();

        TransactionEntity transactionToReturn = null;
        for (TransactionEntity transaction : transactions) {
            if (transaction.getBook().getId().equals(bookId)
                    && transaction.getPatron().getId().equals(patronId)
                    && transaction.getReturn_date() == null) {
                transactionToReturn = transaction;
                break;
            }
        }

        System.out.println(transactionToReturn);

        if (transactionToReturn == null) {
            throw new RuntimeException("No active transaction found for the given book and patron");
        }

        transactionToReturn.setReturn_date(LocalDate.now());
        transactionRepository.save(transactionToReturn);

        BookEntity book = transactionToReturn.getBook();
        book.setAvailable_copies(book.getAvailable_copies() + 1);
        bookRepository.save(book);

        return "Book returned successfully";
    }

    private int getBorrowingLimit(PatronEntity patron) {
        String membershipType = patron.getMembership_type();
        if ("Regular".equalsIgnoreCase(membershipType)) {
            return BORROWING_LIMIT_REGULAR;
        } else if ("Premium".equalsIgnoreCase(membershipType)) {
            return BORROWING_LIMIT_PREMIUM;
        } else {
            throw new RuntimeException("Unknown membership type");
        }
    }
}
