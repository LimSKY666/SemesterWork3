package ru.kpfu.itis.sokolov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @NotEmpty(message = "{errors.incorrect.empty}")
    private String firstName;

    @NotEmpty(message = "{errors.incorrect.empty}")
    private String lastName;

    @Email(message = "{errors.incorrect.email}")
    private String email;

    private String password;
}
