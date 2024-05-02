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
                // If email or username already exists, add an attribute to indicate the error
                model.addAttribute("error", "Email or username already exists");
                // Return the register page
                return "register"; // Assuming your register page is named "register"
            }
            // If account added successfully, redirect to another page
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
                    String base64Image = bytesToBase64(image.getImage()); // Assuming you have a method to convert bytes to Base64
                    image.setPictureBase64(base64Image);
                });
            });
            model.addAttribute("games", games);

            List<Account> coaches = accountRepository.findByusertype("C");
            model.addAttribute("coaches", coaches);

            return "main";
        }
        public String bytesToBase64(byte[] bytes) {
            return Base64.getEncoder().encodeToString(bytes);
        }

    }
