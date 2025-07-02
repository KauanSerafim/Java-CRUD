package com.kauan.crud.crud.api.controller;

import com.kauan.crud.crud.api.docs.DoramaControllerDocs;
import com.kauan.crud.crud.api.domain.Dorama;
import com.kauan.crud.crud.api.dto.DoramaPostDto;
import com.kauan.crud.crud.api.dto.DoramaPutDto;
import com.kauan.crud.crud.api.service.DoramaService;
import com.kauan.crud.crud.api.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "doramas")
@RequiredArgsConstructor
@Log4j2
public class DoramaController implements DoramaControllerDocs {
    private final DoramaService doramaService;
    private final DateUtil dateUtil;


    @Override
    public ResponseEntity<List<Dorama>> listAll() {
        log.info(dateUtil.formatDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(doramaService.listAll());
    }

    @Override
    public ResponseEntity<Page<Dorama>> listAllPage(Pageable pageable) {
        log.info(dateUtil.formatDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(doramaService.listAllPage(pageable));
    }

    @Override
    public ResponseEntity<List<Dorama>> findByTitle(@RequestParam String title) {
        log.info(dateUtil.formatDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(doramaService.findByTitle(title));
    }

    @Override
    public ResponseEntity<Dorama> findById(@PathVariable long id) {
        log.info(dateUtil.formatDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(doramaService.findByIdOrThrowNotFoundException(id));
    }

    @Override
    public ResponseEntity<Dorama> save(@RequestBody @Valid DoramaPostDto doramaPostDto) {
        log.info(dateUtil.formatDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(doramaService.save(doramaPostDto));
    }

    @Override
    public ResponseEntity<Dorama> replace(@RequestBody @Valid DoramaPutDto doramaPutDto) {
        log.info(dateUtil.formatDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(doramaService.replace(doramaPutDto));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info(dateUtil.formatDateTimeToDataBaseStyle(LocalDateTime.now()));
        doramaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
