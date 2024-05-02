package com.ProjetJ2EE.ProjetJ2EE.repositories;

import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends JpaRepository<Game,Long> {

}
