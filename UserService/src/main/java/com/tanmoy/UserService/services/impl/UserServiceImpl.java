package com.tanmoy.UserService.services.impl;

import com.tanmoy.UserService.entities.Hotel;
import com.tanmoy.UserService.entities.Rating;
import com.tanmoy.UserService.entities.User;
import com.tanmoy.UserService.exceptions.UserNotFoundException;
import com.tanmoy.UserService.external.services.HotelService;
import com.tanmoy.UserService.external.services.RatingService;
import com.tanmoy.UserService.repositories.UserRepository;
import com.tanmoy.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUsers = userRepository.findAll();
//        for (User u : allUsers) {
//            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + u.getUserId(), Rating[].class);
//            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//            List<Rating> ratingList = ratings.stream().map(rating -> {
//                Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//                rating.setHotel(hotel);
//                return rating;
//            }).collect(Collectors.toList());
//            u.setRatingOfUser(ratingList);
//        }

        for (User u : allUsers) {
            Rating[] ratingsOfUser = ratingService.getRatingsByUserId(u.getUserId()).getBody().toArray(new Rating[0]);
            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
            List<Rating> ratingList = ratings.stream().map(rating -> {
                Hotel hotel = hotelService.getHotelById(rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            u.setRatingOfUser(ratingList);
        }
        return allUsers;
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with this Id:" + userId));
//        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        Rating[] ratingsOfUser = ratingService.getRatingsByUserId(userId).getBody().toArray(new Rating[0]);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotelById(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        logger.info("{ }", ratingsOfUser);
        user.setRatingOfUser(ratingList);
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
