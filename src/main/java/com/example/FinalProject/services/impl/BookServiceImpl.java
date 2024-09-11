package com.example.FinalProject.services.impl;

import com.example.FinalProject.dto.dtoAvailable;
import com.example.FinalProject.dto.dtoGetBook;
import com.example.FinalProject.dto.dtoOverdue;
import com.example.FinalProject.dto.dtoPostBook;
import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.repository.BookRepository;
import com.example.FinalProject.repository.TransactionRepository;
import com.example.FinalProject.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private  TransactionRepository transactionRepository;

    @Override
    public List<dtoGetBook> getAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();

        // Convert entity to DTO using stream and mapping
        List<dtoGetBook> dtoGetBooks = bookEntities.stream()
                .map(entity -> new dtoGetBook(entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getAvailable_copies())) // Example of mapping fields
                .collect(Collectors.toList());

        return dtoGetBooks;
    }


    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


    @Override
    public BookEntity saveBook(dtoPostBook createBookDTO) {

        LocalDateTime currentTimestamp = LocalDateTime.now();

        BookEntity book = new BookEntity();
        book.setTitle(createBookDTO.getTitle());
        book.setIsbn(createBookDTO.getIsbn());
        book.setAuthor(createBookDTO.getAuthor());
        book.setQuantity(createBookDTO.getQuantity());
        book.setAvailable_copies(createBookDTO.getQuantity());
        book.setCreated_at(LocalDate.from(currentTimestamp));
        book.setUpdated_at(LocalDate.from(currentTimestamp));

        return bookRepository.save(book);
    }

    @Override
    public BookEntity updateBook(Long id, dtoPostBook createBookDTO) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        LocalDateTime currentTimestamp = LocalDateTime.now();
        if (createBookDTO.getTitle() != null) {
            book.setTitle(createBookDTO.getTitle());
        }
        if (createBookDTO.getIsbn() != null) {
            book.setIsbn(createBookDTO.getIsbn());
        }

        if (createBookDTO.getAuthor() != null) {
            book.setAuthor(createBookDTO.getAuthor());
        }

        if (createBookDTO.getQuantity() != null) {
            book.setQuantity(createBookDTO.getQuantity());
            book.setAvailable_copies(createBookDTO.getQuantity());
        }


        book.setUpdated_at(LocalDate.from(currentTimestamp));

        return bookRepository.save(book);
    }


    @Override
    public ResponseEntity<String> deleteBookByid(Long id) {
        int rowsAffected = bookRepository.deleteBooksById(id);

        if (rowsAffected > 0) {
            return new ResponseEntity<>("Book id " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete book with active loans.", HttpStatus.NOT_FOUND);
        }
    }

    public BookEntity getBookByTitleOrAuthor(String title, String author){
        return bookRepository.findByTitleOrAuthor(title, author);
    }

    public List<dtoOverdue> getOverdueBooks() {
        return transactionRepository.findOverdueBooks();
    }

    @Override
    public List<dtoAvailable> checkBookAvailability(Long id) {
        return bookRepository.findBookAvailability(id);
    }
//    @Override
//    public Page<BookEntity> getBooks(Pageable pageable) { return bookRepository.findAll(pageable); }


    }

