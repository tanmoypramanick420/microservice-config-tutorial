package com.tanmoy.UserService.services.impl;

import com.tanmoy.UserService.entities.Rating;
import com.tanmoy.UserService.entities.User;
import com.tanmoy.UserService.exceptions.UserNotFoundException;
import com.tanmoy.UserService.repositories.UserRepository;
import com.tanmoy.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with this Id:" + userId));
        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(),ArrayList.class);
        logger.info("{ }", ratingOfUser);
        user.setRatingOfUser(ratingOfUser);
        return user;
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
