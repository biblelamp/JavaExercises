package com.example.messaging.repository;

import com.example.messaging.model.Message;
import com.example.messaging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Sergey Iryupin on 27.04.2018
 * javageek@email.cz
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByAuthor(User user);
}