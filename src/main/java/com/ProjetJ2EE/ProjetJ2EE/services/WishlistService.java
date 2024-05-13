package com.ProjetJ2EE.ProjetJ2EE.services;

import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.Wishlist;
import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WishlistRepository wishlistRepository;
    @Transactional
    public void addToWishlist(Long gameId, Long userId) {
        Game game = gameRepository.findById(gameId).orElse(null);

        Account user = accountRepository.findById(userId).orElse(null);

        if (game != null && user != null) {
            Wishlist wishlist = user.getWishlist();
            if (wishlist == null) {
                wishlist = new Wishlist();
                wishlist.setAccount(user);
            }
            List<Game> games = wishlist.getGames();
            games.add(game);
            wishlist.setGames(games);
            wishlistRepository.save(wishlist);
        }
    }
}
