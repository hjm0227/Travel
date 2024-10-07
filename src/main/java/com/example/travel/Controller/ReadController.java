package com.example.travel.Controller;

import com.example.travel.Service.RestaurantService;
import com.example.travel.Service.PlayService;
import com.example.travel.Service.HotelService;
import com.example.travel.entity.HotelEntity;
import com.example.travel.entity.PlayEntity;
import com.example.travel.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReadController {

    private final RestaurantService restaurantService;
    private final PlayService playService;
    private final HotelService hotelService;

    // 생성자 주입
    public ReadController(RestaurantService restaurantService, PlayService playService, HotelService hotelService) {
        this.restaurantService = restaurantService;
        this.playService = playService;
        this.hotelService = hotelService;
    }

    // 추천 페이지 데이터 가져오기
    @GetMapping("/recommend")
    public String getRecommendations(Model model) {
        List<RestaurantEntity> restaurants = restaurantService.getAllRestaurants();
        List<PlayEntity> plays = playService.getAllPlays();
        List<HotelEntity> hotels = hotelService.getAllHotels();

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("plays", plays);
        model.addAttribute("hotels", hotels);

        return "recommend";  // recommend.html로 이동
    }
}
