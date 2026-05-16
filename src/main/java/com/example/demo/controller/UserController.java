package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(@RequestParam(required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            User found = userService.findByUsername(search);
            List<User> result = new ArrayList<>();
            if (found != null) result.add(found);
            model.addAttribute("users", result);
            model.addAttribute("search", search);
        } else {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("search", "");
        }
        return "user/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("index", userService.getIndexById(id));
        return "user/edit";
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam int index, @ModelAttribute User user) {
        userService.updateUser(index, user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(userService.getIndexById(id));
        return "redirect:/users";
    }
}