package spring.repository;

import org.springframework.stereotype.Repository;
import spring.domain.User;

@Repository
public class UserRepository {

    public User findByLogin(String login) {
        String password = "123456";
        return new User(login, password, null);
    }
}
