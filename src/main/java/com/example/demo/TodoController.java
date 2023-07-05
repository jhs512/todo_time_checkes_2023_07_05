package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/")
    public String showList(Model model) {
        List<Todo> todos = todoService.findAll();

        model.addAttribute("todos", todos);

        return "list";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        todoService.delete(id);

        return "redirect:/";
    }

    @PostMapping("/create")
    public String create(String title) {
        todoService.save(title);

        return "redirect:/";
    }

    @PatchMapping("/toggleCheck/{id}")
    public String toggleCheck(@PathVariable long id) {
        todoService.toggleCheck(id, LocalDate.now());

        return "redirect:/";
    }
}
