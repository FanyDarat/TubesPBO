import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Jadwal extends DatabaseUtil {
    private int jadwalID;
    private int bioskopID;
    private int filmID;
    private LocalDateTime waktuTayang;
    private double harga;
    private List<Tiket> listTiket;

    public Jadwal(int jadwalID, int bioskopID, int filmID, LocalDateTime waktuTayang, double harga) {
        this.jadwalID = jadwalID;
        this.bioskopID = bioskopID;
        this.filmID = filmID;
        this.waktuTayang = waktuTayang;
        this.harga = harga;
        this.listTiket = new ArrayList<>();
    }

    // Getter and Setter methods
    public int getJadwalID() {
        return jadwalID;
    }

    public int getBioskopID() {
        return bioskopID;
    }

    public int getFilmID() {
        return filmID;
    }

    public LocalDateTime getWaktuTayang() {
        return waktuTayang;
    }

    public double getHarga() {
        return harga;
    }

    public List<Tiket> getListTiket() {
        return listTiket;
    }

    public void updateJadwal(LocalDateTime waktuTayang, double harga) {
        this.waktuTayang = waktuTayang;
        this.harga = harga;
    }

    // Retrieve Jadwal records filtered by bioskopID
    public List<Jadwal> filterByBioskop(int bioskopID) {
        List<Jadwal> result = new ArrayList<>();
        String query = "SELECT * FROM jadwal WHERE bioskopID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bioskopID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(mapResultSetToJadwal(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Retrieve Jadwal records filtered by filmID
    public List<Jadwal> filterByFilm(int filmID) {
        List<Jadwal> result = new ArrayList<>();
        String query = "SELECT * FROM jadwal WHERE filmID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, filmID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(mapResultSetToJadwal(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Helper method to map a ResultSet to a Jadwal object
    private Jadwal mapResultSetToJadwal(ResultSet resultSet) throws SQLException {
        int jadwalID = resultSet.getInt("jadwalID");
        int bioskopID = resultSet.getInt("bioskopID");
        int filmID = resultSet.getInt("filmID");
        LocalDateTime waktuTayang = resultSet.getTimestamp("waktuTayang").toLocalDateTime();
        double harga = resultSet.getDouble("harga");

        return new Jadwal(jadwalID, bioskopID, filmID, waktuTayang, harga);
    }
}
