package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Comment;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.repositories.CommentRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/game-details/{id}/add-comment")
    public String addComment(@PathVariable("id") Long id, Comment newComment) {
        // Retrieve the game by its ID
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            // Associate the new comment with the game
            newComment.setGame(game);
            // Save the new comment
            commentRepository.save(newComment);
        }
        return "redirect:/game-details/{id}";
    }
}
