package com.kauan.crud.crud.api.repository;

import com.kauan.crud.crud.api.domain.Dorama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoramaRepository extends JpaRepository<Dorama, Long> {
    List<Dorama> findByTitleContainingIgnoreCase(String title);
}
