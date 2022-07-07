package com.project.lms.models.requests;

import com.project.lms.models.Genre;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {

    @NotBlank
    private String name;

    @NotNull
    private double cost;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;



}
