package de.pizza.tomate.controller.dto;


import de.pizza.tomate.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String login;

    public static UserDTO getInstance(User user) {
        return new UserDTO(user.getId(), user.getLogin());
    }
}
