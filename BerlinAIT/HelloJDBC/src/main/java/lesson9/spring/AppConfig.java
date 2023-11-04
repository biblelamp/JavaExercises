package lesson9.spring;

import lesson9.EventDAO;
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
        pgDataSource.setDatabaseName("cohort29");
        pgDataSource.setUser("postgres");
        pgDataSource.setPassword("root");
        return pgDataSource;
    }

    @Bean
    public EventDAO evenDAO() {
        return new EventDAO(dataSource());
    }
}
