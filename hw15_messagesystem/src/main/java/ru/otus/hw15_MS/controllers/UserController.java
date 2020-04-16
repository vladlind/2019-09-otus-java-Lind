package ru.otus.hw15_MS.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw15_MS.domain.PhoneDataSet;
import ru.otus.hw15_MS.domain.User;
import ru.otus.hw15_MS.services.UserService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/"})
    public String loginPageView(Model model) {
        return "login.html";
    }

    @PostMapping({"/user/login"})
    public String loginPagePost(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (this.userService.authenticateUser(username, password)) {
            List<PhoneDataSet> phones = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                phones.add(new PhoneDataSet());
            }
            User user = new User();
            model.addAttribute("phoneList", phones);
            model.addAttribute("user", new User());
            user.setPhoneDataSet(phones);
            return "userCreate.html";
        } else {
            return "notAuthenticated.html";
        }
    }

    @GetMapping("/user/list")
    public String userList(Model model) {
        ArrayList<User> users = this.userService.getAll();
        model.addAttribute("allUsers", users);
        return "userList.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        this.userService.saveUser(user);
        return new RedirectView("/user/list", true);
    }

    public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
        Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
        if (consumer == null) {
            logger.warn("consumer not found for:{}", sourceMessageId);
            return Optional.empty();
        }
        return Optional.of(consumer);
    }



}
