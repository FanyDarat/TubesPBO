import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://mysql-703314b-rafaelabednego123-d6c9.e.aivencloud.com:14376/tubespbo";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_tDl-qGjjb2oSl3y_i0m";

    public static Connection getConnection() throws SQLException {  
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
