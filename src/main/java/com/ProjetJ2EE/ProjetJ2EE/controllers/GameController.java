package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.*;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.ImageRepository;
import com.ProjetJ2EE.ProjetJ2EE.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private GameService gameService;
    @GetMapping("/pay")
    public String pay( Model model) {

            return "payment";

    }
    @GetMapping("/game-details/{id}")
    public String showGameDetails(@PathVariable("id") Long id, Model model) {
        Game game = gameRepository.findById(id).orElse(null);


        if (game != null) {
            game.getCategorie();
            game.getMinSpecs();
            game.getRecSpecs();

            List<Image> images = game.getImages();
            images.forEach(image -> {
                String base64Image = bytesToBase64(image.getImage());
                image.setPictureBase64(base64Image);
            });

            String categorieType = game.getCategorie().getCategorieType();
            model.addAttribute("categorieType", categorieType);

            model.addAttribute("game", game);
            return "game-details";
        } else {
            return "error";
        }
    }
    @RequestMapping("/addgame")
    public String showAddGameForm() {
        return "addgame";
    }

    @PostMapping("/addgame")
    public String addGame(
            @RequestParam("gameName") String gameName,
            @RequestParam("description") String description,
            @RequestParam("publishDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date publishDate,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("minOperatingSystem") String minOperatingSystem,
            @RequestParam("minProcessor") String minProcessor,
            @RequestParam("minGPU") String minGPU,
            @RequestParam("minMemory") int minMemory,
            @RequestParam("minStorage") int minStorage,
            @RequestParam("recOperatingSystem") String recOperatingSystem,
            @RequestParam("recProcessor") String recProcessor,
            @RequestParam("recGPU") String recGPU,
            @RequestParam("recMemory") int recMemory,
            @RequestParam("recStorage") int recStorage,
            @RequestParam("prix") double prix) throws IOException {

        gameService.addGame(gameName, description, publishDate, imageFile, categoryName,
                minOperatingSystem, minProcessor, minGPU, minMemory, minStorage,
                recOperatingSystem, recProcessor, recGPU, recMemory, recStorage, prix);
        return "redirect:/main";
    }

    @GetMapping("/gameslist")
    public String usersList(Model model, @RequestParam(name = "search", required = false) String searchQuery) {
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
        return "gameslist";
    }

    public String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}
