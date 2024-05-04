package com.ProjetJ2EE.ProjetJ2EE.services;

import com.ProjetJ2EE.ProjetJ2EE.entities.Categorie;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
@Service
public class GameService
{
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Transactional
    public void addGame(String gameName, String description, Date publishDate, MultipartFile imageFile, String categoryName) throws IOException {
        Game game = Game.builder()
                .gameName(gameName)
                .description(description)
                .publishDate(publishDate)
                .build();

        // Set the category based on the categoryName parameter
        Categorie categorie = new Categorie();
        switch (categoryName) {
            case "action":
                categorie.setCategorieId(1L); // Assuming 1 is the ID for the "action" category
                break;
            case "adventure":
                categorie.setCategorieId(2L); // Assuming 2 is the ID for the "adventure" category
                break;
            case "horror":
                categorie.setCategorieId(3L); // Assuming 3 is the ID for the "horror" category
                break;
            case "openworld":
                categorie.setCategorieId(4L); // Assuming 4 is the ID for the "open world" category
                break;
            default:
                // Handle invalid category
                break;
        }
        game.setCategorie(categorie);

        // Save the game
        gameRepository.save(game);

        if (imageFile != null && !imageFile.isEmpty()) {
            // Set the byte array directly to the image field
            byte[] imageData = imageFile.getBytes();

            // Create and save the image entity
            Image image = Image.builder()
                    .game(game)
                    .image(imageData)
                    .build();
            imageRepository.save(image);
        }
    }


}
