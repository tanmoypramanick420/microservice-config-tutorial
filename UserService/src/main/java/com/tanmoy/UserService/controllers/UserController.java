package com.tanmoy.UserService.controllers;

import com.tanmoy.UserService.entities.User;
import com.tanmoy.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> creeateUser(@RequestBody User user) {
        User u = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User u = userService.getUserById(userId);
        return ResponseEntity.ok(u);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception e) {
        logger.info("Fallback Is Executed Because Service Is Down!", e.getMessage());
        User user = User.builder().email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
}
