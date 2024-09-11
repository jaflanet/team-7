package com.example.FinalProject.controller;

import com.example.FinalProject.dto.dtoGetBook;
import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.dto.dtoPostBook;
import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
        @Autowired
        private BookService bookService;

        //Get all book
        @GetMapping()
        public List<dtoGetBook> getAllBooks() {
            return bookService.getAllBooks();
        }

        // Get book by id
        @GetMapping("/{id}")
        public ResponseEntity<dtoGetBook> getBookById(
                @PathVariable Long id
        ){
            BookEntity book = bookService.getBookById(id);
            if (book != null) {
                dtoGetBook data = new dtoGetBook(book.getId(), book.getTitle(),
                        book.getAuthor(), book.getAvailable_copies());
                return ResponseEntity.ok(data); }
            else{
                return ResponseEntity.notFound().build();
            }

        }

        // Add new book
        @PostMapping
        public ResponseEntity<BookEntity> createBook(@RequestBody dtoPostBook book) {
            BookEntity savedBook = bookService.saveBook(book);
            return ResponseEntity.ok(savedBook);
        }

        // Update book
        @PutMapping("/{id}")
         public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody dtoPostBook book) {
            BookEntity savedBook = bookService.updateBook(id,book);
            return ResponseEntity.ok(savedBook);
    }



        // Search book by title/author
        @GetMapping("/search")
        public ResponseEntity<dtoGetBook> getBookByTitleOrAuthor(
                @RequestParam(name = "title") String title,
                @RequestParam(name = "author") String author
        ) {
            // Retrieve a list of books based on the search criteria
            BookEntity books = bookService.getBookByTitleOrAuthor(title, author);
            if (books != null) {
                dtoGetBook searchResults = new dtoGetBook(books.getId(), books.getTitle(),
                        books.getAuthor(), books.getAvailable_copies());
                return ResponseEntity.ok(searchResults);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        //Delete Book
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteAuthor(
                @PathVariable Long id
        ) {
            BookEntity bookid = bookService.getBookById(id);
            if (bookid != null) {
                bookService.deleteBook(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }
