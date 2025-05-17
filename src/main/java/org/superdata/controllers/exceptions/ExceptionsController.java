package org.superdata.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.superdata.exceptions.InvalidDataException;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModel> tryRuntimeException(RuntimeException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorModel(request.getServletPath(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorModel> tryInvalidDataException(InvalidDataException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorModel(request.getServletPath(), e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
