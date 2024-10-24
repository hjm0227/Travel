package com.example.travel.repository;

import com.example.travel.entity.SavedItems;  // SavedItemsEntity 경로를 확인하세요.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavedItemsRepository extends JpaRepository<SavedItems, Long> {
    Optional<SavedItems> findByMemberId(Long memberId);
}

