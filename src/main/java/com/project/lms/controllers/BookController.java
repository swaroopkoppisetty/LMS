package com.project.lms.controllers;

import com.project.lms.models.entities.Book;
import com.project.lms.models.requests.AddBookRequest;
import com.project.lms.models.requests.AddStudentRequest;
import com.project.lms.models.requests.BookOperationType;
import com.project.lms.models.responses.CommonDTO;
import com.project.lms.repos.BookRepository;
import com.project.lms.services.BookService;
import com.project.lms.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add-book")
    public CommonDTO addStudent(@RequestBody @Valid AddBookRequest addBookRequest){
        return bookService.addBook(addBookRequest);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(@RequestParam(name = "filterType") BookOperationType operationType,
                                  @RequestParam String value){
        return bookService.find(operationType,value);
    }
}
