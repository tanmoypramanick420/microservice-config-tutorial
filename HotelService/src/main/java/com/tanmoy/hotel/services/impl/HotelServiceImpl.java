package com.tanmoy.hotel.services.impl;

import com.tanmoy.hotel.entities.Hotel;
import com.tanmoy.hotel.exceptions.HotelNotFoundException;
import com.tanmoy.hotel.repositories.HotelRepository;
import com.tanmoy.hotel.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel is not found with the id: "+ hotelId));
    }
}
