package com.onlineshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @Getter @Setter
public class ErrorDto {
    HttpStatus httpStatus;
    String message;
}
