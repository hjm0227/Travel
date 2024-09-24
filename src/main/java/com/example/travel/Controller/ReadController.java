package com.example.travel.Controller;

import com.example.travel.Service.RestaurantService;
import com.example.travel.Service.PlayService;
import com.example.travel.Service.HotelService;
import com.example.travel.entity.HotelEntity;
import com.example.travel.entity.PlayEntity;
import com.example.travel.entity.RestaurantEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReadController {

    private final RestaurantService restaurantService;
    private final PlayService playService;
    private final HotelService hotelService;

    public ReadController(RestaurantService restaurantService, PlayService playService, HotelService hotelService) {
        this.restaurantService = restaurantService;
        this.playService = playService;
        this.hotelService = hotelService;
    }

    @GetMapping("/recommend")
    public String getRecommendations(Model model) {
        // 각 서비스에서 데이터 가져오기
        List<RestaurantEntity> restaurants = restaurantService.getAllRestaurants();
        List<PlayEntity> plays = playService.getAllPlays();
        List<HotelEntity> hotels = hotelService.getAllHotels();


        // 모델에 데이터 추가
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("plays", plays);
        model.addAttribute("hotels", hotels);


        return "recommend";  // recommend.html로 이동
    }

    @GetMapping("/createH")
    public String createHForm(){
        return "createH";
    }
    @PostMapping("/createH")
    public String createH(String name, Integer price, String type){
        hotelService.createH(name, price, type);

        return "redirect:/recommend";
    }

    @GetMapping("/createR")
    public String createRForm(){
        return "createR";
    }
    @PostMapping("/createR")
    public String createR(String name, Integer price, String type){
        restaurantService.createR(name, price, type);

        return "redirect:/recommend";
    }

    @GetMapping("/createP")
    public String createPForm(){
        return "createP";
    }
    @PostMapping("/createP")
    public String createP(String name, Integer price, String type){
        playService.createP(name, price, type);

        return "redirect:/recommend";
    }

    @PostMapping("/deleteHotel")
    public String deleteHotel(@RequestParam Long id) {
        hotelService.delete(id);
        return "redirect:/recommend";
    }
    @PostMapping("/deletePlay")
    public String deletePlay(@RequestParam Long id) {
        playService.delete(id);
        return "redirect:/recommend";
    }

    @PostMapping("/deleteRestaurant")
    public String deleteRestaurant(@RequestParam Long id) {
        restaurantService.delete(id);
        return "redirect:/recommend";
    }
}