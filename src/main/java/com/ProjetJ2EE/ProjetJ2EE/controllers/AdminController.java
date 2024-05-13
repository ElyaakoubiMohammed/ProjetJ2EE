package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
import com.ProjetJ2EE.ProjetJ2EE.entities.Comment;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private GameRepository gameRepository;


    @GetMapping("/userslistA")
    public String usersList(Model model, @RequestParam(name = "search", required = false) String searchQuery) {
        List<Account> users;
            users = accountRepository.findAll();

        model.addAttribute("users", users);
        return "userslistA";
    }

    @GetMapping("/gameslistA")
    public String gamesList(Model model, @RequestParam(name = "search", required = false) String searchQuery) {
        List<Game> games;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            games = gameRepository.findBygameNameContaining(searchQuery);
        } else {
            games = gameRepository.findAll();
        }

        games.forEach(game -> {
            List<Image> images = game.getImages();
            images.forEach(image -> {
                String base64Image = bytesToBase64(image.getImage());
                image.setPictureBase64(base64Image);
            });

        });
        model.addAttribute("games", games);

        return "gameslistA";
    }

    @RequestMapping("/admin")
    public String Admin()
    {
        return "adminMain";
    }

    public String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
