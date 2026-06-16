package com.wildlens.wildlesnApi.wildlensApi.controller.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ImageDtoIn {
    private String latitude;
    private String longitude;
    private Long profilId;
    private LocalDateTime date;
}
