package de.pizza.tomate.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class UserJWT {
    private final String login;
    private final Collection<? extends GrantedAuthority> authorities;
}
