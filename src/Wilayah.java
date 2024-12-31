import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Wilayah {
    private int wilayahID;
    private String nama;

    // Konstruktor
    public Wilayah(int wilayahID, String nama) {
        this.wilayahID = wilayahID;
        this.nama = nama;
    }

    // Getter dan Setter
    public int getWilayahID() {
        return wilayahID;
    }

    public String getNama() {
        return nama;
    }

    public void updateWilayah(String nama) {
        this.nama = nama;
    }

    // Metode statis untuk operasi database

    // Metode untuk mendapatkan semua nama wilayah
    public static List<String> getSemuaNamaWilayah() {
        List<String> namaWilayah = new ArrayList<>();
        String query = "SELECT nama FROM wilayah";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                namaWilayah.add(resultSet.getString("nama"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return namaWilayah;
    }

    // Metode untuk menambahkan wilayah baru
    public static boolean tambahWilayah(String nama) {
        String query = "INSERT INTO wilayah (nama) VALUES (?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nama);
            int barisDimasukkan = statement.executeUpdate();
            return barisDimasukkan > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Metode untuk melakukan update nama wilayah
public static boolean updateWilayah(String namaLama, String namaBaru) {
    String query = "UPDATE wilayah SET nama = ? WHERE nama = ?";

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, namaBaru);
        statement.setString(2, namaLama);
        int barisDiperbarui = statement.executeUpdate();
        return barisDiperbarui > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

// Metode untuk menghapus wilayah
public static boolean deleteWilayah(String namaWilayah) {
    String query = "DELETE FROM wilayah WHERE nama = ?";

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, namaWilayah);
        int barisDihapus = statement.executeUpdate();
        return barisDihapus > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}


}
