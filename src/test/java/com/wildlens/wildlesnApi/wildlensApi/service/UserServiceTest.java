package com.wildlens.wildlesnApi.wildlensApi.service;

import com.wildlens.wildlesnApi.wildlensApi.controller.in.RegisterUserDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.LoginUserDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.UserDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.model.User;
import com.wildlens.wildlesnApi.wildlensApi.repository.UserRepository;
import com.wildlens.wildlesnApi.wildlensApi.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldCreateUserSuccessfully() {
        RegisterUserDtoIn dto = new RegisterUserDtoIn("Alice", "alice99", "alice@wildlens.com", "pass123", LocalDateTime.now());

        when(userRepository.existsByMailUser("alice@wildlens.com")).thenReturn(false);
        when(passwordEncoder.encode("pass123")).thenReturn("hashedPass");
        User saved = User.builder()
                .id(1L).nameUser("Alice").usernameUser("alice99")
                .mailUser("alice@wildlens.com").password("hashedPass")
                .registrationDate(LocalDateTime.now()).build();
        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserDtoOut result = userService.createUser(dto);

        assertThat(result).isNotNull();
        assertThat(result.getMailUser()).isEqualTo("alice@wildlens.com");
    }

    @Test
    void createUser_shouldThrowWhenEmailAlreadyExists() {
        RegisterUserDtoIn dto = new RegisterUserDtoIn("Bob", "bob42", "bob@wildlens.com", "pass", LocalDateTime.now());
        when(userRepository.existsByMailUser("bob@wildlens.com")).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("déjà utilisé");
    }

    @Test
    void authenticate_shouldReturnTokenWhenCredentialsAreValid() {
        User user = User.builder()
                .mailUser("alice@wildlens.com").password("hashedPass").build();

        when(userRepository.findByMailUser("alice@wildlens.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("pass123", "hashedPass")).thenReturn(true);
        when(jwtUtil.generateToken("alice@wildlens.com")).thenReturn("jwt-token-mock");

        LoginUserDtoOut result = userService.authenticate("alice@wildlens.com", "pass123");

        assertThat(result).isNotNull();
        assertThat(result.getToken()).isEqualTo("jwt-token-mock");
    }

    @Test
    void authenticate_shouldThrowWhenUserNotFound() {
        when(userRepository.findByMailUser("unknown@wildlens.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.authenticate("unknown@wildlens.com", "pass"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("non trouvé");
    }

    @Test
    void authenticate_shouldThrowWhenPasswordIsWrong() {
        User user = User.builder().mailUser("alice@wildlens.com").password("hashedPass").build();
        when(userRepository.findByMailUser("alice@wildlens.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpass", "hashedPass")).thenReturn(false);

        assertThatThrownBy(() -> userService.authenticate("alice@wildlens.com", "wrongpass"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Mot de passe incorrect");
    }

    @Test
    void getAllUser_shouldReturnListOfUsers() {
        User u1 = User.builder().id(1L).mailUser("a@a.com").build();
        User u2 = User.builder().id(2L).mailUser("b@b.com").build();
        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        List<UserDtoOut> result = userService.getAllUser();

        assertThat(result).hasSize(2);
    }
}
