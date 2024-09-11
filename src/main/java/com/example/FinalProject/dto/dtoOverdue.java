package com.example.FinalProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class dtoOverdue {
    @JsonProperty("title")
    private String title;

    @JsonProperty("name")
    private String name;

    @JsonProperty("due_date")
    private LocalDate due_date;

    @JsonProperty("return_date")
    private LocalDate return_date;

    @JsonProperty("days")
    private Integer days;

    @JsonProperty("fine")
    private int fine;
}
