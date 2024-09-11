package com.example.FinalProject.controller;

import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
        @Autowired
        private BookService bookService;

        @GetMapping()
        public List<BookEntity> getAllBooks() {
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


        //  Create Book
        @PostMapping
        public BookEntity createBook(@RequestBody BookEntity book) {
            return bookService.saveBook(book);
        }

        //   Put Books
        @PutMapping("/{id}")
        public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookEntity bookDetails) {
            BookEntity book = bookService.getBookById(id);
            if (book != null) {
                book.setTitle(bookDetails.getTitle());
                book.setAuthor(bookDetails.getAuthor());
                book.setIsbn(bookDetails.getIsbn());
                BookEntity updatedBook = bookService.saveBook(book);
                return ResponseEntity.ok(updatedBook);
            } else {
                return ResponseEntity.notFound().build();
            }
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

//        //   Get all books with pagination
//        @GetMapping("/page")
//        public Page<BookEntity> getAllBooks(Pageable pageable) {
//            return bookService.(pageable);
//        }
//
//        //   Delete book by id
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
//            BookEntity genre = bookService.getBookById(id);
//            if (genre != null) {
//                bookService.deleteBook(id);
//                return ResponseEntity.ok().build();
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }

    }
