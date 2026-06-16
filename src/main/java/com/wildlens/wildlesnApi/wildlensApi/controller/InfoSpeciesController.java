package com.wildlens.wildlesnApi.wildlensApi.controller;

import com.wildlens.wildlesnApi.wildlensApi.configuration.ExceptionHandlerConfiguration;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.InfoSpeciesDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.controller.in.InfoSpeciesDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.service.InfoSpeciesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InfoSpeciesController {

    private final InfoSpeciesService infoSpeciesService;

    @GetMapping(value="/infoSpecies")
    @Operation(summary = "Endpoint to get info species", description = "Endpoint to get all species")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "species found", content = {@Content(array = @ArraySchema(schema = @Schema(implementation =InfoSpeciesDtoOut.class)))}),
            @ApiResponse(responseCode = "204", description = "No components type found", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Collection<InfoSpeciesDtoOut>> getAllSpecies() {

        List<InfoSpeciesDtoOut> species = infoSpeciesService.getAllSpecies();
        if (species.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(species);
    }

    @GetMapping(value= "/{id}")
    @Operation(summary = "Endpoint to get info species by id", description = "Endpoint to get species by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "species found", content = {@Content(array = @ArraySchema(schema = @Schema(implementation =InfoSpeciesDtoOut.class)))}),
            @ApiResponse(responseCode = "204", description = "No components type found", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<InfoSpeciesDtoOut> getSpeciesById(
            @PathVariable(name = "id") Long infoSpeciesId
    ) {
        try {
            InfoSpeciesDtoOut species = infoSpeciesService.getSpeciesById(infoSpeciesId);
            return ResponseEntity.ok(species);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @ResponseStatus(CREATED)
    @PostMapping(value= "/infoSpecies")
    @Operation(summary = "Endpoint to add a new species", description = "Endpoint to add a new species")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "spieces added", content = {@Content(schema = @Schema(implementation = InfoSpeciesDtoOut.class))}),
            @ApiResponse(responseCode = "406", description = "Duplicate species name", content = {@Content(schema = @Schema(implementation = ExceptionHandlerConfiguration.ErrorResponse.class))})
    })
    public InfoSpeciesDtoOut createSpecies(@RequestBody InfoSpeciesDtoIn infoSpeciesDtoIn) {
        return infoSpeciesService.createSpecies(infoSpeciesDtoIn);
    }

    @DeleteMapping("/infoSpecies/{id}")
    @Operation(summary = "Endpoint to delete species", description = "Endpoint to delete species")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "species deleted"),
            @ApiResponse(responseCode = "404", description = "species not found", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionHandlerConfiguration.ErrorResponse.class))}),
    })
    void deleteInfoSpecies(
            @PathVariable(name = "id") Long infoSpeciesId
    ) {
        infoSpeciesService.deleteInfoSpecies(infoSpeciesId);
    }
}
