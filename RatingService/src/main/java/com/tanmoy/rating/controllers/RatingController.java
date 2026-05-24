package com.tanmoy.rating.controllers;

import com.tanmoy.rating.entities.Rating;
import com.tanmoy.rating.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        Rating r = ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @GetMapping
    private ResponseEntity<List<Rating>> getRatings() {
        List<Rating> lr = ratingService.getRatings();
        return ResponseEntity.ok(lr);
    }

    @GetMapping("/users/{userId}")
    private ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        List<Rating> lr = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(lr);
    }

    @GetMapping("/hotels/{hotelId}")
    private ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        List<Rating> lr = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(lr);
    }
}
