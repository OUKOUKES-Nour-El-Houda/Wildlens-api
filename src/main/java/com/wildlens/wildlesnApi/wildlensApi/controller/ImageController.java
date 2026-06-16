package com.wildlens.wildlesnApi.wildlensApi.controller;

import com.wildlens.wildlesnApi.wildlensApi.controller.in.ImageDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.ImageDtoOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wildlens.wildlesnApi.wildlensApi.service.ImageService;
import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/images")
    @Operation(summary = "Endpoint to accept an image from the front", description = "Endpoint to post image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "image created successfully",
                    content = {@Content(schema= @Schema(implementation = ImageDtoOut.class))}),
            @ApiResponse(responseCode = "204", description = "No components type found", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<ImageDtoOut> uploadImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("profilId") Long profilId
    ) {

        ImageDtoIn dtoIn =ImageDtoIn.builder()
                .latitude(latitude)
                .longitude(longitude)
                .profilId(profilId)
                .date(LocalDateTime.now())
                .build();

        ImageDtoOut result = imageService.processImage(image, dtoIn);
        return ResponseEntity.ok(result);
    }
}
