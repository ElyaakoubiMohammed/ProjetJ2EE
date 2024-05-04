package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
import com.ProjetJ2EE.ProjetJ2EE.entities.Categorie;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.CategorieRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;

@Controller
public class CategoriesController
{
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @RequestMapping("/categories")
    public String Show()
    {
        return "categories";
    }

    @GetMapping("/categories")
    public String Category(Model model, @RequestParam("category") String categoryName) {

        Categorie categorie = categorieRepository.findByCategorieType(categoryName);

         List<Game> games = gameRepository.findByCategorie(categorie);

            games.forEach(game -> {
                List<Image> images = game.getImages();
                images.forEach(image -> {
                    String base64Image = bytesToBase64(image.getImage());
                    image.setPictureBase64(base64Image);
                });
            });
            model.addAttribute("games", games);

        return "categories";
    }

    public String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}
