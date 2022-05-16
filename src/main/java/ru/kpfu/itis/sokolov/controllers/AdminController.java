package ru.kpfu.itis.sokolov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.sokolov.services.userService.UsersService;

import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAccountPage(Model model) {
        model.addAttribute("users", usersService.getAllUsers().stream()
                .filter(user -> user.getRole().equals("USER"))
                .collect(Collectors.toList()));
        return "admin_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/ban/{id}", method = RequestMethod.POST)
    public String banUser(@PathVariable Long id) {
        usersService.banById(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/unban/{id}", method = RequestMethod.POST)
    public String unbanUser(@PathVariable Long id) {
        usersService.unbanById(id);
        return "redirect:/admin";
    }

    @GetMapping("/accessDenied")
    public String getPage() {
        return "accessDenied";
    }
}
