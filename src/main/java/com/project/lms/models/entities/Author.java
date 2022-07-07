package com.project.lms.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(nullable = true)
    private int age;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;
}
