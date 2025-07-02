package com.kauan.crud.crud.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoramaPostDto {

    @NotEmpty(message = "The title cannot be empty or null")
    public String title;

    @NotNull(message = "The release year cannot be null")
    public int releaseYear;

    @NotNull(message = "The score cannot be null")
    public double score;
}
