package com.project.lms.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class ErrorMessage {
    private String statusCode;
    private String message;
    private String description;
    private LocalDate date;
}
