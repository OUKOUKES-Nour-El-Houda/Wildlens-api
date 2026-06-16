package com.wildlens.wildlesnApi.wildlensApi.controller.out;

import com.wildlens.wildlesnApi.wildlensApi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoOut {
    private Long id;
    private String nameUser;
    private String usernameUser;
    private String mailUser;
    private LocalDateTime registrationDate;

    public UserDtoOut(User user) {
        this.id = user.getId();
        this.nameUser = user.getNameUser();
        this.usernameUser = user.getUsernameUser();
        this.mailUser = user.getMailUser();
        this.registrationDate= user.getRegistrationDate();
    }

}
