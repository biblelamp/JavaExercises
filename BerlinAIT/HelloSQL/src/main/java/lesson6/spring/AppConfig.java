package lesson6.spring;

import lesson6.UserDAO;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource pgDS = new PGSimpleDataSource();
        pgDS.setServerName("localhost");
        pgDS.setDatabaseName("cohort26");
        pgDS.setUser("postgres");
        pgDS.setPassword("root");
        return pgDS;
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource());
    }
}
