package com.wildlens.wildlesnApi.wildlensApi.service;

import com.wildlens.wildlesnApi.wildlensApi.controller.in.RegisterUserDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.LoginUserDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.UserDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.model.User;
import com.wildlens.wildlesnApi.wildlensApi.repository.UserRepository;
import com.wildlens.wildlesnApi.wildlensApi.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Builder
@Validated
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder ; // Ajout du hachage


    public List<UserDtoOut> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserDtoOut::new).toList();
    }
    // Créer un user (inscription)
    public UserDtoOut createUser(@Valid RegisterUserDtoIn registerUserDtoIn) {
        // Vérifier si email déjà pris
        if (userRepository.existsByMailUser(registerUserDtoIn.getMailUser())) {
            throw new RuntimeException("Email déjà utilisé");
        }
        // Creer user, encoder mot de passe, mettre date inscription à aujourd'hui
        User user = User.builder()
                .nameUser(registerUserDtoIn.getNameUser())
                .usernameUser(registerUserDtoIn.getUsernameUser())
                .mailUser(registerUserDtoIn.getMailUser())
                .password(passwordEncoder.encode(registerUserDtoIn.getPassword()))
                .registrationDate(LocalDateTime.now())  // on remplace la date par la date du jour
                .build();
        user = userRepository.save(user);
        return new UserDtoOut(user);
    }
    // Authentification (connexion)
    public LoginUserDtoOut authenticate(String mail_user, String password) {
        User user = userRepository.findByMailUser(mail_user)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        // Génère le token JWT
        String token = jwtUtil.generateToken(user.getMailUser());

        return new LoginUserDtoOut(token);
    }






}
