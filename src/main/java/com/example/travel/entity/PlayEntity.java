package com.example.travel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "play_table")
@Setter
@Getter
@ToString
public class PlayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Integer price;
    private String imageUrl;
    private String place;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER) //다대다 관계 설정
    @JoinTable( //중간 테이블 지정(play_type)
            name = "play_type",
            joinColumns = @JoinColumn(name = "play_id"), //play_id와 연결
            inverseJoinColumns = @JoinColumn(name = "type_id")  //type_id와 연결
    )
    private Set<TypeEntity> types = new HashSet<>();

}
