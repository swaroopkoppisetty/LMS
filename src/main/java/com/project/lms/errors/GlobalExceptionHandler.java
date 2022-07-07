package com.project.lms.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleMethodArgumentNotValidException(WebRequest request, MethodArgumentNotValidException e){
        StringBuilder messages = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fieldError ->{
                    messages.append(fieldError.getField() + " " +fieldError.getDefaultMessage() + " ");
                });
        return ErrorMessage.builder()
                .date(LocalDate.now())
                .description(request.getDescription(false))
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .message(messages.toString())
                .build();
    }
}
