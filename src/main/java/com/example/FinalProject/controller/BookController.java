package com.example.FinalProject.controller;

import com.example.FinalProject.dto.dtoGetBook;
import com.example.FinalProject.dto.dtoPostBook;
import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
        @Autowired
        private BookService bookService;

        @GetMapping()
        public List<dtoGetBook> getAllBooks() {
            return bookService.getAllBooks();
        }

        @GetMapping("/{id}")
        public ResponseEntity<BookEntity> getBookById(
                @PathVariable Long id
        ){
            BookEntity book = bookService.getBookById(id);
            if (book != null) {
                return ResponseEntity.ok(book);
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @PostMapping
        public ResponseEntity<BookEntity> createBook(@RequestBody dtoPostBook book) {
            BookEntity savedBook = bookService.saveBook(book);
            return ResponseEntity.ok(savedBook);
        }

    @PutMapping("/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody dtoPostBook book) {
        BookEntity savedBook = bookService.updateBook(id,book);
        return ResponseEntity.ok(savedBook);
    }



        // Search book by title/author/genre
        @GetMapping("/search")
        public ResponseEntity<BookEntity> getBookByTitleOrAuthorOrGenre(
                @RequestParam (name = "title", required = false, defaultValue = "") String title,
                @RequestParam (name = "author", required = false, defaultValue = "") String author
        ){
            BookEntity book = bookService.getBookByTitleOrAuthor(title, author);
            if (book != null) {
                return ResponseEntity.ok(book);
            } else {
                return ResponseEntity.notFound().build();
            }
        }


    }
