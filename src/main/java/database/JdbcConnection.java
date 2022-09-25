package database;

import exception.RuntimeWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {
    private static final Logger logger = LoggerFactory.getLogger(JdbcConnection.class);
    private static final String driver;
    private static final String username;
    private static final String password;
    private static final String url;
    private static Connection connection;
    static {
        try (var loader = new FileInputStream("src/main/resources/db.properties")) {
            var prop = new Properties();
            prop.load(loader);
            driver = prop.getProperty("db.driver");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            url = prop.getProperty("db.path");
        } catch (FileNotFoundException e) {
            logger.debug("DB config file not found or invalid JDBC driver");
            throw new RuntimeWrapperException(e.getMessage());
        } catch (IOException e) {
            logger.atError().log("Unsuccessful open input stream");
            throw new RuntimeWrapperException(e.getMessage());
        }
    }
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                logger.atError().setCause(e).log("Failed to connecting to DB, probably invalid url or username/pass");
                throw new RuntimeWrapperException(e.getMessage(), e);
            } catch (ClassNotFoundException e) {
                logger.atError().setCause(e).log("Not found JDBC driver, specified is {}", driver);
                throw new RuntimeWrapperException(e.getMessage(), e);
            }
        }
        return connection;
    }
}
