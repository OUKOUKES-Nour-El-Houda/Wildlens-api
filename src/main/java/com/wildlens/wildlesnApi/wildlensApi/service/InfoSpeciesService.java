package com.wildlens.wildlesnApi.wildlensApi.service;

import com.wildlens.wildlesnApi.wildlensApi.controller.in.InfoSpeciesDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.InfoSpeciesDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.model.InfoSpecies;
import com.wildlens.wildlesnApi.wildlensApi.repository.InfoSpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;


@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class InfoSpeciesService {

    private final InfoSpeciesRepository infoSpeciesRepository;

    public List<InfoSpeciesDtoOut> getAllSpecies() {
        return infoSpeciesRepository.findAll()
                .stream()
                .map(InfoSpeciesDtoOut::new).toList();
    }

    public InfoSpeciesDtoOut getSpeciesById(Long infoSpeciesId){
        return infoSpeciesRepository.findById(infoSpeciesId)
                .map(InfoSpeciesDtoOut::new)
                .orElseThrow(() -> new EntityNotFoundException("Species not found with id: " + infoSpeciesId));
    }


    public InfoSpeciesDtoOut createSpecies(InfoSpeciesDtoIn infoSpeciesDtoIn) {
        InfoSpecies infoSpecies = InfoSpecies.builder()
                .especes(infoSpeciesDtoIn.getEspeces())
                .description(infoSpeciesDtoIn.getDescription())
                .nomLatin(infoSpeciesDtoIn.getNomLatin())
                .famille(infoSpeciesDtoIn.getFamille())
                .taille(infoSpeciesDtoIn.getTaille())
                .region(infoSpeciesDtoIn.getRegion())
                .habitat(infoSpeciesDtoIn.getHabitat())
                .funfact(infoSpeciesDtoIn.getFunfact())
                .build();
        infoSpecies = infoSpeciesRepository.save(infoSpecies);
        return new InfoSpeciesDtoOut(infoSpecies);

    }
    public void deleteInfoSpecies(Long infoSpeciesId){
        InfoSpecies infoSpecies = infoSpeciesRepository.findById(infoSpeciesId)
                .orElseThrow(() -> new ResponseStatusException(NO_CONTENT, "Species for id" + infoSpeciesId + "already deleted or never creates"));
        infoSpeciesRepository.delete(infoSpecies);
    }



}




