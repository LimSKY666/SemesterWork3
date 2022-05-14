package ru.kpfu.itis.sokolov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.sokolov.dto.UserDto;
import ru.kpfu.itis.sokolov.models.User;
import ru.kpfu.itis.sokolov.security.VkAuth;
import ru.kpfu.itis.sokolov.security.details.UserDetailsImpl;
import ru.kpfu.itis.sokolov.services.tourService.TourService;
import ru.kpfu.itis.sokolov.services.userService.UsersService;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SignInController {

    private final UsersService usersService;

    private final TourService tourService;

    @GetMapping("/signIn")
    public String getSignInPage(@RequestParam(value = "error", required = false) String error) {
        if (error != null && error.equals("error")) {
            return "signInFailed";
        }
        return "signIn";
    }

    @GetMapping("/signIn/vk")
    public String loginWithVk(@RequestParam("first_name") String firstName,
                              @RequestParam("last_name") String lastName,
                              @RequestParam("uid") String id,
                              Model model) {

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .email("don't know")
                .tours(Collections.emptyList())
                .hashPassword(id)
                .build();

        Optional<UserDto> userByCode = usersService.getUserByCode(user.getHashPassword());
        if (userByCode.isPresent()) {
            model.addAttribute("user", userByCode.get());
            model.addAttribute("tours", tourService.getALlToursByUserId(userByCode.get().getId()));

            UserDetailsImpl userDetails = new UserDetailsImpl(usersService.getUserById(userByCode.get().getId()));

            Authentication auth = new VkAuth(userDetails);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            Optional<UserDto> userDto = usersService.saveUser(user);
            model.addAttribute("user", userDto.get());
            model.addAttribute("tours", tourService.getALlToursByUserId(userDto.get().getId()));

            UserDetailsImpl userDetails = new UserDetailsImpl(usersService.getUserById(userDto.get().getId()));

            Authentication auth = new VkAuth(userDetails);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        return "account";
    }
}
