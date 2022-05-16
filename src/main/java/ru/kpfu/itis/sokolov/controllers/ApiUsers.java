package ru.kpfu.itis.sokolov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.sokolov.dto.UserDto;
import ru.kpfu.itis.sokolov.models.User;
import ru.kpfu.itis.sokolov.services.userService.UsersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class ApiUsers {

    private final UsersService usersService;

    @GetMapping
    public UserDto getUserByEmail(@RequestParam(value = "email",required = false) String email) {
        Optional<UserDto> userByEmail = usersService.getUserByEmail(email);
        return userByEmail.orElse(null);
    }

    @PutMapping("/ban/{id}")
    public List<UserDto> banUserById(@PathVariable("id") Long id) {
        usersService.banById(id);
        return usersService.getAllUsers().stream()
                .filter(userDto -> userDto.getRole().equals("USER"))
                .collect(Collectors.toList());
    }

    @PutMapping("/unban/{id}")
    public List<UserDto> unBanUserById(@PathVariable("id") Long id) {
        usersService.unbanById(id);
        return usersService.getAllUsers().stream()
                .filter(userDto -> userDto.getRole().equals("USER"))
                .collect(Collectors.toList());
    }

    @PutMapping
    public UserDto updateUser(User user) {
        return usersService.updateUser(user);
    }

    @PostMapping
    public UserDto createUser(User user) {
        return usersService.saveUser(user).orElse(new UserDto());
    }

    @DeleteMapping
    public void deleteUser(Long id) {
        usersService.deleteUser(id);
    }
}
