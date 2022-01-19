package com.example.springboot.service;

import com.example.springboot.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.springboot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findById(Long id) {
        return userRepository.getOne(id);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserEntity editUser(UserEntity user) {
        UserEntity userOld = userRepository.getById(user.getId());
        if (user.getPassword().equals(userOld.getPassword())) {
            return userRepository.save(user);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserEntity> findByUserName(String name) {
        return userRepository.findByUserName(name);
    }
}

