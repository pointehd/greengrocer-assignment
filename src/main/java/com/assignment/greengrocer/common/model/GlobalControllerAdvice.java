package com.assignment.greengrocer.common.model;

import com.assignment.greengrocer.common.model.enums.ResponseType;
import com.assignment.greengrocer.common.model.exception.ApiErrorException;
import com.assignment.greengrocer.common.model.exception.ApiTokenException;
import com.assignment.greengrocer.common.model.out.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<Response<String>> apiErrorHandler(ApiErrorException e) {
        return ResponseEntity.status(e.getStatus())
            .body(Response.error(ResponseType.EXTERNAL_API_ERROR, e.getMessage()));
    }

    @ExceptionHandler(ApiTokenException.class)
    public ResponseEntity<Response<String>> apiErrorHandler(ApiTokenException e) {
        return ResponseEntity.status(e.getStatus())
            .body(Response.error(ResponseType.EXTERNAL_API_ERROR, e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<String>> apiErrorHandler(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Response.error(ResponseType.USER_REQUEST_ERROR, e.getMessage()));
    }

}
