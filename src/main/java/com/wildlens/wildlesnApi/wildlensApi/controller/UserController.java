package com.wildlens.wildlesnApi.wildlensApi.controller;

import com.wildlens.wildlesnApi.wildlensApi.configuration.ExceptionHandlerConfiguration;
import com.wildlens.wildlesnApi.wildlensApi.controller.in.LoginUserDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.controller.in.RegisterUserDtoIn;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.LoginUserDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.controller.out.UserDtoOut;
import com.wildlens.wildlesnApi.wildlensApi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/Users")
    @Operation(summary = "Endpoint to get list of users", description = "Endpoint to get list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "users list found", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserDtoOut.class)))}),
            @ApiResponse(responseCode = "204", description = "No components type found", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Collection<UserDtoOut>> getAllUser() {
        Collection<UserDtoOut> users = userService.getAllUser();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    @Operation(summary = "Endpoint to add a new user", description = "Endpoint to add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user added", content = {@Content(schema = @Schema(implementation = UserDtoOut.class))}),
            @ApiResponse(responseCode = "406", description = "Duplicate name", content = {@Content(schema = @Schema(implementation = ExceptionHandlerConfiguration.ErrorResponse.class))})
    })
    public ResponseEntity<UserDtoOut> register(@Valid @RequestBody RegisterUserDtoIn dtoIn) {
        UserDtoOut newUser = userService.createUser(dtoIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    @Operation(summary = "Endpoint to authenticate user", description = "Endpoint for user login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login", content = {@Content(schema = @Schema(implementation = LoginUserDtoOut.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content)
    })
    public ResponseEntity<LoginUserDtoOut> login(@Valid @RequestBody LoginUserDtoIn dtoIn) {
        LoginUserDtoOut loginResponse = userService.authenticate(dtoIn.getMailUser(), dtoIn.getPassword());
        return ResponseEntity.ok(loginResponse);
    }
}
