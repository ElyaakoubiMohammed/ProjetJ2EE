    package com.ProjetJ2EE.ProjetJ2EE.services;

    import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
    import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.stereotype.Service;


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
    }

