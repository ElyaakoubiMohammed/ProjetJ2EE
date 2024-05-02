    package com.ProjetJ2EE.ProjetJ2EE.controllers;

    import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.ImageRepository;
    import com.ProjetJ2EE.ProjetJ2EE.services.AccountService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.Base64;
    import java.util.List;

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

        @RequestMapping("/index")
        public String index() {
            return "index";
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

            return "redirect:/main";
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
            List<Game> games = gameRepository.findAll();

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
                // Ensure that the categorie object is loaded
                game.getCategorie();
                game.getMinSpecs();
                game.getRecSpecs();// This should trigger the lazy loading if necessary

                List<Image> images = game.getImages();
                images.forEach(image -> {
                    String base64Image = bytesToBase64(image.getImage());
                    image.setPictureBase64(base64Image);
                });

                // Accessing the categorieType property
                String categorieType = game.getCategorie().getCategorieType();
                // You can do something with the categorieType here, like adding it to the model
                model.addAttribute("categorieType", categorieType);

                model.addAttribute("game", game);
                return "game-details";
            } else {
                return "error";
            }
        }


    }
