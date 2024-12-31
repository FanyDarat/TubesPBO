import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bioskop {
    private int bioskopID;
    private String nama;
    private int wilayahID;
    private static final String URL = "jdbc:mysql://mysql-703314b-rafaelabednego123-d6c9.e.aivencloud.com:14376/tubespbo";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_tDl-qGjjb2oSl3y_i0m";

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

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

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
}
