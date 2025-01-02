import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bioskop extends DatabaseUtil {
    private int bioskopID;
    private String nama;
    private int wilayahID;
    

    public Bioskop(int bioskopID, String nama, int wilayahID) {
        this.bioskopID = bioskopID;
        this.nama = nama;
        this.wilayahID = wilayahID;
    }

    public int getBioskopID() {
        return bioskopID;
    }

    public String getNama() {
        return nama;
    }

    public int getWilayahID() {
        return wilayahID;
    }

    public static List<Bioskop> listBioskopByWilayah(String wilayahNama) {
        List<Bioskop> bioskopList = new ArrayList<>();
        String query = "SELECT b.id, b.nama, w.id " +
                       "FROM bioskop b " +
                       "JOIN wilayah w ON b.wilayahID = w.id " +
                       "WHERE w.nama = ?";

        try (Connection connection = getConnectionStatic();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, wilayahNama);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bioskopID = resultSet.getInt("id");
                String namaBioskop = resultSet.getString("nama");
                int wilayahID = resultSet.getInt("id");

                Bioskop bioskop = new Bioskop(bioskopID, namaBioskop, wilayahID);
                bioskopList.add(bioskop);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bioskopList;
    }

    public static boolean tambahBioskop(String namaBioskop, String namaWilayah) {
        String getWilayahIDQuery = "SELECT id FROM wilayah WHERE nama = ?";
        String insertBioskopQuery = "INSERT INTO bioskop (nama, wilayahID) VALUES (?, ?)";

        try (Connection connection = getConnectionStatic()) {

            PreparedStatement getWilayahStatement = connection.prepareStatement(getWilayahIDQuery);
            getWilayahStatement.setString(1, namaWilayah);
            ResultSet wilayahResultSet = getWilayahStatement.executeQuery();

            if (wilayahResultSet.next()) {
                int wilayahID = wilayahResultSet.getInt("id");

                // Insert new bioskop
                PreparedStatement insertBioskopStatement = connection.prepareStatement(insertBioskopQuery);
                insertBioskopStatement.setString(1, namaBioskop);
                insertBioskopStatement.setInt(2, wilayahID);

                int rowsAffected = insertBioskopStatement.executeUpdate();
                return rowsAffected > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void tampilkanBioskopList(List<Bioskop> bioskopList) {
        if (bioskopList.isEmpty()) {
            System.out.println("Tidak ada bioskop yang terdaftar di wilayah ini.");
        } else {
            for (Bioskop bioskop : bioskopList) {
                System.out.println("- " + bioskop.getNama());
            }
        }
    }

    // Method untuk memperbarui nama bioskop berdasarkan nama bioskop dan wilayahnya
public static boolean updateBioskop(String namaBioskopLama, String namaWilayah, String namaBioskopBaru) {
    String getWilayahIDQuery = "SELECT id FROM wilayah WHERE nama = ?";
    String updateBioskopQuery = "UPDATE bioskop SET nama = ? WHERE nama = ? AND wilayahID = ?";

    try (Connection connection = getConnectionStatic()) {

        PreparedStatement getWilayahStatement = connection.prepareStatement(getWilayahIDQuery);
        getWilayahStatement.setString(1, namaWilayah);
        ResultSet wilayahResultSet = getWilayahStatement.executeQuery();

        if (wilayahResultSet.next()) {
            int wilayahID = wilayahResultSet.getInt("id");
            // Memperbarui nama bioskop
            PreparedStatement updateBioskopStatement = connection.prepareStatement(updateBioskopQuery);
            updateBioskopStatement.setString(1, namaBioskopBaru);
            updateBioskopStatement.setString(2, namaBioskopLama);
            updateBioskopStatement.setInt(3, wilayahID);

            int rowsAffected = updateBioskopStatement.executeUpdate();
            return rowsAffected > 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

// Method untuk menghapus bioskop berdasarkan nama bioskop dan wilayahnya
public static boolean deleteBioskop(String namaBioskop, String namaWilayah) {
    String getWilayahIDQuery = "SELECT id FROM wilayah WHERE nama = ?";
    String deleteBioskopQuery = "DELETE FROM bioskop WHERE nama = ? AND wilayahID = ?";

    try (Connection connection = getConnectionStatic()) {

        // Mendapatkan ID wilayah berdasarkan nama wilayah
        PreparedStatement getWilayahStatement = connection.prepareStatement(getWilayahIDQuery);
        getWilayahStatement.setString(1, namaWilayah);
        ResultSet wilayahResultSet = getWilayahStatement.executeQuery();

        if (wilayahResultSet.next()) {
            int wilayahID = wilayahResultSet.getInt("id");

            // Menghapus bioskop
            PreparedStatement deleteBioskopStatement = connection.prepareStatement(deleteBioskopQuery);
            deleteBioskopStatement.setString(1, namaBioskop);
            deleteBioskopStatement.setInt(2, wilayahID);

            int rowsAffected = deleteBioskopStatement.executeUpdate();
            return rowsAffected > 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

    
}
