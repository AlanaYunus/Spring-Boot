package com.example.springboot.config;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class StartData {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    private void postConstruct() {
        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword("1234");
        Set<Role> roleSetAdmin = new HashSet<>();
        roleSetAdmin.add(roleService.findById(1L));
        roleSetAdmin.add(roleService.findById(2L));
        admin.setRoles(roleSetAdmin);

        userService.saveUser(admin);

        User normalUser = new User();
        normalUser.setUserName("bob");
        normalUser.setPassword("1234");
        Set<Role> roleSetUser = new HashSet<>();
        roleSetUser.add(roleService.findById(2L));
        normalUser.setRoles(roleSetUser);

        userService.saveUser(normalUser);
    }

}
