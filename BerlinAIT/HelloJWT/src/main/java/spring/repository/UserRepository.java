package spring.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import spring.domain.User;

@Repository
public class UserRepository {

    public User findUserByLogin(String login){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "123456";
        String encodedPassword = passwordEncoder.encode(password);

        return new User(login, password, null);
    }
}
