package ru.otus.hw13_DI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw13_DI.domain.User;
import ru.otus.hw13_DI.repository.UserRepository;
import ru.otus.hw13_DI.services.UserService;
import ru.otus.hw13_DI.services.UserServiceImpl;

import java.util.ArrayList;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/user/list"})
    public String userListView(Model model) {
        ArrayList<User> users = this.userService.getAll();
        model.addAttribute("users", users);
        return "userList.html";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userCreate.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        userService.saveUser(user);
        return new RedirectView("/user/list", true);
    }

}
