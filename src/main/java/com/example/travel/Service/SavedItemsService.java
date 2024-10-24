package com.example.travel.Service;

import com.example.travel.entity.SavedItems;
import com.example.travel.repository.SavedItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavedItemsService {

    @Autowired
    private SavedItemsRepository savedItemsRepository;

    public Optional<SavedItems> getSavedItemsByMemberId(Long memberId) {
        return savedItemsRepository.findByMemberId(memberId);
    }
}
