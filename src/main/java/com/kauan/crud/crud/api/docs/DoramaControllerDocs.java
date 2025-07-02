package com.kauan.crud.crud.api.docs;

import com.kauan.crud.crud.api.domain.Dorama;
import com.kauan.crud.crud.api.dto.DoramaPostDto;
import com.kauan.crud.crud.api.dto.DoramaPutDto;
import com.kauan.crud.crud.api.exception.BadRequestException;
import com.kauan.crud.crud.api.exception.InternalServerErrorException;
import com.kauan.crud.crud.api.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Dorama API", description = "Dorama related endpoints")
public interface DoramaControllerDocs {

    @Operation(summary = "Get all doramas",
            description = "Return a list type of dorama with all doramas in the system")
    @GetMapping("/all")
    ResponseEntity<List<Dorama>> listAll();

    @Operation(summary = "Get all doramas inside a page object",
            description = "Receive the pageable specification in the request")
    @GetMapping
    ResponseEntity<Page<Dorama>> listAllPage(Pageable pageable);

    @Operation(summary = "Get dorama by searched title",
            description = "Return a list type of dorama within the searched dorama or related doramas with the same title")
    @GetMapping("/find")
    ResponseEntity<List<Dorama>> findByTitle(@RequestParam String title);

    @Operation(summary = "Get dorama by Id",
            responses = {
                    @ApiResponse(description = "Get doramas by its id",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Dorama.class))),
                    @ApiResponse(description = "Dorama not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = NotFoundException.class)))
            })
    @GetMapping("/{id}")
    ResponseEntity<Dorama> findById(@PathVariable long id);

    @Operation(summary = "Create a dorama and save in the data base",
            responses = {
                    @ApiResponse(description = "Internal Server Error when saving invalid dorama " +
                            "with release year less than 1900 or score out of range 0 to 10",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = InternalServerErrorException.class)))
            })
    @PostMapping
    ResponseEntity<Dorama> save(@RequestBody @Valid DoramaPostDto doramaPostDto);

    @Operation(summary = "Update a dorama saved in the data base",
            responses = {
                    @ApiResponse(description = "Update successful",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Dorama.class))),
                    @ApiResponse(description = "Bad Request when updating non-existent dorama",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = BadRequestException.class))),
                    @ApiResponse(description = "Internal Server Error when saving invalid dorama " +
                            "with release year less than 1900 or score out of range 0 to 10",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = InternalServerErrorException.class)))
            })
    @PutMapping
    ResponseEntity<Dorama> replace(@RequestBody @Valid DoramaPutDto doramaPutDto);

    @Operation(summary = "Delete dorama by Id",
            responses = {
                    @ApiResponse(description = "Delete successful",
                            responseCode = "204"),
                    @ApiResponse(description = "Bad Request when trying to delete a non-existent dorama",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = BadRequestException.class)))
            })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable long id);
}