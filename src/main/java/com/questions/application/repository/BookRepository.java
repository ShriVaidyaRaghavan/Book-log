package com.questions.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.questions.application.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}
