package com.example.messaging.controller;

import com.example.messaging.model.Message;
import com.example.messaging.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Created by Sergey Iryupin on 27.04.2018
 * javageek@email.cz
 */
@RestController
@RequestMapping(value = "messages")
public class MessageController {

    private static final String ANONYMOUS_USER = "anonymousUser";

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "create")
    public ResponseEntity<String> create(@RequestBody String message) {
        if (Objects.isNull(message)) {
            return new ResponseEntity<>("No message to create given", HttpStatus.BAD_REQUEST);
        }

        if (ANONYMOUS_USER.equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return new ResponseEntity<>("Not authenticated", HttpStatus.UNAUTHORIZED);
        }

        messageService.create(message);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "all")
    public ResponseEntity<List<Message>> listAll() {
        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "own")
    public ResponseEntity<List<Message>> findAllByUser() {
        return new ResponseEntity<>(messageService.findAllForCurrentUser(), HttpStatus.OK);
    }

}