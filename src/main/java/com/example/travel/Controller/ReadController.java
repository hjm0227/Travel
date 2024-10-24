package com.example.travel.Controller;

import com.example.travel.Service.RestaurantService;
import com.example.travel.Service.PlayService;
import com.example.travel.Service.HotelService;
import com.example.travel.entity.HotelEntity;
import com.example.travel.entity.MemberEntity;
import com.example.travel.entity.PlayEntity;
import com.example.travel.entity.RestaurantEntity;
import com.example.travel.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReadController {

    private final RestaurantService restaurantService;
    private final PlayService playService;
    private final HotelService hotelService;
    private final MemberRepository memberRepository;

    // 생성자 주입
    public ReadController(RestaurantService restaurantService, PlayService playService, HotelService hotelService, MemberRepository memberRepository) {
        this.restaurantService = restaurantService;
        this.playService = playService;
        this.hotelService = hotelService;
        this.memberRepository = memberRepository;
    }

    // MBTI에 따른 해시태그 매핑
    private static final Map<String, String> mbtiHashtags = new HashMap<String, String>() {{
        put("ISTJ", "#역사 #전통 #힐링");
        put("ISFJ", "#자연 #평화 #힐링");
        put("INFJ", "#감성 #예술 #평화");
        put("INTJ", "#창의 #전통 #역사");
        put("ISTP", "#모험 #액티비티 #자연");
        put("ISFP", "#예술 #자연 #감성");
        put("INFP", "#감성 #예술 #힐링");
        put("INTP", "#창의 #모험 #액티비티");
        put("ESTP", "#액티비티 #모험 #축제");
        put("ESFP", "#축제 #사교 #예술");
        put("ENFP", "#사교 #창의 #모험");
        put("ENTP", "#창의 #액티비티 #모험");
        put("ESTJ", "#리더십 #전통 #역사");
        put("ESFJ", "#사교 #축제 #힐링");
        put("ENFJ", "#리더십 #사교 #감성");
        put("ENTJ", "#리더십 #액티비티 #창의");
    }};

    // 추천 페이지 데이터 가져오기
    @GetMapping("/recommend")
    public String getRecommendations(
            @RequestParam("duration") String duration,
            @RequestParam("mbti") String mbti,
            Model model,
            HttpSession session) {

        // 세션에서 사용자 ID 가져오기
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return "redirect:/travel/login?loginRequired=true";  // 로그인 안된 상태면 로그인 페이지로 리다이렉트
        }

        // MemberId를 통해 MemberEntity 호출
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 사용자 정보를 Model에 추가
        model.addAttribute("memberEntity", memberEntity);

        // 서비스로부터 데이터 가져오기
        List<RestaurantEntity> restaurants = restaurantService.getAllRestaurants();
        List<PlayEntity> plays = playService.getAllPlays();
        List<HotelEntity> hotels = hotelService.getAllHotels();

        // Model에 데이터 추가
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("plays", plays);
        model.addAttribute("hotels", hotels);

        // URL 파라미터로 받은 duration과 mbti 값을 Model에 추가
        model.addAttribute("duration", duration);
        model.addAttribute("mbti", mbti);

        // MBTI에 따른 해시태그 추가
        String hashtags = mbtiHashtags.get(mbti);
        model.addAttribute("hashtags", hashtags != null ? hashtags : "해시태그를 찾을 수 없습니다.");

        return "recommend";  // recommend.html로 이동
    }

}
