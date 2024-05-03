package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GameController
{
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/pay/{id}")
    public String Pay(@PathVariable("id") Long id, Model model)
    {
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            model.addAttribute("game", game);
            return "payment";
        } else {
            return "error";
        }
    }


}
