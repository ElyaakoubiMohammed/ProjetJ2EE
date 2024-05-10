package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/addwish/{gameId}/{userId}")
    public String addToWishlist(@PathVariable("gameId") Long gameId, @PathVariable("userId") Long userId) {
        wishlistService.addToWishlist(gameId, userId);
        return "main";
    }


}
