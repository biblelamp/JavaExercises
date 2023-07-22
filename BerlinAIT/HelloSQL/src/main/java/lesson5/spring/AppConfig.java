package lesson5.spring;

import lesson5.UserDAO;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
        pgDataSource.setServerName("localhost");
        pgDataSource.setDatabaseName("ait_tr");
        pgDataSource.setUser("postgres");
        pgDataSource.setPassword("root");
        return pgDataSource;
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource());
    }
}
