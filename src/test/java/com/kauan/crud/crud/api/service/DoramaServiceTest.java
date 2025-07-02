package com.kauan.crud.crud.api.service;

import com.kauan.crud.crud.api.domain.Dorama;
import com.kauan.crud.crud.api.dto.DoramaPostDto;
import com.kauan.crud.crud.api.dto.DoramaPutDto;
import com.kauan.crud.crud.api.mapper.DoramaMapper;
import com.kauan.crud.crud.api.repository.DoramaRepository;
import com.kauan.crud.crud.api.util.DateUtil;
import com.kauan.crud.crud.api.util.DoramaCreator;
import com.kauan.crud.crud.api.util.DoramaPostDtoCreator;
import com.kauan.crud.crud.api.util.DoramaPutDtoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DisplayName("Test for Dorama Service")
@ExtendWith(SpringExtension.class)
class DoramaServiceTest {
    @InjectMocks
    private DoramaService doramaService;
    @Mock
    private DoramaRepository doramaRepositoryMock;
    @Mock
    private DateUtil dateUtilMock;
    @Mock
    private DoramaMapper doramaMapperMock;

    @BeforeEach
    void setUp() {
        dateUtilMock.formatDateTimeToDataBaseStyle(LocalDateTime.now());

        BDDMockito.when(doramaMapperMock.toDorama(ArgumentMatchers.any(DoramaPostDto.class)))
                .thenReturn(DoramaCreator.createValidDorama());

        BDDMockito.when(doramaMapperMock.toDorama(ArgumentMatchers.any(DoramaPutDto.class)))
                .thenReturn(DoramaCreator.createValidDoramaUpdated());


        PageImpl<Dorama> doramaPage = new PageImpl<>(List.of(DoramaCreator.createValidDorama()));
        BDDMockito.when(doramaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(doramaPage);

        BDDMockito.when(doramaRepositoryMock.findAll())
                .thenReturn(List.of(DoramaCreator.createValidDorama()));

        BDDMockito.when(doramaRepositoryMock.findByTitleContainingIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(List.of(DoramaCreator.createValidDorama()));

        BDDMockito.when(doramaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(DoramaCreator.createValidDorama()));

        BDDMockito.when(doramaRepositoryMock.save(ArgumentMatchers.any(Dorama.class)))
                .thenReturn(DoramaCreator.createValidDorama());

        BDDMockito.doNothing().when(doramaRepositoryMock).delete(ArgumentMatchers.any(Dorama.class));
    }

    @Test
    @DisplayName("List returns List of Dorama inside a Page Object when successful")
    void list_ReturnsListOfDoramaInsidePageObject_WhenSuccessful() {
        Dorama dorama = DoramaCreator.createValidDorama();
        Page<Dorama> doramaPage = doramaService.listAllPage(PageRequest.of(0, 1));

        Assertions.assertThat(doramaPage).isNotNull();
        Assertions.assertThat(doramaPage.toList()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(doramaPage).contains(dorama);
    }

    @Test
    @DisplayName("List returns List of Doramas when successful")
    void list_ReturnsListOfDoramas_WhenSuccessful() {
        Dorama dorama = DoramaCreator.createValidDorama();
        List<Dorama> doramas = doramaService.listAll();

        Assertions.assertThat(doramas).isNotNull().isNotEmpty();
        Assertions.assertThat(doramas).contains(dorama);
    }

    @Test
    @DisplayName("List returns List of Dorama with the searched title when successful")
    void list_ReturnsListOfDoramaWithTheSearchedTitle_WhenSuccessful() {
        String expectedDoramaTitle = DoramaCreator.createValidDorama().getTitle();
        List<Dorama> doramas = doramaService.findByTitle("Dorama");

        Assertions.assertThat(doramas).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(doramas).hasExactlyElementsOfTypes(Dorama.class);
        Assertions.assertThat(doramas.get(0).getTitle()).isEqualTo(expectedDoramaTitle);
    }

    @Test
    @DisplayName("Find by Id returns Dorama when successful")
    void findById_ReturnsDorama_WhenSuccessful() {
        Long expectedId = DoramaCreator.createValidDorama().getId();
        Dorama dorama = doramaService.findByIdOrThrowNotFoundException(1);

        Assertions.assertThat(dorama).isNotNull();
        Assertions.assertThat(dorama.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Save persists Dorama when successful")
    void save_ReturnsDorama_WhenSuccessful() {
        DoramaPostDto doramaPostDto = DoramaPostDtoCreator.createDoramaPostDto();
        Dorama dorama = doramaService.save(doramaPostDto);

        Assertions.assertThat(dorama).isNotNull();
        Assertions.assertThat(dorama.getId()).isNotNull();
        Assertions.assertThat(dorama.getTitle()).isEqualTo("Dorama");
    }

    @Test
    @DisplayName("Update replaces Dorama when successful")
    void update_ReplacesDorama_WhenSuccessful() {
        Assertions.assertThatCode(() -> doramaService.replace(DoramaPutDtoCreator.createDoramaPutDto()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete removes Dorama when successful")
    void delete_RemovesDorama_WhenSuccessful() {
        Assertions.assertThatCode(() -> doramaService.delete(1L))
                .doesNotThrowAnyException();

        BDDMockito.when(doramaRepositoryMock.findById(1L))
                .thenReturn(Optional.empty());

        Optional<Dorama> doramaAfterDelete = doramaRepositoryMock.findById(1L);
        Assertions.assertThat(doramaAfterDelete).isEmpty();
    }
}