package com.example.messaging.controller;

import com.example.messaging.model.User;
import com.example.messaging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created by Sergey Iryupin on 27.04.2018
 * javageek@email.cz
 */
@RequestMapping("users")
@RestController
public class UserController {

    private static final String USER_ALREADY_EXISTS = "User with given name has been already created";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody User user) {
        if (Objects.isNull(user) ||
                Objects.isNull(user.getUserName()) ||
                Objects.isNull(user.getPassword())) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
        }

        User foundUser = userRepository.findByUserName(user.getUserName());
        if (Objects.nonNull(foundUser)) {
            return new ResponseEntity<>(USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        User newUser = User.builder()
                .userName(user.getUserName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(newUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}