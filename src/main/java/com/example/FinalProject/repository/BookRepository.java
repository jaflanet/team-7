package com.example.FinalProject.repository;

import com.example.FinalProject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByIsbn(String isbn);

    @Query(
            value = "select b.* " +
                    "from books b " +
                    "where b.title like :title or " +
                    "b.author like :author or ",
            nativeQuery = true
    )
    BookEntity findByTitleOrAuthor(@Param("title") String title, @Param("author") String author);

}
