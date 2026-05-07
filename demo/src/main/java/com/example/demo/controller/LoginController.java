package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // Show login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {

        if (username.equals("admin") && password.equals("admin123")) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("userType", "Admin");
            session.setAttribute("username", "admin");
            return "redirect:/";
        }

        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("userType", user.getUserType());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            return "redirect:/customer/home";
        }

        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    // Show signup page
    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    // Handle signup
    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam String licenseNumber,
                               HttpSession session,
                               Model model) {

        // Check username already taken
        User existing = userService.findByUsername(username);
        if (existing != null) {
            model.addAttribute("error", "Username already taken. Please choose another.");
            return "signup";
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setLicenseNumber(licenseNumber);
        newUser.setUserType("Individual");

        userService.addUser(newUser);

        // Auto login after signup
        User saved = userService.findByUsername(username);
        session.setAttribute("loggedIn", true);
        session.setAttribute("userType", saved.getUserType());
        session.setAttribute("username", saved.getUsername());
        session.setAttribute("userId", saved.getUserId());

        return "redirect:/customer/home";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}