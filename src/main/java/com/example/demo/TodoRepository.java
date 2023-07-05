package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByDeleteDate(LocalDateTime deleteDate);

    Optional<Todo> findByTitle(String title);
}
