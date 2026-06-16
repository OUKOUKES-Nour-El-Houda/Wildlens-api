package com.wildlens.wildlesnApi.wildlensApi.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="info_species")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoSpecies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String especes;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name="nom_latin")
    private String nomLatin;
    private String famille;
    private String taille;
    private String region;
    private String habitat;
    @Column(columnDefinition = "TEXT")
    private String funfact;


}
