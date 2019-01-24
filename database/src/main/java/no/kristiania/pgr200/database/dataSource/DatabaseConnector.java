package no.kristiania.pgr200.database.dataSource;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.jdbc2.optional.PoolingDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnector {

    private Properties prop = new Properties();
    private InputStream input = null;

    public DatabaseConnector() {
    }

    public DataSource connect() {

/**
 * Husk å skriv i read me hvor properties filen er
 */
        try {
            String fileName = "innlevering.properties";

            input = getClass().getClassLoader().getResourceAsStream(fileName);

            //Load the properties file
            prop.load(input);

        } catch (IOException e) {
            System.out.println("Finner ikke properties fil");
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        PGPoolingDataSource dataSource = new PoolingDataSource();
        dataSource.setURL(prop.getProperty("dataSource.url"));
        dataSource.setUser(prop.getProperty("dataSource.username"));
        dataSource.setPassword(prop.getProperty("dataSource.password"));

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();

        System.out.println("Database Connected");

        return dataSource;
    }

    public void resetDb(DataSource datasource) throws SQLException {
        Flyway flyway = new Flyway();
        flyway.setDataSource(datasource);
        flyway.clean();
        connect();
    }
}
