package com.ProjetJ2EE.ProjetJ2EE.controllers;

import com.ProjetJ2EE.ProjetJ2EE.entities.Account;
import com.ProjetJ2EE.ProjetJ2EE.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;
import java.util.List;
@Controller
public class AdminController
{
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/userslistA")
    public String usersList(Model model) {
        List<Account> users = accountRepository.findAll();

        users.forEach(user -> {
            byte[] userImage = user.getImage();
            if (userImage != null) {
                String base64Image = bytesToBase64(userImage);
                user.setPictureBase64(base64Image);
            }
        });

        model.addAttribute("users", users);
        return "userslistA";
    }
    public String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}
