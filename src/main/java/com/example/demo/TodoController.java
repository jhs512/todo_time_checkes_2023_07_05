package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        todoService.delete(id);

        return "redirect:/";
    }
}
