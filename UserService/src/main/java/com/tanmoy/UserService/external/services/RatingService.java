package com.tanmoy.UserService.external.services;

import com.tanmoy.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {
    @GetMapping("/ratings/users/{userId}")
    ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId);

    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating values);

    @PutMapping("/ratings/{ratingId}")
    Rating updaterating(@PathVariable String ratingId, Rating values);

    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}
