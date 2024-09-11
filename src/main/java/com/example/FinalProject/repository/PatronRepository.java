package com.example.FinalProject.repository;

import com.example.FinalProject.entity.PatronEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatronRepository extends JpaRepository<PatronEntity, Long> {
    boolean existsByEmail(String email);
}
