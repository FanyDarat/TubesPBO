import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int userID;
    private String username;
    private String email;
    private String password;
    private String telepon;

    public User(int userID, String username, String email, String password, String telepon) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telepon = telepon;
    }

    // Register user
    public boolean registerUser(String username, String email, String password, String telepon) {
        String query = "INSERT INTO user(username, email, password, telepon) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, telepon);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login user
    public static User loginUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String telepon = resultSet.getString("telepon");
                String usernameFromDB = resultSet.getString("username");
                return new User(userID, usernameFromDB, email, password, telepon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelepon() {
        return telepon;
    }
}
