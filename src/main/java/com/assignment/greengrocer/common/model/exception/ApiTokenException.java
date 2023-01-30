package com.assignment.greengrocer.common.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ApiTokenException extends RuntimeException {

    @Getter
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ApiTokenException(String message) {
        super(message);
    }
}
