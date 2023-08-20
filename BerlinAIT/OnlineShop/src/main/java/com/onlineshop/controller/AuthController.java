package com.onlineshop.controller;

import com.onlineshop.config.JwtUtil;
import com.onlineshop.controller.dto.ErrorDto;
import com.onlineshop.controller.dto.LoginReqDto;
import com.onlineshop.controller.dto.LoginResDto;
import com.onlineshop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReqDto loginReq)  {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginReq.getLogin(), loginReq.getPassword()));
            String login = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            System.out.println(authorities);
            User user = new User(login, authorities);
            String token = jwtUtil.createToken(user);
            LoginResDto loginRes = new LoginResDto(login, token);

            return ResponseEntity.ok(loginRes);
        } catch (BadCredentialsException e) {
            ErrorDto errorResponse = new ErrorDto(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorDto errorResponse = new ErrorDto(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
