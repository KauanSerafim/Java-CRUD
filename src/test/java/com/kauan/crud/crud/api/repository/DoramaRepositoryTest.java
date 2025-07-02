package com.kauan.crud.crud.api.repository;

import com.kauan.crud.crud.api.domain.Dorama;
import com.kauan.crud.crud.api.util.DoramaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test for Dorama Repository")
class DoramaRepositoryTest {

    @Autowired
    private DoramaRepository doramaRepository;

    @Test
    @DisplayName("Save Dorama when successful")
    void save_Dorama_WhenSuccessful() {
        Dorama doramaToBeSaved = DoramaCreator.createDoramaToBeSaved();
        Dorama doramaSaved = this.doramaRepository.save(doramaToBeSaved);

        Assertions.assertThat(doramaSaved).isNotNull();
        Assertions.assertThat(doramaSaved.getId()).isNotNull();
        Assertions.assertThat(doramaSaved.getTitle()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(doramaSaved.getReleaseYear()).isNotNull();
        Assertions.assertThat(doramaSaved.getScore()).isNotNull();
        Assertions.assertThat(doramaSaved.getTitle()).isEqualTo(doramaToBeSaved.getTitle());
        Assertions.assertThat(doramaSaved.getReleaseYear()).isEqualTo(doramaToBeSaved.getReleaseYear());
        Assertions.assertThat(doramaSaved.getScore()).isEqualTo(doramaToBeSaved.getScore());
    }

    @Test
    @DisplayName("Save updates Dorama when successful")
    void save_UpdatesDorama_WhenSuccessful() {
        Dorama doramaToBeSaved = DoramaCreator.createDoramaToBeSaved();
        Dorama doramaSaved = this.doramaRepository.save(doramaToBeSaved);

        doramaSaved.setTitle("Dorama 2");
        doramaSaved.setReleaseYear(1999);
        doramaSaved.setScore(9);

        Dorama updatedDorama = this.doramaRepository.save(doramaSaved);

        Assertions.assertThat(updatedDorama).isNotNull();
        Assertions.assertThat(updatedDorama.getId()).isNotNull();
        Assertions.assertThat(updatedDorama.getTitle()).isEqualTo(doramaSaved.getTitle());
        Assertions.assertThat(updatedDorama.getReleaseYear()).isEqualTo(doramaSaved.getReleaseYear());
        Assertions.assertThat(updatedDorama.getScore()).isEqualTo(doramaSaved.getScore());
    }

    @Test
    @DisplayName("Delete removes Dorama when successful")
    void delete_RemovesDorama_WhenSuccessful() {
        Dorama doramaToBeSaved = DoramaCreator.createDoramaToBeSaved();
        Dorama doramaSaved = this.doramaRepository.save(doramaToBeSaved);

        this.doramaRepository.delete(doramaSaved);
        Optional<Dorama> doramaOptional = this.doramaRepository.findById(doramaSaved.getId());

        Assertions.assertThat(doramaOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by title returns List of dorama when successful")
    void findByTitle_ReturnsDorama_WhenSuccessful() {
        Dorama doramaToBeSaved = DoramaCreator.createDoramaToBeSaved();
        Dorama doramaSaved = this.doramaRepository.save(doramaToBeSaved);

        String title = doramaSaved.getTitle();
        List<Dorama> doramas = this.doramaRepository.findByTitleContainingIgnoreCase(title);

        Assertions.assertThat(doramas).isNotEmpty();
        Assertions.assertThat(doramas).hasSize(1);
        Assertions.assertThat(doramas).contains(doramaSaved);
    }
}
