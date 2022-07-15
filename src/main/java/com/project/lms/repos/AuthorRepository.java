package com.project.lms.repos;

import com.project.lms.models.entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Optional<Author> findByEmail(String email);
}
