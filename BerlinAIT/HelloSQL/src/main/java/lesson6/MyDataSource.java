package lesson6;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class MyDataSource {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            // prepare connect to database
            PGSimpleDataSource pgDS = new PGSimpleDataSource();
            pgDS.setServerName("localhost");
            pgDS.setDatabaseName("cohort26");
            pgDS.setUser("postgres");
            pgDS.setPassword("root");
            dataSource = pgDS;
        }
        return dataSource;
    }
}
