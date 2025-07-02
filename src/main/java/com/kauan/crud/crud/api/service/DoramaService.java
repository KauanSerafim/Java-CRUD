package com.kauan.crud.crud.api.service;

import com.kauan.crud.crud.api.domain.Dorama;
import com.kauan.crud.crud.api.dto.DoramaPostDto;
import com.kauan.crud.crud.api.dto.DoramaPutDto;
import com.kauan.crud.crud.api.exception.BadRequestException;
import com.kauan.crud.crud.api.exception.NotFoundException;
import com.kauan.crud.crud.api.mapper.DoramaMapper;
import com.kauan.crud.crud.api.repository.DoramaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoramaService {
    private final DoramaRepository doramaRepository;
    private final DoramaMapper doramaMapper;

    public List<Dorama> listAll() {
        return doramaRepository.findAll();
    }

    public Page<Dorama> listAllPage(Pageable pageable) {
        return doramaRepository.findAll(pageable);
    }

    public List<Dorama> findByTitle(String title) {
        return doramaRepository.findByTitleContainingIgnoreCase(title);
    }

    public Dorama findByIdOrThrowNotFoundException(long id) {
        return doramaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dorama Not Found."));
    }

    @Transactional
    public Dorama save(DoramaPostDto doramaPostDto) {
        return doramaRepository.save(doramaMapper.toDorama(doramaPostDto));
    }

    @Transactional
    public Dorama replace(DoramaPutDto doramaPutDto) {
        Dorama doramaSaved = findByIdOrThrowBadRequestException(doramaPutDto.getId());
        Dorama doramaToBeSave = doramaMapper.toDorama(doramaPutDto);
        doramaToBeSave.setId(doramaSaved.getId());
        return doramaRepository.save(doramaToBeSave);
    }

    public void delete(long id) {
        doramaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    private Dorama findByIdOrThrowBadRequestException(long id) {
        return doramaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Dorama Not Found."));
    }
}
