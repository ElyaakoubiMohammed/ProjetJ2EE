    package com.ProjetJ2EE.ProjetJ2EE.controllers;

    import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Comment;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
    import com.ProjetJ2EE.ProjetJ2EE.entities.Image;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.ImageRepository;
    import com.ProjetJ2EE.ProjetJ2EE.services.AccountService;
    import com.ProjetJ2EE.ProjetJ2EE.services.JavaSmtpGmailSenderService;
    import org.apache.catalina.connector.ClientAbortException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.mail.SimpleMailMessage;
    import org.springframework.mail.javamail.JavaMailSender;
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
        private JavaSmtpGmailSenderService senderService;

        @Autowired
        public DemoApplication(AccountService accountService) {
            this.accountService = accountService;
        }

        @GetMapping("/register")
        public String showRegistrationForm() {
            return "register";
        }

        @PostMapping("/register")
        public String register(@ModelAttribute Account account, Model model, @RequestParam("imageFile") MultipartFile imageFile) {
            if (!accountService.addAccount(account.getEmail(), account.getPassword(), account.getUsername(), account.getCountry(), account.getFirstName(), account.getLastName(), account.getAge(), account.getGender(), imageFile)) {
                model.addAttribute("error", "Email or username already exists");
                return "register";
            }
            senderService.sendEmail(account.getEmail(), "Welcome to NexaPlay - Your Ultimate Gaming Destination!",
                    "Dear " + account.getUsername() + ",\n\n" +
                            "Welcome to NexaPlay, your ultimate destination for gaming excitement and adventure! We are absolutely delighted to have you join our ever-growing community of gamers.\n\n" +
                            "Here at NexaPlay, we're passionate about gaming and committed to providing you with an unparalleled gaming experience. From heart-pounding action to brain-teasing puzzles, our extensive library of games has something for every taste and preference.\n\n" +
                            "Your account setup is now complete, and here are your account details:\n" +
                            "Email Address: " + account.getEmail() + "\n" +
                            "Username: " + account.getUsername() + "\n\n" +
                            "As a valued member of NexaPlay, you'll have access to exclusive features, exciting challenges, and a vibrant community of fellow gamers. Take your time to explore all that NexaPlay has to offer and embark on unforgettable gaming journeys.\n\n" +
                            "But wait, there's more! In addition to our vast collection of games, we also offer regular tournaments, events, and special promotions to keep the excitement level high. Be sure to stay tuned for upcoming announcements and opportunities to showcase your gaming skills.\n\n" +
                            "Should you ever need assistance, our dedicated support team is here to help. Whether you have questions about a game, encounter technical issues, or simply want to share your feedback, we're just a click away.\n\n" +
                            "Once again, welcome to NexaPlay! Get ready to immerse yourself in a world of endless gaming possibilities and unforgettable moments.\n\n" +
                            "Best regards,\n" +
                            "The NexaPlay Team");

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
            List<Game> allGames = gameRepository.findAll();
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

        @GetMapping("/profile2/{userId}")
        public String profile2(@PathVariable Long userId, Model model) {
            Optional<Account> userOptional = accountRepository.findById(userId);

            if (userOptional.isPresent()) {
                Account user = userOptional.get();
                byte[] userImage = user.getImage();
                if (userImage != null) {
                    String base64Image = bytesToBase64(userImage);
                    user.setPictureBase64(base64Image);
                }
                model.addAttribute("user", user);
                return "profile2";
            } else {
                return "login";
            }
        }

        @PostMapping("/deleteUser")
        public String delete(@RequestParam("userId") Long userId) {
            accountService.deleteUserById(userId);
            return "redirect:/userslistA";
        }

        @GetMapping("/editAccount/{accountId}")
        public String editAccount(@PathVariable Long accountId, Model model) {

            Account account = accountService.getAccountById(accountId);

            model.addAttribute("account", account);

            return "editAccount";
        }
        @PostMapping("/editAccount/{accountId}")
        public String updateAccount(@PathVariable Long accountId, @ModelAttribute Account updatedAccount, @RequestParam("imageFile") MultipartFile imageFile) {
            accountService.updateAccount(accountId, updatedAccount, imageFile);
            return "redirect:/userslist";
        }

    }
