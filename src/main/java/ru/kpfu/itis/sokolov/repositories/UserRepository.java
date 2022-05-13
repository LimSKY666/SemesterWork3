package ru.kpfu.itis.sokolov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.sokolov.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByHashPassword(String pass);
    Optional<User> findUserById(Long id);
}
