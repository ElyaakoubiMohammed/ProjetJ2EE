package com.ProjetJ2EE.ProjetJ2EE.repositories;

import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
import com.ProjetJ2EE.ProjetJ2EE.entities.Categorie;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game,Long> {
    @Query("SELECT a FROM Game a WHERE a.categorie = :categorie")
    List<Game> findByCategorie(Categorie categorie);
}
