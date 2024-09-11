package com.example.FinalProject.services;

import com.example.FinalProject.dto.dtoGetBook;
import com.example.FinalProject.dto.dtoPostBook;
import com.example.FinalProject.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    List<dtoGetBook> getAllBooks();

    BookEntity getBookById (Long id);

    BookEntity saveBook (dtoPostBook book);

    BookEntity updateBook (Long id, dtoPostBook bookRequest);

    void deleteBook(Long id);

    BookEntity getBookByTitleOrAuthor(String title, String author);

    Page<BookEntity> getBooks(Pageable pageable);
}
