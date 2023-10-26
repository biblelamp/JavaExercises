package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.UserDTO;
import de.pizza.tomate.domain.User;
import de.pizza.tomate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO add(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());

        return UserDTO.getInstance(userRepository.save(user));
    }
}
