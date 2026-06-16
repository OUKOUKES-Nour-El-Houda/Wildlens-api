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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long imageId;

    @Column(name="photo_url")
    private String photoUrl;

    private LocalDateTime date;
    private String latitude;
    private String longitude;

    @ManyToOne
    @JoinColumn(name = "profil_id")
    private User user;


    // Getters and Setters
}
