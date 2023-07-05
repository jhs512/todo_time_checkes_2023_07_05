package com.example.demo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalDateTime deleteDate;

    @Builder.Default
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TodoTimeCheck> timeCheckes = new ArrayList<>();

    public void revive() {
        deleteDate = null;
    }

    public void delete() {
        deleteDate = LocalDateTime.now();
    }

    public void removeTodoTimeCheck(TodoTimeCheck todoTimeCheck) {
        timeCheckes.remove(todoTimeCheck);
    }

    public TodoTimeCheck addTodoTimeCheck(LocalDate now) {
        return TodoTimeCheck.builder()
                .todo(this)
                .checkDate(now)
                .build();
    }

    public void toggleTodoTimeCheck(LocalDate checkDate) {
        TodoTimeCheck todoTimeCheck = timeCheckes.stream()
                .filter(e -> e.getCheckDate().equals(checkDate))
                .findFirst()
                .orElse(null);

        if (todoTimeCheck == null) {
            timeCheckes.add(
                    TodoTimeCheck
                            .builder()
                            .todo(this)
                            .checkDate(checkDate)
                            .build()
            );
        } else {
            removeTodoTimeCheck(todoTimeCheck);
        }
    }

    public boolean isCheckedWhen(String checkDate) {
        return timeCheckes.stream()
                .anyMatch(e -> e.getCheckDate().toString().equals(checkDate));
    }
}
