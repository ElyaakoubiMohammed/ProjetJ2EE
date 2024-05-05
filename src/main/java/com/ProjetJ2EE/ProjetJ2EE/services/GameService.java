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

        specsRepository.save(minSpecs);
        specsRepository.save(recSpecs);

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
            case "horror":
                categorie.setCategorieId(1L);
                break;
            case "action":
                categorie.setCategorieId(2L);
                break;
            case "adventure":
                categorie.setCategorieId(3L);
                break;
            case "openworld":
                categorie.setCategorieId(4L);
                break;
            default:
                break;
        }

        game.setCategorie(categorie);

        gameRepository.save(game);

        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageData = imageFile.getBytes();
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
            account.setGameCount(account.getGameCount() + 1);
            accountRepository.save(account);
        } else {

        }
    }
    @Transactional

    public void deleteGameById(Long gameId)
    {
        gameRepository.deleteById(gameId);
    }


}
