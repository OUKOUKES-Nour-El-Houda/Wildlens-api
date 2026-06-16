package com.wildlens.wildlesnApi.wildlensApi.controller.in;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUserDtoIn {

    private String mailUser;
    private String password;

}
