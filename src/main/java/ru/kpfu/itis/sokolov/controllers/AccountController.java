package ru.kpfu.itis.sokolov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.sokolov.security.details.UserDetailsImpl;
import ru.kpfu.itis.sokolov.services.signUpService.SignUpService;
import ru.kpfu.itis.sokolov.services.tourService.TourService;

@Controller
public class AccountController {

    @Autowired
    private TourService tourService;

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/account")
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("tours", tourService.getALlToursByUserId(userDetails.getUser().getId()));
        model.addAttribute("user", signUpService.getUserById(userDetails.getUser().getId()));
        return "account";
    }
}
