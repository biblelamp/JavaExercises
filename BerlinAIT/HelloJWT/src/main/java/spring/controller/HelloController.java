package spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello, Admin";
    }

    @GetMapping("/user/hello")
    public String helloUser() {
        return "Hello, User";
    }

    @GetMapping("/noauth/hello")
    public String hello() {
        return "Hello, Anonimus";
    }
}
