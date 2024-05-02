package com.ProjetJ2EE.ProjetJ2EE.repositories;

import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByGame_GameId(Long gameId);
}
