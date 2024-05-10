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

    import java.util.List;

    @Controller
    public class CommentController {

        @Autowired
        private GameRepository gameRepository;

        @Autowired
        private CommentRepository commentRepository;

        @PostMapping("/game-details/add-comment")
        public String addComment(@RequestParam("gameId") Long gameId,
                                 @RequestParam("content") String newComment,
                                 @RequestParam("title") String titre,
                                 @RequestParam("rating") int rating,
                                 Model model) {
            Game game = gameRepository.findById(gameId).orElse(null);
            if (game != null) {
                Comment comment = new Comment();
                comment.setGame(game);
                comment.setFeedBack(newComment);
                comment.setTitre(titre);
                comment.setRating(rating);

                // Save the comment
                commentRepository.save(comment);


                List<Comment> comments = game.getComments();
                int numberOfRatings = comments.size();

// Calculate the total satisfaction score of existing comments
                double totalSatisfactionScore = comments.stream().mapToInt(Comment::getRating).sum();

// Add the new rating to the total satisfaction score
                totalSatisfactionScore += rating;

// Calculate the new rating score
                double ratingScore = totalSatisfactionScore / (numberOfRatings + 1);

                game.setRating((int) Math.round(ratingScore));

                gameRepository.save(game);
            }
            model.addAttribute("gameId", gameId);
            return "redirect:/main";
        }


    }