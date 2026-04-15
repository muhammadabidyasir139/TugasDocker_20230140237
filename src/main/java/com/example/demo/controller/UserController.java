package com.example.demo.controller;

import com.example.demo.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // ===================== LOGIN =====================

    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if (username.equals("admin") && password.equals("20230140237")) {
            session.setAttribute("loggedIn", true);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau password salah!");
            return "login";
        }
    }

    // ===================== HOME =====================
//menuju ke halmaan home
    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        List<User> users = getUsers(session);
        model.addAttribute("users", users);
        return "home";
    }

    // ===================== FORM =====================

    @GetMapping("/form")
    public String showForm(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute User user,
                             HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        List<User> users = getUsers(session);
        users.add(user);
        session.setAttribute("users", users);
        return "redirect:/home";
    }

    // ===================== LOGOUT =====================

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // ===================== HELPER =====================

    @SuppressWarnings("unchecked")
    private List<User> getUsers(HttpSession session) {
        List<User> users = (List<User>) session.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
            session.setAttribute("users", users);
        }
        return users;
    }
}