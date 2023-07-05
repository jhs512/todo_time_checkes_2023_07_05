package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {
    @Bean
    public ApplicationRunner initData(TodoService todoService) {
        return args -> {
            todoService.save(Todo.builder().title("Buy milk").build());
            todoService.save(Todo.builder().title("Buy snack").build());
        };
    }
}
