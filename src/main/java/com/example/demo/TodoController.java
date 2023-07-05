package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/history")
    public String showHistory(Model model, String dateStr) {
        LocalDate date = dateStr == null ? LocalDate.now() : LocalDate.parse(dateStr);
        List<Todo> history = todoService.findHistory(date);

        model.addAttribute("history", history);

        return "history";
    }

    @GetMapping("/")
    public String showList(Model model) {
        String today = LocalDate.now().toString();
        List<Todo> todos = todoService.findAll();

        model.addAttribute("today", today);
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
