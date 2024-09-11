package com.example.FinalProject.repository;

import com.example.FinalProject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookReturn extends JpaRepository<BookEntity, Long> {
    boolean existsByIsbn(String isbn);
}
