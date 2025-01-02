import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order extends DatabaseUtil implements Pembayaran {
    private String orderID;
    private ArrayList<Tiket> listTiket;

    public Order() {
        listTiket = new ArrayList<>();
        long currentTime = System.nanoTime();
        String uniqueID = String.valueOf(currentTime);
        orderID = "BOOK-" + uniqueID;

        String query = "INSERT INTO `order` (id,total_harga) VALUES (?,?)";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Menyisipkan nilai ke dalam query
            preparedStatement.setString(1, orderID);
            preparedStatement.setDouble(2, 0);
            // Menjalankan query
            int result = preparedStatement.executeUpdate();

            // Memeriksa apakah query berhasil
            if (result > 0) {

            } else {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTiket(Tiket tiket) {
        listTiket.add(tiket);
    }

    public void deleteTiket(Tiket tiket) {
        listTiket.remove(tiket);
    }

    public void deleteTiket(int index) {
        listTiket.remove(index);
    }

    public double getHarga(int filmID) {
        String query = "SELECT * FROM film WHERE id = ?";
        double price = 0;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Menyisipkan nilai ke dalam query
            preparedStatement.setInt(1, filmID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                price = resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    public boolean cekSaldo(double totalHarga) {
        String query = "SELECT * FROM user WHERE id = ?";
        boolean check = true;
        try (Connection connection = new User(0, null, null, null, null, 0, 0).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, listTiket.get(0).getUserID());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double saldo = resultSet.getDouble("saldo");
                if (saldo - totalHarga < 0) {
                    check = false;
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error during login: " + e.getMessage());
        }

        return check;
    }

    @Override
    public boolean prosesPembayaran(int filmID) {
        double hargaFilm = getHarga(filmID);
        double totalHarga = hargaFilm * listTiket.size();
        if (!cekSaldo(totalHarga)) {
            return false;
        }
        listTiket.forEach(tickets -> {
            String nomorKursi = tickets.getNomorKursi();
            int userID = tickets.getUserID();
            int jadwalID = tickets.getJadwalID();

            String query = "INSERT INTO tiket (nomor_kursi, userID, jadwalID, orderID) VALUES (?, ?, ?, ?)";

            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                // Menyisipkan nilai ke dalam query
                preparedStatement.setString(1, nomorKursi);
                preparedStatement.setInt(2, userID);
                preparedStatement.setInt(3, jadwalID);
                preparedStatement.setString(4, orderID);
                // Menjalankan query
                int result = preparedStatement.executeUpdate();

                // Memeriksa apakah query berhasil
                if (result > 0) {

                } else {
                    
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        String query = "UPDATE `order` SET total_harga = ? WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Menyisipkan nilai ke dalam query
            preparedStatement.setDouble(1, totalHarga);
            preparedStatement.setString(2, orderID);

            // Menjalankan query
            int result = preparedStatement.executeUpdate();

            // Memeriksa apakah query berhasil
            if (result > 0) {

            } else {
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String queryUser = "UPDATE user SET saldo = saldo - ? WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queryUser)) {

            // Menyisipkan nilai ke dalam query
            preparedStatement.setDouble(1, totalHarga);
            preparedStatement.setInt(2, listTiket.get(0).getUserID());

            // Menjalankan query
            int result = preparedStatement.executeUpdate();

            // Memeriksa apakah query berhasil
            if (result > 0) {

            } else {
                System.out.println("Gagal menambahkan tiket.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
