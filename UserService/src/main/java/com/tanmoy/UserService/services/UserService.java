package com.tanmoy.UserService.services;

import com.tanmoy.UserService.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUserById(String userId);
    User updateUser(String userId);
    void deleteUser(String userId);


}
