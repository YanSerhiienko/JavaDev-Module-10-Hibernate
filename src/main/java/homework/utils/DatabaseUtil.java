package homework.utils;

import org.flywaydb.core.Flyway;

public class DatabaseUtil {
    public void initDb(String url) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(url, null, null)
                .load();
        flyway.migrate();
    }
}
