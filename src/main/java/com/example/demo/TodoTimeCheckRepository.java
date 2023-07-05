package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoTimeCheckRepository extends JpaRepository<TodoTimeCheck, Long> {
    List<TodoTimeCheck> findByCheckDate(LocalDate date);
}
