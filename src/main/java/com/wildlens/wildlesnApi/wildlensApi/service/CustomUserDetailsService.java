package com.wildlens.wildlesnApi.wildlensApi.service;

import com.wildlens.wildlesnApi.wildlensApi.model.User;
import com.wildlens.wildlesnApi.wildlensApi.repository.UserRepository;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;

@Slf4j
@Service
@Builder
@Validated
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String mailUser) throws UsernameNotFoundException {
        User user = userRepository.findByMailUser(mailUser)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return new org.springframework.security.core.userdetails.User(
                user.getMailUser(),
                user.getPassword(),
                Collections.emptyList() // aucun rôle pour l’instant
        );
    }
}