package lesson5;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class MyDataSource {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            // настройка соединения с базой данных
            PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
            pgDataSource.setServerName("localhost");
            pgDataSource.setDatabaseName("ait_tr");
            pgDataSource.setUser("postgres");
            pgDataSource.setPassword("root");
            dataSource = pgDataSource;
        }
        return dataSource;
    }
}
