import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3308/TubesPBO";
    private static final String USER = "root";
    private static final String PASSWORD = "rafael123";

    // Singleton Connection instance
    private static Connection connection;

    // Method to get the database connection
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
