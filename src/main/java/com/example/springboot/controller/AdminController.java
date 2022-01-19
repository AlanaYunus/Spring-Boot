package com.example.springboot.controller;

import com.example.springboot.model.UserEntity;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/admin/users")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String allUsers(ModelMap model) {
        List<UserEntity> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String getById(ModelMap model, @PathVariable("id") Long id) {
        UserEntity user = userService.findById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/addddd")
    public String newPerson(ModelMap model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("allRoles", roleService.findAll());
        return "add";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("user") UserEntity user) {
        userService.saveUser(user);
        return "successPage";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        UserEntity user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.findAll());
        return "editUser";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") UserEntity user) {
        userService.editUser(user);
        return "successPage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "successPageDelete";
    }

}