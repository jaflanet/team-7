package com.example.FinalProject.services;

import com.example.FinalProject.dto.dtoAvailable;
import com.example.FinalProject.dto.dtoGetBook;
import com.example.FinalProject.dto.dtoOverdue;
import com.example.FinalProject.dto.dtoPostBook;
import com.example.FinalProject.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {
    List<dtoGetBook> getAllBooks();

    BookEntity getBookById (Long id);

    BookEntity saveBook (dtoPostBook book);

    BookEntity updateBook (Long id, dtoPostBook bookRequest);

    ResponseEntity<String> deleteBookByid(Long id);

    BookEntity getBookByTitleOrAuthor(String title, String author);

    List<dtoOverdue> getOverdueBooks();

    List<dtoAvailable> checkBookAvailability(Long bookId);
//    Page<BookEntity> getBooks(Pageable pageable);
}
