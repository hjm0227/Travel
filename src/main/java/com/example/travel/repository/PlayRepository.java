package com.example.travel.repository;

import com.example.travel.entity.PlayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayRepository extends JpaRepository<PlayEntity, Long> {

}
