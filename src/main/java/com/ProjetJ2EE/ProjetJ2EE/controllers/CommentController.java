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

        @PostMapping("/game-details/add-comment")
        public String addComment(@RequestParam("gameId") Long gameId,@RequestParam("content") String newComment,@RequestParam("title") String titre, Model model) {
            Game game = gameRepository.findById(gameId).orElse(null);
            if (game != null) {
                Comment com=new Comment();
                com.setGame(game);
                com.setFeedBack(newComment);
                com.setTitre(titre);
                commentRepository.save(com);
            }
            model.addAttribute("gameId", gameId);
            return "redirect:/main";
        }
    }