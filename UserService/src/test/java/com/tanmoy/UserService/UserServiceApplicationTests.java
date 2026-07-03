package com.tanmoy.UserService;

import com.tanmoy.UserService.entities.Rating;
import com.tanmoy.UserService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;
	@Test
	void createRating() {
		Rating rating = Rating.builder().ratingId("11").userId("1").hotelId("111").feedback("this is created using feign client").build();
		Rating saveRating = ratingService.createRating(rating).getBody();
		System.out.println("new rating created");
	}

}
