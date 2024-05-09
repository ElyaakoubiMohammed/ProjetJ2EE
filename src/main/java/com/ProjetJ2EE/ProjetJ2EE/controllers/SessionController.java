package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
import com.ProjetJ2EE.ProjetJ2EE.entities.Session;
import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.SessionRepository;
import com.ProjetJ2EE.ProjetJ2EE.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SessionController {

    @Autowired
    private  SessionRepository sessionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SessionService sessionService;
    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping("/booksession")
    public String ShowBook(Model model)
    {
        List<Account> coaches = accountRepository.findByusertype("C");
        model.addAttribute("coaches", coaches);
        return "booksession";
    }
    @PostMapping("/booksessionadd")
    public String bookSession(@RequestParam("level") String level,
                              @RequestParam("game") String game,
                              @RequestParam("coachId") Long coachId) {

        sessionService.addSession(game, level, coachId);
        return "redirect:/main";
    }


    @GetMapping("/coach/index")
    public String session(Model model) {
        List<Session> sessions = sessionRepository.findAll();
        model.addAttribute("sessions", sessions);
        return "coach";
    }
}