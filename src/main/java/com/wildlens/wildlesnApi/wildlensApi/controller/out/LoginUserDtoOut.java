package com.wildlens.wildlesnApi.wildlensApi.controller.out;

import com.wildlens.wildlesnApi.wildlensApi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDtoOut {
    private String token;

}
