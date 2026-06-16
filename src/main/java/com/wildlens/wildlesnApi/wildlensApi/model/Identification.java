package com.wildlens.wildlesnApi.wildlensApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Image image;  // L'image de l'empreinte identifiée

    @ManyToOne
    private InfoSpecies species;  // Espèce identifiée

    private LocalDateTime identificationDate;  // Date de l'identification

    private Float confidence;

    // Getters and Setters
}

