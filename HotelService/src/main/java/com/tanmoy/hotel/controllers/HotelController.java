package com.tanmoy.hotel.controllers;

import com.tanmoy.hotel.entities.Hotel;
import com.tanmoy.hotel.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel h = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(h);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
        Hotel h = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(h);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll() {
        List<Hotel> lh = hotelService.getAll();
        return ResponseEntity.ok(lh);
    }
}
