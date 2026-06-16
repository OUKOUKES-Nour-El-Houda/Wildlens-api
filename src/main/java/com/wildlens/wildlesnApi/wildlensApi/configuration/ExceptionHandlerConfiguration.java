package com.wildlens.wildlesnApi.wildlensApi.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.annotation.Validated;
import java.util.stream.Collectors;
import jakarta.validation.ConstraintViolationException;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handle(ConstraintViolationException constraintViolationException) {
        String errorMessage = constraintViolationException.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + " " + cv.getMessage())
                .sorted()
                .collect(Collectors.joining(". ", "ConstraintViolationException occurred. ", "."));
        return new ResponseEntity<>(new ErrorResponse(NOT_ACCEPTABLE.name(), errorMessage), NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handle(ResponseStatusException responseStatusException) {

        return new ResponseEntity<>(new ErrorResponse(responseStatusException.getStatusCode().toString(), responseStatusException.getReason()), responseStatusException.getStatusCode());
    }

    @Getter
    public static class ErrorResponse {
        String error;
        String errorDescription;
        public ErrorResponse(String error, String errorDescription) {
            this.error = error;
            this.errorDescription = errorDescription;
        }
    }
}
