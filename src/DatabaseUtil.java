import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    
    public Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://mysql-703314b-rafaelabednego123-d6c9.e.aivencloud.com:14376/tubespbo";
        String USER = "avnadmin";
        String PASSWORD = "AVNS_tDl-qGjjb2oSl3y_i0m";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection getConnectionStatic() throws SQLException {
        String URL = "jdbc:mysql://mysql-703314b-rafaelabednego123-d6c9.e.aivencloud.com:14376/tubespbo";
        String USER = "avnadmin";
        String PASSWORD = "AVNS_tDl-qGjjb2oSl3y_i0m";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
