package com.merci.fitapp.handlers;

import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<String>> handleServiceException(ServiceException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception e) {
        return new ResponseEntity<>(new ApiResponse<>("An unexpected error occurred."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
