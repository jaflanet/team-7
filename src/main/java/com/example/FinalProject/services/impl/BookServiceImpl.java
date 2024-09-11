package com.example.FinalProject.services.impl;

import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.repository.BookRepository;
import com.example.FinalProject.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public BookEntity saveBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public BookEntity getBookByTitleOrAuthor(String title, String author){
        return bookRepository.findByTitleOrAuthor(title, author);
    }

    @Override
    public Page<BookEntity> getBooks(Pageable pageable) { return bookRepository.findAll(pageable); }

}
