package com.kauan.crud.crud.api.util;

import com.kauan.crud.crud.api.dto.DoramaPutDto;

public class DoramaPutDtoCreator {
    public static DoramaPutDto createDoramaPutDto() {
        return DoramaPutDto.builder()
                .id(1L)
                .title("Dorama 2")
                .releaseYear(1900)
                .score(9)
                .build();
    }
}
