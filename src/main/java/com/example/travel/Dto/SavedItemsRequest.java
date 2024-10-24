package com.example.travel.Dto;

import java.util.List;

public class SavedItemsRequest {
    private Long memberId;
    private List<Long> hotelIds;
    private List<Long> playIds;
    private List<Long> restaurantIds;

    // 기본 생성자
    public SavedItemsRequest() {}

    // getters와 setters
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public List<Long> getHotelIds() {
        return hotelIds;
    }

    public void setHotelIds(List<Long> hotelIds) {
        this.hotelIds = hotelIds;
    }

    public List<Long> getPlayIds() {
        return playIds;
    }

    public void setPlayIds(List<Long> playIds) {
        this.playIds = playIds;
    }

    public List<Long> getRestaurantIds() {
        return restaurantIds;
    }

    public void setRestaurantIds(List<Long> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }
}
