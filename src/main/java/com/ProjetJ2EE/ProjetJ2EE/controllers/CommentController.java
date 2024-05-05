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
public class CommentController
{



    @Autowired
    private GameRepository gameRepository;
    private CommentRepository commentRepository;
    @PostMapping("/game-details/{id}")
    public String addComment(@PathVariable("id") Long id, @RequestParam("comment") String commentText, Model model) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            Comment comment = Comment.builder()
                    .FeedBack(commentText)
                    .game(game)
                    .build();
            game.getComments().add(comment);
            commentRepository.save(comment);
        }

        return "redirect:/register";
    }


}
