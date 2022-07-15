package com.project.lms.repos;

import com.project.lms.models.TransactionType;
import com.project.lms.models.entities.Book;
import com.project.lms.models.entities.Student;
import com.project.lms.models.entities.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {


    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student, TransactionType transactionType);

}

