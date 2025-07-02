package com.kauan.crud.crud.api.util;

import com.kauan.crud.crud.api.dto.DoramaPostDto;

public class DoramaPostDtoCreator {
    public static DoramaPostDto createDoramaPostDto() {
        return DoramaPostDto.builder()
                .title("Dorama")
                .releaseYear(1900)
                .score(8)
                .build();
    }
}
