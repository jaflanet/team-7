package com.example.FinalProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class dtoBorrow {
    @JsonProperty("patron_id")
    private long patron_id;

    @JsonProperty("book_id")
    private long book_id;
}
