package ru.kpfu.itis.sokolov.services.userService;

import ru.kpfu.itis.sokolov.dto.UserDto;
import ru.kpfu.itis.sokolov.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserDto> getAllUsers();

    void unbanById(Long id);

    void banById(Long id);

    Optional<UserDto> getUserByEmail(String email);

    Optional<UserDto> saveUser(User user);

    Optional<UserDto> getUserByCode(String code);

    void deleteUser(Long id);

    UserDto updateUser(User user);

    User getUserById(Long id);
}
