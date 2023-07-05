package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitle(String title);

    List<Todo> findByDeleteDateNull();

    Optional<Todo> findByDeleteDateNullAndId(long id);
}
