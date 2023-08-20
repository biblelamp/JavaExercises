package com.onlineshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class LoginResDto {
    private String login;
    private String token;
}
