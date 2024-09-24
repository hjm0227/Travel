package com.example.travel.Service;

import com.example.travel.entity.RestaurantEntity;
import com.example.travel.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public void createR(String name, Integer price, String type){
        RestaurantEntity restaurant = new RestaurantEntity();

        restaurant.setName(name);
        restaurant.setPrice(price);
        restaurant.setType(type);

        restaurantRepository.save(restaurant);
    }
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }
}
