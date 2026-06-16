//package com.wildlens.wildlesnApi.wildlensApi.controller.out;
//
//public class ImageDtoOut {
//    private Long imageId;
//    private String photoUrl;
//
//    public ImageDtoOut(Long imageId, String photoUrl) {
//        this.imageId = imageId;
//        this.photoUrl = photoUrl;
//    }
//}
package com.wildlens.wildlesnApi.wildlensApi.controller.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ImageDtoOut {
    private Long imageId;
    private String photoUrl;
    private String espece;
    private Float confidence;
    private LocalDateTime identificationDate;
    private String description;
    private String famille;
    private String funFact;
    private String habitat;
    private String nomLatin;
    private String region;
    private String taille;
    private Boolean success;
}
