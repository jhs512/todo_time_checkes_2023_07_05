package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findByDeleteDate(null);
    }

    public Todo save(Todo buyMilk) {
        return todoRepository.save(buyMilk);
    }

    public void delete(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        todo.setDeleteDate(LocalDateTime.now());
        todoRepository.save(todo);
    }
}
