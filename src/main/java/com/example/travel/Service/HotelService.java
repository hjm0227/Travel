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

    public void createH(String name, Integer price, String type){
        HotelEntity hotel = new HotelEntity();

        hotel.setName(name);
        hotel.setPrice(price);
        hotel.setType(type);

        hotelRepository.save(hotel);
    }
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }
}
