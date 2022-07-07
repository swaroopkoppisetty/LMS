package com.project.lms.models.entities;

import com.project.lms.models.TransactionType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    private double payment;

    @CreationTimestamp
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn
    private Student student;

    @ManyToOne
    @JoinColumn
    private Book book;
}
