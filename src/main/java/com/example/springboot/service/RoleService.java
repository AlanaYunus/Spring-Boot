package com.example.springboot.service;

import com.example.springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Role findById(Long id){
        return roleRepository.getOne(id);
    }


}
