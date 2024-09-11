package com.example.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Setter
@Getter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patron_id", referencedColumnName = "id")
    private PatronEntity patron;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;

    @Column(name = "borrow_date", nullable = false)
    @JsonProperty("borrow_date")
    private LocalDate borrow_date;

    @Column(name = "due_date", nullable = false)
    @JsonProperty("due_date")
    private LocalDate due_date;

    @Column(name = "return_date")
    @JsonProperty("return_date")
    private LocalDate return_date;

    @Column(name = "fine")
    @JsonProperty("fine")
    private Integer fine;
}
