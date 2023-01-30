package com.assignment.greengrocer.common.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ApiErrorException extends RuntimeException {

    @Getter
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public ApiErrorException(String message) {
        super(message);
    }
}
