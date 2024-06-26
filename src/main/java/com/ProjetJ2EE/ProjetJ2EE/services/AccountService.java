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
        @Transactional
        public boolean addAccount(String email, String password, String username, String country, String firstName, String lastName, int age, String gender, MultipartFile imageFile) {

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

            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    byte[] imageData = imageFile.getBytes();
                    account.setImage(imageData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return true;
        }

        public boolean validateLogin(String email, String password) {
            String query = "SELECT COUNT(*) FROM account WHERE email = ? AND password = ?";

            int count = jdbcTemplate.queryForObject(query, Integer.class, email, password);

            return count > 0;
        }

        public void deleteUserById(Long userId) {
            accountRepository.deleteById(userId);
        }


        public Account getAccountById(Long accountId) {
            return accountRepository.findById(accountId).orElse(null);
        }

        public void updateAccount(Long accountId, Account updatedAccount, MultipartFile imageFile) {
            Account existingAccount = accountRepository.findById(accountId).orElse(null);
            if (existingAccount != null) {
                existingAccount.setUsername(updatedAccount.getUsername());
                existingAccount.setEmail(updatedAccount.getEmail());
                existingAccount.setPassword(updatedAccount.getPassword());
                existingAccount.setCountry(updatedAccount.getCountry());
                existingAccount.setFirstName(updatedAccount.getFirstName());
                existingAccount.setLastName(updatedAccount.getLastName());
                existingAccount.setAge(updatedAccount.getAge());
                existingAccount.setGender(updatedAccount.getGender());

                if (imageFile != null && !imageFile.isEmpty()) {
                    try {
                        byte[] imageData = imageFile.getBytes();
                        existingAccount.setImage(imageData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                accountRepository.save(existingAccount);
            }
        }


    }

