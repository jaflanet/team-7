package com.example.FinalProject.repository;

import com.example.FinalProject.dto.dtoAvailable;
import com.example.FinalProject.dto.dtoOverdue;
import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByIsbn(String isbn);

    @Query(
            value = "select b.* " +
                    "from books b " +
                    "where b.title like :title or " +
                    "b.author like :author",
            nativeQuery = true
    )
    BookEntity findByTitleOrAuthor(@Param("title") String title, @Param("author") String author);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM books " +
                    "WHERE id = :id " +
                    "AND NOT EXISTS ( " +
                    "    SELECT 1 " +
                    "    FROM transactions " +
                    "    WHERE transactions.book_id = books.id " +
                    "    AND transactions.return_date IS NULL " +
                    ");",
            nativeQuery = true
    )
    int deleteBooksById(@Param("id") Long id);


    @Query(
            value = "SELECT b.title, b.available_copies FROM books b WHERE b.id = id;", nativeQuery = true)
    List<dtoAvailable> findBookAvailability(@Param("id") Long id);
}
