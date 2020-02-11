package ru.otus.hw13_DI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw13_DI.domain.User;
import ru.otus.hw13_DI.repository.UserRepository;

import java.util.ArrayList;

@Controller
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/", "/user/list"})
    public String userListView(Model model) {
        ArrayList<User> users = repository.findAll();
        model.addAttribute("users", users);
        return "userList.html";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        repository.saveUser(user);
        return new RedirectView("/user/list", true);
    }

}
