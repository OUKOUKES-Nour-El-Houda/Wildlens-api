package com.wildlens.wildlesnApi.wildlensApi.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EspecePredictionResponse {
    private String animal;
    private double confidence;
    private boolean success;

    @JsonProperty("species_info")
    private SpeciesInfo speciesInfo;

    @Data
    public static class SpeciesInfo {
        @JsonProperty("Description")
        private String description;

        @JsonProperty("Espece")
        private String espece;

        @JsonProperty("Famille")
        private String famille;

        @JsonProperty("Fun fact")
        private String funFact;

        @JsonProperty("Habitat")
        private String habitat;

        @JsonProperty("Nom latin")
        private String nomLatin;

        @JsonProperty("Région")
        private String region;

        @JsonProperty("Taille")
        private String taille;
    }
}
