package com.example.travel.Service;

import com.example.travel.entity.PlayEntity;
import com.example.travel.repository.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayService {

    private final PlayRepository playRepository;

    // 생성자 주입
    public PlayService(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    // 모든 PlayEntity 항목 가져오기
    @Transactional(readOnly = true)
    public List<PlayEntity> getAllPlays() {
        return playRepository.findAll();
    }

}
