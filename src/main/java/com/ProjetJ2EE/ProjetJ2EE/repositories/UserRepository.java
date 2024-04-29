package com.ProjetJ2EE.ProjetJ2EE.repositories;
import com.ProjetJ2EE.ProjetJ2EE.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{

}
