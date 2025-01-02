import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Jadwal extends DatabaseUtil {
    private int jadwalID;
    private int filmID;
    private String waktuTayang;

    public Jadwal(int jadwalID,int filmID, String waktuTayang) {
        this.jadwalID = jadwalID;
        this.filmID = filmID;
        this.waktuTayang = waktuTayang;
    }

    // Getter and Setter methods
    public int getJadwalID() {
        return jadwalID;
    }

    public int getFilmID() {
        return filmID;
    }

    public String getWaktuTayang() {
        return waktuTayang;
    }

    public void updateJadwal(String waktuTayang) {
        this.waktuTayang = waktuTayang;
    }

    public List<String> getKursiTersedia() {
        List<String> listKursi = new ArrayList<>();

        String query = "SELECT * FROM tiket WHERE jadwalID = ?";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Menyisipkan nilai ke dalam query
            preparedStatement.setInt(1, jadwalID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listKursi.add(resultSet.getString("nomor_kursi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listKursi;

    }

    public static boolean tambahJadwal(String waktuTayang, String namaFilm, String namaWilayah, String namaBioskop) {
        String queryWilayah = "SELECT id FROM wilayah WHERE nama = ?";
        String queryBioskop = "SELECT id FROM bioskop WHERE nama = ? AND wilayahID = ?";
        String queryFilm = "SELECT id FROM film WHERE bioskopID = ? AND nama = ?";
        String query = "INSERT INTO jadwal (waktu_tayang, filmID) VALUES (?, ?)";

        try (Connection connection = getConnectionStatic();
            PreparedStatement psWilayah = connection.prepareStatement(queryWilayah)) {
    
            // Cari wilayahID berdasarkan namaWilayah
            psWilayah.setString(1, namaWilayah);
            ResultSet rsWilayah = psWilayah.executeQuery();

            if (rsWilayah.next()) {
                int wilayahID = rsWilayah.getInt("id");
                PreparedStatement psBioskop = connection.prepareStatement(queryBioskop);
                psBioskop.setString(1, namaBioskop);
                    psBioskop.setInt(2, wilayahID);
                    ResultSet rsBioskop = psBioskop.executeQuery();

                    if (rsBioskop.next()) {
                        int bioskopID = rsBioskop.getInt("id");
                        PreparedStatement psFilm = connection.prepareStatement(queryFilm);
                        psFilm.setInt(1, bioskopID);
                        psFilm.setString(2, namaFilm);
                        ResultSet rsFilm = psFilm.executeQuery();

                        if (rsFilm.next()) {
                            int filmID = rsFilm.getInt("id");
                            PreparedStatement statement = connection.prepareStatement(query);
               
                           statement.setString(1, waktuTayang);
                           statement.setInt(2, filmID);
    
                           int barisDimasukkan = statement.executeUpdate();
                           return barisDimasukkan > 0;
                        }
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateJadwal(int jadwalID, String waktu_tayang) {
        String query = "UPDATE jadwal SET waktu_tayang = ? WHERE id = ?";

        try (Connection connection =getConnectionStatic();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, waktu_tayang);
            statement.setInt(2, jadwalID);
            int barisDiperbarui = statement.executeUpdate();
            return barisDiperbarui > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteJadwal(int jadwalID) {
        String query = "DELETE FROM jadwal WHERE id = ?";

        try (Connection connection = getConnectionStatic();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, jadwalID);
            int barisDihapus = statement.executeUpdate();
            return barisDihapus > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
