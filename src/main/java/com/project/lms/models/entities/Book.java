package com.project.lms.models.entities;

import com.project.lms.models.Genre;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double cost;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @CreationTimestamp
    private LocalDate createdOn;

    @UpdateTimestamp
    private LocalDate updateOn;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;

}
