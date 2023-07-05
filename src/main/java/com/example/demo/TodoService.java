package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findByDeleteDateNull();
    }

    @Transactional
    public Todo save(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return findByTitle(title)
                .map(todo -> {
                    todo.revive();
                    return todo;
                })
                .orElseGet(() -> todoRepository.save(Todo.builder().title(title).build()));
    }

    private Optional<Todo> findByTitle(String title) {
        return todoRepository.findByTitle(title);
    }

    @Transactional
    public void delete(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        todo.delete();
    }

    public Optional<Todo> findById(long id) {
        return todoRepository.findByDeleteDateNullAndId(id);
    }

    @Transactional
    public void toggleCheck(long id, LocalDate checkDate) {
        Todo todo = findById(id).orElse(null);

        if (todo == null) return;

        todo.toggleTodoTimeCheck(checkDate);
    }
}
