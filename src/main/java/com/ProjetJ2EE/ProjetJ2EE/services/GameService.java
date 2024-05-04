package com.ProjetJ2EE.ProjetJ2EE.services;

import com.ProjetJ2EE.ProjetJ2EE.entities.*;
import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.ImageRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.SpecsRepository; // Add this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SpecsRepository specsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void addGame(String gameName, String description, Date publishDate, MultipartFile imageFile, String categoryName,
                        String minOperatingSystem, String minProcessor, String minGPU, int minMemory, int minStorage,
                        String recOperatingSystem, String recProcessor, String recGPU, int recMemory, int recStorage, double prix) throws IOException {
        // Create the Specs objects for minimum and recommended specifications
        Specs minSpecs = Specs.builder()
                .OperatingSystem(minOperatingSystem)
                .Processor(minProcessor)
                .Memory(minMemory)
                .GPU(minGPU)
                .Storage(minStorage)
                .build();

        Specs recSpecs = Specs.builder()
                .OperatingSystem(recOperatingSystem)
                .Processor(recProcessor)
                .Memory(recMemory)
                .GPU(recGPU)
                .Storage(recStorage)
                .build();

        // Save the Specs entities using the Specs repository
        specsRepository.save(minSpecs);
        specsRepository.save(recSpecs);

        // Create the Game object and set its properties
        Game game = Game.builder()
                .gameName(gameName)
                .description(description)
                .publishDate(publishDate)
                .minSpecs(minSpecs)
                .recSpecs(recSpecs)
                .prix(prix)
                .build();

        Categorie categorie = new Categorie();
        switch (categoryName) {
            case "action":
                categorie.setCategorieId(2L);
                break;
            case "adventure":
                categorie.setCategorieId(3L);
            case "horror":
                categorie.setCategorieId(1L);
                break;
            case "openworld":
                categorie.setCategorieId(4L);
                break;
            default:
                break;
        }

        // Set the category directly
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
    @Transactional
    public void addGameToCart(Long gameId, Long accountId) {
        Game game = gameRepository.findById(gameId).orElse(null);
        Account account = accountRepository.findById(accountId).orElse(null);

        if (game != null && account != null) {
            // Increment the GameCount for the account
            account.setGameCount(account.getGameCount() + 1);
            accountRepository.save(account);
        } else {
            // Handle the case where either the game or account is not found
            // You can throw an exception, log an error, or handle it as appropriate for your application
        }
    }


}
