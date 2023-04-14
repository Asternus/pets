package com.pets.pets.controllers;

import com.pets.pets.entity.User;
import com.pets.pets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class FirstController {

    private final UserService userService;

    @Autowired
    public FirstController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String adminAddUser(final Model model) {
        final List<User> all = userService.getAll();
        final boolean empty = all.isEmpty();
        model.addAttribute("users", all);
        model.addAttribute("empty", empty);
        return "admReg";
    }

    @GetMapping("/id/{id}")
    public String getWithId(final Model model, @PathVariable int id) {
        final User byId = userService.getById(id);
        model.addAttribute("user", byId);
        return "getById";
    }

    @PostMapping("/name")
    public String add(@RequestParam Map<String, String> form) {
        userService.addUser(form.get("name"), form.get("type"));
        return "redirect:/user";
    }

    @GetMapping("/name")
    public String getInformationFromUser() {
        return "addUser";
    }

}
