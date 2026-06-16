package com.wildlens.wildlesnApi.wildlensApi.controller.in;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterUserDtoIn {
        private String nameUser;
        private String usernameUser;
        private String mailUser;
        private String password;
        private LocalDateTime registrationDate;

}
