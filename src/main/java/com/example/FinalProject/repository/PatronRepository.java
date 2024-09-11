package com.example.FinalProject.repository;

import com.example.FinalProject.entity.PatronEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface PatronRepository extends JpaRepository<PatronEntity, Long> {
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM patrons " +
                    "WHERE id = :id " +
                    "AND NOT EXISTS ( " +
                    "    SELECT 1 " +
                    "    FROM transactions " +
                    "    WHERE transactions.patron_id = patrons.id " +
                    "    AND transactions.return_date IS NULL " +
                    ");",
            nativeQuery = true
    )
    int deletePatron(@Param("id") Long id);

    @Query(
            value = "SELECT t.fine, " +
                    "t.return_date, " +
                    "t.borrow_date, " +
                    "b.title " +
                    "FROM patrons p " +
                    "JOIN Transactions t ON p.id = t.patron_id " +
                    "JOIN books b ON t.book_id = b.id " +
                    "WHERE p.id = :id " +
                    "AND t.return_date IS NOT NULL " +
                    "ORDER BY t.borrow_date DESC",
            nativeQuery = true
    )
    List<Map<String, Object>> getPatronBorrowingHistory(@Param("id") Long id);

    @Query(
            value = "SELECT b.title, " +
                    "t.due_date, " +
                    "t.borrow_date " +
                    "FROM patrons p " +
                    "JOIN Transactions t ON p.id = t.patron_id " +
                    "JOIN books b ON t.book_id = b.id " +
                    "WHERE p.id = :id " +
                    "AND t.return_date IS NULL " +
                    "ORDER BY t.borrow_date DESC",
            nativeQuery = true
    )
    List<Map<String, Object>> getPatronCurrentBorrowing(@Param("id") Long id);

}
