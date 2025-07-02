package com.kauan.crud.crud.api.mapper;

import com.kauan.crud.crud.api.domain.Dorama;
import com.kauan.crud.crud.api.dto.DoramaPostDto;
import com.kauan.crud.crud.api.dto.DoramaPutDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoramaMapper {
    Dorama toDorama(DoramaPostDto doramaPostDto);

    Dorama toDorama(DoramaPutDto doramaPutDto);
}
