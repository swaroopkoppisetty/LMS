package com.project.lms.services;

import com.project.lms.errors.TransactionException;
import com.project.lms.models.TransactionType;
import com.project.lms.models.entities.Book;
import com.project.lms.models.entities.Student;
import com.project.lms.models.entities.Transaction;
import com.project.lms.models.requests.BookOperationType;
import com.project.lms.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final StudentService studentService;
    private final BookService bookService;
    private final TransactionRepository transactionRepository;

    @Value("${book.days}")
    private int noOfDays;

    public TransactionService(StudentService studentService, BookService bookService, TransactionRepository transactionRepository) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.transactionRepository = transactionRepository;
    }


    /*
        check student is valid or not
        check book is valid or not
        if valid check its available or not
        create a transaction
        map book to student
     */

    @Transactional
    public String issueTxn(int studentId, int bookId) throws TransactionException {

        Student student = studentService.getStudentById(studentId);
        if(student == null){
            throw new TransactionException("Student not present with id: "+ studentId);
        }

        List<Book> books = bookService.find(BookOperationType.Book_ID,String.valueOf(bookId));

        if(books.size() == 0){
            throw  new TransactionException("Book not present with id: " +bookId);
        }

        Book book = books.get(0);
        if(book.getStudent() != null){
            throw new TransactionException("Sorry Book was already assigned");
        }

        Transaction transaction = Transaction.builder()
                                    .transactionType(TransactionType.ISSUE)
                                    .externalTxnId(UUID.randomUUID().toString())
                                    .payment(book.getCost())
                                    .book(book)
                                    .student(student)
                                    .build();

        transactionRepository.save(transaction);

        book.setStudent(student);
        bookService.updateBook(book);

        return transaction.getExternalTxnId();
    }

    /*
        check student is valid or not
        check particular book issued to this student or not
        calculate fine
        create transaction
        make book available
     */
    public String returnTxn(int studentId, int bookId) throws TransactionException {
        Student student = studentService.getStudentById(studentId);
        if(student == null){
            throw new TransactionException("Student not present with id: "+ studentId);
        }

        List<Book> books = bookService.find(BookOperationType.Book_ID,String.valueOf(bookId));

        if(books.size() == 0){
            throw  new TransactionException("Book not present with id: " +bookId);
        }
        Book book = books.get(0);

        if(book.getStudent().getId() != studentId){
            throw new TransactionException("Book not issued to this student");
        }

        Transaction issueTxn = transactionRepository.findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(book,student,TransactionType.ISSUE);

        Transaction returnTxn = Transaction.builder()
                                .externalTxnId(UUID.randomUUID().toString())
                                .transactionType(TransactionType.RETURN)
                                .payment(calculateFine(issueTxn))
                                .student(student)
                                .book(book)
                                .build();
        transactionRepository.save(returnTxn);
        book.setStudent(null);
        bookService.updateBook(book);
        return  returnTxn.getExternalTxnId();
    }

    private double calculateFine(Transaction transaction){
        LocalDate issuedDate = transaction.getTransactionDate();
        LocalDate returnDate = LocalDate.now();
        int dueDays = issuedDate.getDayOfMonth() - returnDate.getDayOfMonth();
        if(dueDays > noOfDays){
            return (dueDays - noOfDays) * 1.0;
        }
        return 0.0;
    }
}
