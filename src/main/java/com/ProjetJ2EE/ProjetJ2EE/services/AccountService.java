    package com.ProjetJ2EE.ProjetJ2EE.services;

    import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.StringUtils;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.Base64;
    import java.util.Objects;
    import java.util.Optional;


    @Service
    public class AccountService {

        private final AccountRepository accountRepository;
        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public AccountService(AccountRepository accountRepository, JdbcTemplate jdbcTemplate) {
            this.accountRepository = accountRepository;
            this.jdbcTemplate = jdbcTemplate;
        }
        public boolean addAccount(String email, String password, String username, String country, String firstName, String lastName, int age, String gender) {
            if (accountRepository.existsByemail(email) || accountRepository.existsByusername(username)) {
                return false;
            }
            Account account = Account.builder()
                    .email(email)
                    .Password(password)
                    .username(username)
                    .Country(country)
                    .FirstName(firstName)
                    .LastName(lastName)
                    .Age(age)
                    .Gender(gender)
                    .build();

            accountRepository.save(account);
            return true;
        }
        public boolean validateLogin(String email, String password) {
            String query = "SELECT COUNT(*) FROM account WHERE email = ? AND password = ?";

            int count = jdbcTemplate.queryForObject(query, Integer.class, email, password);

            return count > 0;
        }

        @Transactional
        public boolean addPicture(String email, MultipartFile profilePicture) throws IOException {
                Optional<Account> optionalAccount = accountRepository.findByEmail(email);
            if (optionalAccount.isEmpty()) {
                return false;
            }

            Account account = optionalAccount.get();

            if (profilePicture != null && !profilePicture.isEmpty()) {
                account.setImage(profilePicture.getBytes());
                account.setPictureBase64(Base64.getEncoder().encodeToString(profilePicture.getBytes()));

                accountRepository.save(account);
                return true;
            }

            return false;
        }

        public void deleteUserById(Long userId) {
            accountRepository.deleteById(userId);
        }
    }

