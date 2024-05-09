package com.ProjetJ2EE.ProjetJ2EE.services;

import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.Session;
import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService
{
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GameRepository gameRepository;
    @Transactional
    public void addSession(String game, String l, Long coachId) {
        Session s = new Session();

        switch (l) {
            case "beginner":
                s.setPrix(120);
                break;
            case "intermediate":
                s.setPrix(199);
                break;
            case "advanced":
                s.setPrix(250);
                break;
        }
        s.setGameName(game);

        // Set coach ID
        Account coach = accountRepository.findById(coachId).orElse(null);
        if (coach != null) {
            s.setCoach(coach);
        } else {

        }

        sessionRepository.save(s);
    }


}
