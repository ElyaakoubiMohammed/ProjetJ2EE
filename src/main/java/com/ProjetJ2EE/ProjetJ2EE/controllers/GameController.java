package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Categorie;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
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







}
