package com.ProjetJ2EE.ProjetJ2EE.repositories;
import com.ProjetJ2EE.ProjetJ2EE.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>
{

    List<User> findByUserType(String c);
}
