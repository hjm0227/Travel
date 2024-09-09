package com.example.travel.Service;

import com.example.travel.entity.HotelEntity;
import com.example.travel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelEntity> getAllHotels() {
        return hotelRepository.findAll();
    }
}
