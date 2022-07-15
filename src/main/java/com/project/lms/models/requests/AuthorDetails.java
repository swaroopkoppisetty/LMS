package com.project.lms.models.requests;

import com.project.lms.models.entities.Author;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorDetails {

    @NotNull
    private String name;
    @NotNull
    private String email;
    private int age;

    public Author toAuthor(){
        return Author.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();
    }
}
