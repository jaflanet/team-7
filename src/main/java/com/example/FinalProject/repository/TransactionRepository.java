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

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
//    List<TransactionEntity> findByPatronId(Long patronId);
//    long countByPatronAndReturn_dateIsNull(PatronEntity patron);
@Modifying
@Transactional
@Query(
        value = "SELECT " +
                "    b.title AS book_title, " +
                "    p.name AS patron_name, " +
                "    t.due_date, t.return_date , " +
                "    (t.return_date - t.due_date) AS days_overdue, (t.fine * 10 * (t.return_date - t.due_date) ) " +
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
