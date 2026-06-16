package com.wildlens.wildlesnApi.wildlensApi.service;

import com.wildlens.wildlesnApi.wildlensApi.controller.in.InfoSpeciesDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.InfoSpeciesDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.model.InfoSpecies;
import com.wildlens.wildlesnApi.wildlensApi.repository.InfoSpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoSpeciesServiceTest {

    @Mock
    private InfoSpeciesRepository infoSpeciesRepository;

    @InjectMocks
    private InfoSpeciesService infoSpeciesService;

    @Test
    void getAllSpecies_shouldReturnAllSpecies() {
        InfoSpecies s1 = InfoSpecies.builder().id(1L).especes("Loup").build();
        InfoSpecies s2 = InfoSpecies.builder().id(2L).especes("Renard").build();
        when(infoSpeciesRepository.findAll()).thenReturn(List.of(s1, s2));

        List<InfoSpeciesDtoOut> result = infoSpeciesService.getAllSpecies();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getEspeces()).isEqualTo("Loup");
    }

    @Test
    void getSpeciesById_shouldReturnSpeciesWhenFound() {
        InfoSpecies species = InfoSpecies.builder().id(1L).especes("Aigle").nomLatin("Aquila chrysaetos").build();
        when(infoSpeciesRepository.findById(1L)).thenReturn(Optional.of(species));

        InfoSpeciesDtoOut result = infoSpeciesService.getSpeciesById(1L);

        assertThat(result.getEspeces()).isEqualTo("Aigle");
    }

    @Test
    void getSpeciesById_shouldThrowWhenNotFound() {
        when(infoSpeciesRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> infoSpeciesService.getSpeciesById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createSpecies_shouldSaveAndReturnSpecies() {
        InfoSpeciesDtoIn dto = new InfoSpeciesDtoIn();
        dto.setEspeces("Cerf");
        dto.setNomLatin("Cervus elaphus");
        dto.setFamille("Cervidae");
        dto.setDescription("Grand cervidé");
        dto.setTaille("1.5m");
        dto.setRegion("Europe");
        dto.setHabitat("Forêt");
        dto.setFunfact("Mue ses bois chaque année");

        InfoSpecies saved = InfoSpecies.builder().id(1L).especes("Cerf").nomLatin("Cervus elaphus").build();
        when(infoSpeciesRepository.save(any(InfoSpecies.class))).thenReturn(saved);

        InfoSpeciesDtoOut result = infoSpeciesService.createSpecies(dto);

        assertThat(result.getEspeces()).isEqualTo("Cerf");
    }
}
