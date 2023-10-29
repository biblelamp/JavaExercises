package de.pizza.tomate.controller;

import de.pizza.tomate.config.JwtUtil;
import de.pizza.tomate.controller.dto.*;
import de.pizza.tomate.service.IngredientService;
import de.pizza.tomate.service.PizzaBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/noauth")
public class NoAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PizzaBaseService pizzaBaseService;

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginReq)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getLogin(), loginReq.getPassword()));
            String login = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            UserJWT user = new UserJWT(login, authorities);
            String token = jwtUtil.createToken(user);
            LoginResponse response = new LoginResponse(login, token);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid login or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/pizzas")
    public List<PizzaBaseDTO> findAllPizzas() {
        return pizzaBaseService.findAll();
    }

    @GetMapping("/ingredients")
    public List<IngredientDTO> findAllIngredients() {
        return ingredientService.findAll();
    }
}
