package com.ProjetJ2EE.ProjetJ2EE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.ProjetJ2EE.ProjetJ2EE.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.usertype = :usertype")
    List<Account> findByusertype(String usertype);
}
