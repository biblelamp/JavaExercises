package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PasswordEncoderApp {
    public static void main(String[] args) {
        SpringApplication.run(PasswordEncoderApp.class, args);

        String password = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encodedPassword);
    }
}
