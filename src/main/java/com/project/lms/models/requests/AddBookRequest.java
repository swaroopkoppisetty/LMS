package com.project.lms.models.requests;

import com.project.lms.models.Genre;
import com.project.lms.models.entities.Book;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
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
    private Double cost;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Valid
    private AuthorDetails authorDetails;

    public Book toBook(){
        return Book.builder()
                .name(name)
                .cost(cost)
                .genre(genre)
                .author(authorDetails.toAuthor())
                .build();
    }




}
