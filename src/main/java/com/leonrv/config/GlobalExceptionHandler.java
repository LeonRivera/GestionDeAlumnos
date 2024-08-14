package com.leonrv.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.leonrv.exceptions.StudentNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler({ StudentNotFoundException.class })
    // public ResponseEntity<?> handleStudentNotFound(StudentNotFoundException ex) {
    //     return ResponseEntity.status(500).body(ex.getMessage());
    // }


    //PATTERN MATCHING SWITCH JAVA 21 FOR GENERAL HANDLING OF CLASSES EXTENDING RUNTIME EXCEPTION
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<?> handleRuntimeException(Object exception) {

        ResponseEntity<?> res = switch (exception) {
            case StudentNotFoundException e ->
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(e.getMessage());

            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrio un error desconocido en la aplicacion");

        };
        return res;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errors);
    }
}
