package com.wildlens.wildlesnApi.wildlensApi.controller.out;

import com.wildlens.wildlesnApi.wildlensApi.model.InfoSpecies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoSpeciesDtoOut {
    private Long id;
    private String especes;
    private String description;
    private String nom_latin;
    private String famille;
    private String taille;
    private String region;
    private String habitat;
    private String funfact;


    public InfoSpeciesDtoOut(InfoSpecies infoSpecies ) {
        this.id = infoSpecies.getId();
        this.especes = infoSpecies.getEspeces();
        this.description = infoSpecies.getDescription();
        this.nom_latin = infoSpecies.getNomLatin();
        this.famille = infoSpecies.getFamille();
        this.taille = infoSpecies.getTaille();
        this.region= infoSpecies.getRegion();
        this.habitat = infoSpecies.getHabitat();
        this.funfact = infoSpecies.getFunfact();

    }

}
