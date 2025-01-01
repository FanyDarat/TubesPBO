import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class User extends DatabaseUtil {
    private int userID;
    private String username;
    private String email;
    private String password;
    private String telepon;
    private int role;

    public User(int userID, String username, String email, String password, String telepon, int role) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telepon = telepon;
        this.role = role;
    }

    public boolean registerUser(String username, String email, String password, String telepon) {
        String query = "INSERT INTO user(username, email, password, telepon) VALUES (?, ?, ?, ?)";

        try ( // Menggunakan metode getConnection dari interface DatabaseUtil
             PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());  // Hash the password

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, hashedPassword);  // Store the hashed password
            preparedStatement.setString(4, telepon);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error during registration: " + e.getMessage());
            return false;
        }
    }

    public static User loginUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = new User(0, null, null, null, null, 0).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedHashedPassword = resultSet.getString("password");

                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    int userID = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String telepon = resultSet.getString("telepon");
                    String usernameFromDB = resultSet.getString("username");
                    int role = resultSet.getInt("role");
                    return new User(userID, usernameFromDB, email, storedHashedPassword, telepon, role);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error during login: " + e.getMessage());
        }
        return null;
    }

    public void topUpSaldo(double jumlahSaldo) {
        String query = "UPDATE user SET saldo = saldo + ? WHERE id = ?";
        try (Connection connection = new User(0, null, null, null, null, 0).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, jumlahSaldo);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error during top-up: " + e.getMessage());
        }

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

    public int getRole() {
        return role;
    }

    public int getUserID() {
        return userID;
    }
}
