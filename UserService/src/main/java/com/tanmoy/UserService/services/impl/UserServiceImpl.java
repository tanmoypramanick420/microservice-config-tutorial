package com.tanmoy.UserService.services.impl;

import com.tanmoy.UserService.entities.User;
import com.tanmoy.UserService.exceptions.UserNotFoundException;
import com.tanmoy.UserService.repositories.UserRepository;
import com.tanmoy.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with this Id:" + userId));
    }

    @Override
    public User updateUser(String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
