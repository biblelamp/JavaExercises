package com.example.messaging.service;

import com.example.messaging.model.Message;
import com.example.messaging.model.User;
import com.example.messaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Sergey Iryupin on 27.04.2018
 * javageek@email.cz
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public void create(String message) {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findByUserName(principal.getUsername());

        Message newlyCreatedMessage = Message.builder().author(user).text(message).build();
        messageRepository.save(newlyCreatedMessage);
        messageRepository.flush();
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<Message> findAllForCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.isNull(principal)) {
            throw new UsernameNotFoundException("No current user");
        }
        User user = (User) principal;

        return messageRepository.findAllByAuthor(user);
    }

}