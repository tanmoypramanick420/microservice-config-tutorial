package com.tanmoy.hotel.services;

import com.tanmoy.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getHotel(String hotelId);
}
