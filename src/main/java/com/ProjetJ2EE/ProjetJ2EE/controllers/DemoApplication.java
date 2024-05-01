    package com.ProjetJ2EE.ProjetJ2EE.controllers;

    import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
    import com.ProjetJ2EE.ProjetJ2EE.entities.User;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.UserRepository;
    import com.ProjetJ2EE.ProjetJ2EE.services.AccountService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @Controller
    public class DemoApplication {
        @Autowired
        private final AccountService accountService;
        @Autowired
        private GameRepository gameRepository;

        @Autowired
        private UserRepository userRepository;

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
        public String register(@ModelAttribute Account account) {
            accountService.addAccount(account.getEmail(), account.getPassword(), account.getUsername(), account.getCountry(), account.getFirstName(), account.getLastName(), account.getAge(), account.getGender());
            return "redirect:/index";
        }

        @GetMapping("/login")
        public String showLoginForm() {
            return "login";
        }

        @PostMapping("/login")
        public String login(@RequestParam String email, @RequestParam String password) {
            if (accountService.validateLogin(email, password)) {
                return "redirect:/index";
            } else {
                return "redirect:/login";
            }
        }
        @RequestMapping("/profile")
        public String Profile() {
            return "profile";
        }

        @RequestMapping("/main")
        public String Acceuil(Model model) {
            List<Game> games = gameRepository.findAll();
            model.addAttribute("games", games);

            List<User> coaches = userRepository.findByUserType("C");
            model.addAttribute("coaches", coaches);
            return "main";
        }


    }
