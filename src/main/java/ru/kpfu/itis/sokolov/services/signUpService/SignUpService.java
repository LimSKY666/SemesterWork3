package ru.kpfu.itis.sokolov.services.signUpService;

import ru.kpfu.itis.sokolov.dto.UserForm;
import ru.kpfu.itis.sokolov.models.User;

public interface SignUpService {

    void signUp(UserForm form);

    User getUserById(Long userId);

}
