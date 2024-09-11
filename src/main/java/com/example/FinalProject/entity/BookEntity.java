package com.example.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Setter
@Getter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(name = "author", nullable = false)
    @JsonProperty("author")
    private String author;

    @Column(name = "isbn", nullable = false)
    @JsonProperty("isbn")
    private String isbn;

    @Column(name = "quantity", nullable = false)
    @JsonProperty("quantity")
    private Integer quantity;

    @Column(name = "available_copies", nullable = false)
    @JsonProperty("available_copies")
    private Integer available_copies;

    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private LocalDate created_at;

    @Column(name = "updated_at", nullable = false)
    @JsonProperty("updated_at")
    private LocalDate updated_at;
}
