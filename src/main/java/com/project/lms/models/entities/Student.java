package com.project.lms.models.entities;

import com.project.lms.models.AccountStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String contact;

    @Column(unique = true)
    private String email;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    @CreationTimestamp
    private LocalDate createdOn;

    @UpdateTimestamp
    private LocalDate updatedOn;

    @OneToMany(mappedBy = "student") //back reference to get all books related to this user by hibernate
    private List<Book> bookList;

    @OneToMany(mappedBy = "student") //back reference to get all transactions related to this user by hibernate
    private List<Transaction> transactionList;

}
