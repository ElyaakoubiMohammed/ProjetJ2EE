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
                // Create a new comment
                Comment comment = new Comment();
                comment.setGame(game);
                comment.setFeedBack(newComment);
                comment.setTitre(titre);
                comment.setRating(rating);

                // Save the comment
                commentRepository.save(comment);

                // Calculate the new game rating
                List<Comment> comments = game.getComments();
                double totalRatingSum = comments.stream().mapToInt(Comment::getRating).sum() + rating;
                double newRating = totalRatingSum / (comments.size() + 1); // Add 1 for the new comment

                // Update the game's rating
                game.setRating((int) Math.round(newRating));
                gameRepository.save(game);
            }
            model.addAttribute("gameId", gameId);
            return "redirect:/main";
        }


    }