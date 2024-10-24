package com.example.travel.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "saved_items")
public class SavedItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member와의 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    // Hotel과의 다대다 관계 설정
    @ManyToMany
    @JoinTable(
            name = "saved_items_hotels",
            joinColumns = @JoinColumn(name = "saved_items_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<HotelEntity> hotels;

    // Play와의 다대다 관계 설정
    @ManyToMany
    @JoinTable(
            name = "saved_items_plays",
            joinColumns = @JoinColumn(name = "saved_items_id"),
            inverseJoinColumns = @JoinColumn(name = "play_id")
    )
    private List<PlayEntity> plays;

    // Restaurant과의 다대다 관계 설정
    @ManyToMany
    @JoinTable(
            name = "saved_items_restaurants",
            joinColumns = @JoinColumn(name = "saved_items_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<RestaurantEntity> restaurants;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public List<HotelEntity> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelEntity> hotels) {
        this.hotels = hotels;
    }

    public List<PlayEntity> getPlays() {
        return plays;
    }

    public void setPlays(List<PlayEntity> plays) {
        this.plays = plays;
    }

    public List<RestaurantEntity> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantEntity> restaurants) {
        this.restaurants = restaurants;
    }
}
