package com.kauan.crud.crud.api.util;

import com.kauan.crud.crud.api.domain.Dorama;

public class DoramaCreator {
    public static Dorama createDoramaToBeSaved() {
        return Dorama.builder()
                .title("Dorama")
                .releaseYear(1900)
                .score(8)
                .build();
    }

    public static Dorama createValidDorama() {
        return Dorama.builder()
                .id(1L)
                .title("Dorama")
                .releaseYear(1900)
                .score(8)
                .build();
    }

    public static Dorama createValidDoramaUpdated(){
        return Dorama.builder()
                .id(1L)
                .title("Dorama 2")
                .releaseYear(1900)
                .score(9)
                .build();
    }
}
