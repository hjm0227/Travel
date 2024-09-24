package com.example.travel.Service;

import com.example.travel.entity.PlayEntity;
import com.example.travel.repository.PlayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayService {

    private final PlayRepository playRepository;

    public PlayService(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    public List<PlayEntity> getAllPlays() {
        return playRepository.findAll();
    }

    public void createP(String name, Integer price, String type){
        PlayEntity play = new PlayEntity();

        play.setName(name);
        play.setPrice(price);
        play.setType(type);

        playRepository.save(play);
    }

    public void delete(Long id) {
        playRepository.deleteById(id);
    }
}
