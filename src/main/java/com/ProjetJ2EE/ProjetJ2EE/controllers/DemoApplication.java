    package com.ProjetJ2EE.ProjetJ2EE.controllers;

    import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.ImageRepository;
    import com.ProjetJ2EE.ProjetJ2EE.services.AccountService;
    import org.apache.catalina.connector.ClientAbortException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import java.util.stream.Collectors;

    import java.io.IOException;
    import java.util.Base64;
    import java.util.List;
    import java.util.Optional;

    @Controller
    public class DemoApplication {
        @Autowired
        private final AccountService accountService;
        @Autowired
        private GameRepository gameRepository;

        @Autowired
        private AccountRepository accountRepository;
        @Autowired
        private ImageRepository imageRepository;


        @Autowired
        public DemoApplication(AccountService accountService) {
            this.accountService = accountService;
        }

        @GetMapping("/register")
        public String showRegistrationForm() {
            return "register";
        }

        @PostMapping("/register")
        public String register(@ModelAttribute Account account, Model model) {
            if (!accountService.addAccount(account.getEmail(), account.getPassword(), account.getUsername(), account.getCountry(), account.getFirstName(), account.getLastName(), account.getAge(), account.getGender())) {
                model.addAttribute("error", "Email or username already exists");
                return "register";
            }

            return "redirect:/profilepic";
        }

        @GetMapping("/login")
        public String showLoginForm() {
            return "login";
        }

        @PostMapping("/login")
        public String login(@RequestParam String email, @RequestParam String password) {
            if (accountService.validateLogin(email, password)) {
                return "redirect:/main";
            } else {
                return "redirect:/login";
            }
        }
        @RequestMapping("/profile")
        public String Profile() {
            return "profile";
        }

        @GetMapping("/main")
        public String Acceuil(Model model) {
            // Fetch all games from the repository
            List<Game> allGames = gameRepository.findAll();

            // Limit the number of games to six
            List<Game> games = allGames.stream().limit(6).collect(Collectors.toList());

            games.forEach(game -> {
                List<Image> images = game.getImages();
                images.forEach(image -> {
                    String base64Image = bytesToBase64(image.getImage());
                    image.setPictureBase64(base64Image);
                });
            });
            model.addAttribute("games", games);

            List<Account> coaches = accountRepository.findByusertype("C");
            coaches.forEach(coach -> {
                byte[] coachImage = coach.getImage();
                if (coachImage != null) {
                    String base64Image = bytesToBase64(coachImage);
                    coach.setPictureBase64(base64Image);
                }
            });

            model.addAttribute("coaches", coaches);

            return "main";
        }
        public String bytesToBase64(byte[] bytes) {
            return Base64.getEncoder().encodeToString(bytes);
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




        @GetMapping("/userslist")
        public String usersList(Model model, @RequestParam(name = "search", required = false) String searchQuery) {
            List<Account> users;

            if (searchQuery != null && !searchQuery.isEmpty()) {
                users = accountRepository.findByusernameContaining(searchQuery);
            } else {
                users = accountRepository.findAll();
            }

            users.forEach(user -> {
                byte[] userImage = user.getImage();
                if (userImage != null) {
                    String base64Image = bytesToBase64(userImage);
                    user.setPictureBase64(base64Image);
                }
            });

            model.addAttribute("users", users);
            return "userslist";
        }




    }
