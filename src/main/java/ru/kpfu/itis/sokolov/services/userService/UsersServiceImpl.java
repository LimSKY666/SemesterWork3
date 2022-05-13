package ru.kpfu.itis.sokolov.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.sokolov.dto.UserDto;
import ru.kpfu.itis.sokolov.models.User;
import ru.kpfu.itis.sokolov.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(userRepository.findAll());
    }

    @Override
    public void banById(Long id) {
        User user = userRepository.getOne(id);
        if (!user.isAdmin()) {
            user.setState(User.State.BANNED);
            userRepository.save(user);
        }
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(UserDto::from);
    }

    @Override
    public Optional<UserDto> saveUser(User user) {
        return Optional.of(UserDto.from(userRepository.save(user)));
    }

    @Override
    public Optional<UserDto> getUserByCode(String code) {
        Optional<User> user = userRepository.findByHashPassword(code);

        return user.map(UserDto::from);
    }

    @Override
    public void unbanById(Long id) {
        User user = userRepository.getOne(id);
        if (!user.isAdmin()) {
            user.setState(User.State.ACTIVE);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(User user) {
        return UserDto.from(userRepository.save(user));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElse(null);
    }
}
