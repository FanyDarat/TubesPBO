import java.sql.*;

public class Main {
    public static void main(String[] args) {


        String query = "SELECT * FROM user";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("Data: " + resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}