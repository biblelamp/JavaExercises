package com.example.messaging.repository;

import com.example.messaging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sergey Iryupin on 27.04.2018
 * javageek@email.cz
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}