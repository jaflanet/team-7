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


@Repository
public interface PatronRepository extends JpaRepository<PatronEntity, Long> {
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM Patrons " +
                    "WHERE id = :id " +
                    "AND NOT EXISTS ( " +
                    "    SELECT 1 " +
                    "    FROM Transactions " +
                    "    WHERE Transactions.patron_id = Patrons.id " +
                    "    AND Transactions.return_date IS NULL " +
                    ");",
            nativeQuery = true
    )
    int deletePatron(@Param("id") Long id);

}
