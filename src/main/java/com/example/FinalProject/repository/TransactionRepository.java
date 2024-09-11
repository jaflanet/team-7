package com.example.FinalProject.repository;

import com.example.FinalProject.dto.dtoOverdue;
import com.example.FinalProject.entity.BookEntity;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
//    List<TransactionEntity> findByPatronId(Long patronId);
//    long countByPatronAndReturn_dateIsNull(PatronEntity patron);
@Modifying
@Transactional
@Query(
        value = "SELECT " +
                "    b.title AS bookTitle, " +
                "    p.name AS patronName, " +
                "    t.due_date AS dueDate, " +
                "    t.return_date AS returnDate, " +
                "    CAST(t.return_date - t.due_date AS INTEGER) AS daysOverdue, " +
                "    CAST(t.fine * 10 * (t.return_date - t.due_date) AS DOUBLE PRECISION) AS totalFine " +
                "FROM " +
                "    books b " +
                "JOIN " +
                "    transactions t ON t.book_id = b.id " +
                "JOIN " +
                "    patrons p ON t.patron_id = p.id " +
                "WHERE " +
                "    t.return_date IS NOT NULL " +
                "    AND t.return_date > t.due_date",
        nativeQuery = true
)
List<dtoOverdue> findOverdueBooks();

}


