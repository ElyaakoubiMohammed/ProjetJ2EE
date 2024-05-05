package com.ProjetJ2EE.ProjetJ2EE.repositories;

import com.ProjetJ2EE.ProjetJ2EE.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
}
