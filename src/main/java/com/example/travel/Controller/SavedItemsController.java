package com.example.travel.Controller;

import com.example.travel.Service.SavedItemsService;
import com.example.travel.entity.SavedItems;
import com.example.travel.repository.HotelRepository;
import com.example.travel.repository.MemberRepository;
import com.example.travel.repository.PlayRepository;
import com.example.travel.repository.RestaurantRepository;
import com.example.travel.repository.SavedItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping()
public class SavedItemsController {

    @Autowired
    private SavedItemsService savedItemsService;
    @PostMapping("/save-itinerary")
    public String StringsaveItems(
            @RequestParam("day1Items") String day1ItemsJson,
            @RequestParam("day2Items") String day2ItemsJson,
            @RequestParam("day3Items") String day3ItemsJson) {
        System.out.println(day1ItemsJson);


        return "redirect:/";
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
