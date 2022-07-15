package com.project.lms.services;

import com.project.lms.models.entities.Author;
import com.project.lms.models.entities.Book;
import com.project.lms.models.requests.AddBookRequest;
import com.project.lms.models.requests.BookOperationType;
import com.project.lms.models.responses.CommonDTO;
import com.project.lms.repos.AuthorRepository;
import com.project.lms.repos.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public CommonDTO addBook(AddBookRequest addBookRequest){
        CommonDTO commonDTO = new CommonDTO();
        Book book = addBookRequest.toBook();
        Author author = book.getAuthor();

        //find author exists or not
        Author authorDB = authorRepository.findByEmail(author.getEmail()).orElse(null);

        //if not find add author to db
        if(authorDB == null){
            authorDB = authorRepository.save(author);
        }

        //set author to the book

        book.setAuthor(authorDB);
        bookRepository.save(book);
        commonDTO.setStatusCode(HttpStatus.OK.toString());
        commonDTO.setMessage("Student added successfully");
        return commonDTO;

    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> find(BookOperationType operationType, String value){

        switch (operationType){
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthorName(value);
            case GENRE:
                return bookRepository.findByGenre(value);
            case Book_ID:
                return (List<Book>) bookRepository.findAllById(Collections.singletonList(Integer.parseInt(value)));
            default:
                return new ArrayList<>();

        }
    }
}
