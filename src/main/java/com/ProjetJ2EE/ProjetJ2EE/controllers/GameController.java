package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import com.ProjetJ2EE.ProjetJ2EE.entities.User;
import com.ProjetJ2EE.ProjetJ2EE.repositories.GameRepository;
import com.ProjetJ2EE.ProjetJ2EE.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class GameController
{
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;


}
