package com.kauan.crud.crud.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dorama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title", nullable = false, length = 100)
    @NotEmpty(message = "The title cannot be empty or null")
    String title;

    @Column(name = "release_year", nullable = false)
    @Min(value = 1900, message = "The release year must be 1900 or later")
    @Max(value = 9999, message = "The release year must be a valid 4-digit number")
    int releaseYear;

    @Column(name = "score", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "10.0", inclusive = true)
    double score;
}
