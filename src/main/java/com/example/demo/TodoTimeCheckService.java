package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TodoTimeCheckService {
    private final TodoTimeCheckRepository todoTimeCheckRepository;

    public List<TodoTimeCheck> findByCheckDate(LocalDate date) {
        return todoTimeCheckRepository.findByCheckDate(date);
    }
}

