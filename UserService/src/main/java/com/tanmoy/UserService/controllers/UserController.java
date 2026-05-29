package com.tanmoy.UserService.controllers;

import com.tanmoy.UserService.entities.User;
import com.tanmoy.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> creeateUser(@RequestBody User user) {
        User u = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User u = userService.getUserById(userId);
        return ResponseEntity.ok(u);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
}
