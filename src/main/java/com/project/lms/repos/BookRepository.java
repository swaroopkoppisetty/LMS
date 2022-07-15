package com.project.lms.repos;

import com.project.lms.models.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {


    @Query("select b from Book b where lower(b.name) like lower(concat('%',?1,'%'))")
    List<Book> findByName(String name);

    //select * from book where author_id in(select id from author where name= :name');
    //select b.cost, b.id, b.genre,b.name,a.name from book b inner join author a on b.author_id = a.id where a.name = 'Rolex';
    @Query("select b from Book b where b.author in(select id from Author a where lower(a.name) = lower(?1))")
    List<Book> findByAuthorName(String name);

    @Query("select b from Book b where lower(b.genre) = lower(?1)")
    List<Book> findByGenre(String genre);
}
