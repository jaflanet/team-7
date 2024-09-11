package com.example.FinalProject.repository;

import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
//    List<TransactionEntity> findByPatronId(Long patronId);
//    long countByPatronAndReturn_dateIsNull(PatronEntity patron);
}
