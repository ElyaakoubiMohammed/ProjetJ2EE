package com.ProjetJ2EE.ProjetJ2EE;

import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import com.ProjetJ2EE.ProjetJ2EE.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetJ2EeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetJ2EeApplication.class, args);
	}

	@Override
	public void run(String... args) {

	}
}
