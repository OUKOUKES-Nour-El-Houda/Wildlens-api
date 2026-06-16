package com.wildlens.wildlesnApi.wildlensApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "profil")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name_user")
    private String nameUser;

    @Column(name="username_user")
    private String usernameUser;

    @Column(name="mail_user")
    private String mailUser;

    private String password;

    @Column(name="registration_date")
    private LocalDateTime registrationDate;

    // Getters and Setters
}
