package com.wildlens.wildlesnApi.wildlensApi.controller.in;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InfoSpeciesDtoIn {
    private String especes;
    private String description;
    private String nomLatin;
    private String famille;
    private String taille;
    private String region;
    private String habitat;
    private String funfact;

}
