package com.ProjetJ2EE.ProjetJ2EE.repositories;

import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
