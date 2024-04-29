package com.ProjetJ2EE.ProjetJ2EE.repositories;

import com.ProjetJ2EE.ProjetJ2EE.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
