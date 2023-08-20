package com.onlineshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Collection;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String login;
    private String password;
    private String role;
    private String firstName;
    private String lastName;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(String login, Collection<? extends GrantedAuthority> authorities) {
        this.login = login;
        this.authorities = authorities;
    }
}
