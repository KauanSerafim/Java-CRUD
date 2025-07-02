package com.kauan.crud.crud.api.controller;

import com.kauan.crud.crud.api.domain.Dorama;
import com.kauan.crud.crud.api.dto.DoramaPostDto;
import com.kauan.crud.crud.api.dto.DoramaPutDto;
import com.kauan.crud.crud.api.service.DoramaService;
import com.kauan.crud.crud.api.util.DateUtil;
import com.kauan.crud.crud.api.util.DoramaCreator;
import com.kauan.crud.crud.api.util.DoramaPostDtoCreator;
import com.kauan.crud.crud.api.util.DoramaPutDtoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;


@ActiveProfiles("test")
@DisplayName("Test for Dorama Controller")
@ExtendWith(SpringExtension.class)
class DoramaControllerTest {
    @InjectMocks
    private DoramaController doramaController;
    @Mock
    private DoramaService doramaServiceMock;
    @Mock
    private DateUtil dateUtilMock;

    @BeforeEach
    void setUp() {
        dateUtilMock.formatDateTimeToDataBaseStyle(LocalDateTime.now());

        PageImpl<Dorama> doramaPage = new PageImpl<>(List.of(DoramaCreator.createValidDorama()));
        BDDMockito.when(doramaServiceMock.listAllPage(ArgumentMatchers.any())).thenReturn(doramaPage);

        BDDMockito.when(doramaServiceMock.findByTitle(ArgumentMatchers.any()))
                .thenReturn(List.of(DoramaCreator.createValidDorama()));

        BDDMockito.when(doramaServiceMock.findByIdOrThrowNotFoundException(ArgumentMatchers.anyLong()))
                .thenReturn(DoramaCreator.createValidDorama());

        BDDMockito.when(doramaServiceMock.save(ArgumentMatchers.any(DoramaPostDto.class)))
                .thenReturn(DoramaCreator.createValidDorama());

        BDDMockito.when(doramaServiceMock.replace(ArgumentMatchers.any(DoramaPutDto.class)))
                .thenReturn(DoramaCreator.createValidDoramaUpdated());

        BDDMockito.doNothing().when(doramaServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("List returns List of Doramas when successful")
    void list_ReturnsListOfDoramas_WhenSuccessful() {
        Dorama dorama = DoramaCreator.createValidDorama();
        List<Dorama> doramas = List.of(dorama);

        Assertions.assertThat(doramas).isNotNull().isNotEmpty();
        Assertions.assertThat(doramas).contains(dorama);
    }

    @Test
    @DisplayName("List returns List of Dorama inside a Page Object when successful")
    void list_ReturnsListOfDoramaInsidePageObject_WhenSuccessful() {
        Dorama dorama = DoramaCreator.createValidDorama();
        Page<Dorama> doramaPage = doramaController.listAllPage(null).getBody();

        Assertions.assertThat(doramaPage).isNotNull();
        Assertions.assertThat(doramaPage.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(doramaPage).contains(dorama);
    }

    @Test
    @DisplayName("List returns List of Dorama with the searched title when successful")
    void list_ReturnsListOfDoramaWithTheSearchedTitle_WhenSuccessful() {
        String expectedDoramaTitle = DoramaCreator.createValidDorama().getTitle();
        List<Dorama> doramas = doramaController.findByTitle("Dorama").getBody();

        Assertions.assertThat(doramas).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(doramas).hasExactlyElementsOfTypes(Dorama.class);
        Assertions.assertThat(doramas.get(0).getTitle()).isEqualTo(expectedDoramaTitle);
    }

    @Test
    @DisplayName("Find by Id returns Dorama when successful")
    void findById_ReturnsDorama_WhenSuccessful() {
        Long expectedId = DoramaCreator.createValidDorama().getId();
        Dorama dorama = doramaController.findById(1).getBody();

        Assertions.assertThat(dorama).isNotNull();
        Assertions.assertThat(dorama.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Save persists Dorama when successful")
    void save_ReturnsDorama_WhenSuccessful() {
        Dorama dorama = doramaController.save(DoramaPostDtoCreator.createDoramaPostDto()).getBody();

        Assertions.assertThat(dorama).isNotNull().isEqualTo(DoramaCreator.createValidDorama());
    }

    @Test
    @DisplayName("Update replaces Dorama when successful")
    void update_ReplacesDorama_WhenSuccessful() {
        Dorama dorama = doramaController.replace(DoramaPutDtoCreator.createDoramaPutDto()).getBody();

        Assertions.assertThat(dorama).isNotNull().isEqualTo(DoramaCreator.createValidDoramaUpdated());
        Assertions.assertThatCode(() -> doramaController.replace(DoramaPutDtoCreator.createDoramaPutDto()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete removes Dorama when successful")
    void delete_RemovesDorama_WhenSuccessful() {
        Dorama savedDorama = DoramaCreator.createValidDorama();
        Void dorama = doramaController.delete(savedDorama.getId()).getBody();

        Assertions.assertThat(dorama).isNull();
    }
}