package com.example.travel.Controller;

import com.example.travel.Dto.SavedItemsRequest;
import com.example.travel.Service.SavedItemsService;
import com.example.travel.entity.HotelEntity;
import com.example.travel.entity.MemberEntity;
import com.example.travel.entity.PlayEntity;
import com.example.travel.entity.RestaurantEntity;
import com.example.travel.entity.SavedItems;
import com.example.travel.repository.HotelRepository;
import com.example.travel.repository.MemberRepository;
import com.example.travel.repository.PlayRepository;
import com.example.travel.repository.RestaurantRepository;
import com.example.travel.repository.SavedItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SavedItemsController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PlayRepository playRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private SavedItemsRepository savedItemsRepository;
    @Autowired
    private SavedItemsService savedItemsService;

    @PostMapping("/saveItems")
    public ResponseEntity<?> saveItems(@RequestBody SavedItemsRequest request) {
        // Member 조회
        MemberEntity member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 다른 엔티티들 조회
        List<HotelEntity> hotels = hotelRepository.findAllById(request.getHotelIds());
        List<PlayEntity> plays = playRepository.findAllById(request.getPlayIds());
        List<RestaurantEntity> restaurants = restaurantRepository.findAllById(request.getRestaurantIds());

        // 관계 설정 및 저장
        SavedItems savedItems = new SavedItems();
        savedItems.setMember(member);
        savedItems.setHotels(hotels);
        savedItems.setPlays(plays);
        savedItems.setRestaurants(restaurants);

        savedItemsRepository.save(savedItems);

        return ResponseEntity.ok("Items saved successfully");
    }
    @GetMapping("/savedItems/{memberId}")
    public ResponseEntity<?> getSavedItems(@PathVariable Long memberId) {
        Optional<SavedItems> savedItems = savedItemsService.getSavedItemsByMemberId(memberId);

        if (savedItems.isPresent()) {
            return ResponseEntity.ok(savedItems.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
