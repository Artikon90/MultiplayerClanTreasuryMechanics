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
import java.util.Arrays;
import java.util.Properties;

public class JdbcConnection {
    private static final Logger logger = LoggerFactory.getLogger(JdbcConnection.class);
    private static final String driver;
    private static final String username;
    private static final String password;
    private static final String url;
    static {
        try (var loader = new FileInputStream("src/main/resources/db.properties")) {
            var prop = new Properties();
            prop.load(loader);
            driver = prop.getProperty("db.driver");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            url = prop.getProperty("db.path");
            Class.forName(driver);
        } catch (FileNotFoundException | ClassNotFoundException e) {
            logger.debug("DB config file not found or invalid JDBC driver");
            throw new RuntimeWrapperException(e.getMessage());
        } catch (IOException e) {
            logger.atError().log("Unsuccessful open input stream");
            throw new RuntimeWrapperException(e.getMessage());
        }
    }
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.atError().log("In process getting connection for db has been" +
                    " throw SQLException with stacktrace: " + Arrays.toString(e.getStackTrace()));
            throw new RuntimeWrapperException(e.getMessage());
        }
    }
}
