package com.example.demo;

import jakarta.persistence.*;
import lombok.*;

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
}
