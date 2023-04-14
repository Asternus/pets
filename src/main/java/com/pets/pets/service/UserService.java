package com.pets.pets.service;

import com.pets.pets.entity.User;
import com.pets.pets.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(final String name, String type) {
        final User user = new User();
        user.setType(type);
        user.setName(name);
        userRepo.saveUser(user);
    }

    public User getById(final int id) {
        return userRepo.findById(id);
    }

    public List<User> getAll() {
        return userRepo.findAllUsers();
    }

}
