package com.example.springboot.controller;

import com.example.springboot.model.UserEntity;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
import java.util.Optional;

@Controller
@EnableWebMvc
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserPage(Model model, Principal user) {
        // check if user is login
        Optional<UserEntity> userFound = userService.findByUserName(user.getName());
        model.addAttribute("user", userFound.get());
        return "user";
    }


}
