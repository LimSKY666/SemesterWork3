package ru.kpfu.itis.sokolov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.sokolov.models.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String state;
    private String hashPassword;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .state(user.getState().name())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }

    public boolean isActive() {
        return this.state.equals("ACTIVE");
    }

    public static User to(UserDto userDto) {
        return User.builder()
                .hashPassword(userDto.getHashPassword())
                .tours(Collections.emptyList())
                .role(User.Role.valueOf(userDto.getRole()))
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .email(userDto.getEmail())
                .state(User.State.valueOf(userDto.getState()))
                .build();
    }
}
