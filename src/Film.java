import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Film extends DatabaseUtil {
    private int filmID;
    private String namaFilm;
    private String genre;
    private int durasi;
    private double harga;

    // Constructor
    public Film(int filmID, String namaFilm, String genre, int durasi, double harga) {
        this.filmID = filmID;
        this.namaFilm = namaFilm;
        this.genre = genre;
        this.durasi = durasi;
        this.harga = harga;
    }

    // Getter methods
    public int getFilmID() {
        return filmID;
    }

    public String getNamaFilm() {
        return namaFilm;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public double getHarga() {
        return harga;
    }


    // Method to add a new film
    public static boolean addFilm(String namaBioskop, String namaWilayah, String namaFilm, String genre, int durasi, double harga) {
        String queryWilayah = "SELECT id FROM wilayah WHERE nama = ?";
        String queryBioskop = "SELECT id FROM bioskop WHERE nama = ? AND wilayahID = ?";
        String queryFilm = "INSERT INTO film (nama, genre, durasi, price, bioskopID) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection connection = new Film(0, null, null, 0, 0).getConnection();
             PreparedStatement psWilayah = connection.prepareStatement(queryWilayah)) {
    
            // Cari wilayahID berdasarkan namaWilayah
            psWilayah.setString(1, namaWilayah);
            ResultSet rsWilayah = psWilayah.executeQuery();
    
            if (rsWilayah.next()) {
                int wilayahID = rsWilayah.getInt("id");
    
                // Cari bioskopID berdasarkan namaBioskop dan wilayahID
                try (PreparedStatement psBioskop = connection.prepareStatement(queryBioskop)) {
                    psBioskop.setString(1, namaBioskop);
                    psBioskop.setInt(2, wilayahID);
                    ResultSet rsBioskop = psBioskop.executeQuery();
    
                    if (rsBioskop.next()) {
                        int bioskopID = rsBioskop.getInt("id");
    
                        // Setelah mendapatkan bioskopID, masukkan film ke tabel film
                        try (PreparedStatement psFilm = connection.prepareStatement(queryFilm)) {
                            psFilm.setString(1, namaFilm);
                            psFilm.setString(2, genre);
                            psFilm.setInt(3, durasi);
                            psFilm.setDouble(4, harga);
                            psFilm.setInt(5, bioskopID);
    
                            int result = psFilm.executeUpdate();
                            return result > 0;
                        }
    
                    } else {
                        System.out.println("Bioskop tidak ditemukan dalam wilayah yang diberikan.");
                    }
    
                }
    
            } else {
                System.out.println("Wilayah tidak ditemukan.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

    // Method to update an existing film
    public static boolean updateFilm(
    String namaFilmLama, String namaBioskop, String namaWilayah,
    String namaFilmBaru, String genreFilmBaru, int durasiFilmBaru, double hargaFilmBaru) {
    
    String query = "UPDATE film " +
                   "SET nama = ?, genre = ?, durasi = ?, price = ? " +
                   "WHERE nama = ? " +
                   "AND bioskopID = (SELECT id FROM bioskop WHERE nama = ? AND wilayahID = " +
                   "(SELECT id FROM wilayah WHERE nama = ?))";

    try (Connection connection = new Film(0, null, null, 0, 0).getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        // Mengisi parameter query
        preparedStatement.setString(1, namaFilmBaru);
        preparedStatement.setString(2, genreFilmBaru);
        preparedStatement.setInt(3, durasiFilmBaru);
        preparedStatement.setDouble(4, hargaFilmBaru);
        preparedStatement.setString(5, namaFilmLama);
        preparedStatement.setString(6, namaBioskop);
        preparedStatement.setString(7, namaWilayah);

        // Eksekusi update
        int result = preparedStatement.executeUpdate();
        return result > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}



    // Method to delete a film
    public static boolean deleteFilm(String namaFilm, String namaBioskop, String namaWilayah) {
        String query = "DELETE FROM film " +
                       "WHERE nama = ? AND bioskopID = (SELECT id FROM bioskop WHERE nama = ? AND wilayahID = " +
                       "(SELECT id FROM wilayah WHERE nama = ?))";
    
        try (Connection connection = new Film(0, null, null, 0, 0).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            // Mengisi parameter query
            preparedStatement.setString(1, namaFilm);
            preparedStatement.setString(2, namaBioskop);
            preparedStatement.setString(3, namaWilayah);
    
            // Eksekusi delete
            int result = preparedStatement.executeUpdate();
            return result > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    // Method to get a list of films from a specific bioskop and wilayah
    public static List<Film> getFilm(String bioskopNama, String wilayahNama) {
        List<Film> films = new ArrayList<>();
        String query = "SELECT f.id, f.nama, f.genre, f.durasi, f.price " +
                       "FROM film f " +
                       "JOIN jadwal j ON f.id = j.filmID " +
                       "JOIN bioskop b ON f.bioskopID = b.id " +
                       "JOIN wilayah w ON b.wilayahID = w.id " +
                       "WHERE b.nama = ? AND w.nama = ?";

        try (Connection connection = new Film(0, null, null, 0, 0).getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, bioskopNama);
            preparedStatement.setString(2, wilayahNama);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int filmID = resultSet.getInt("id");
                String namaFilm = resultSet.getString("nama");
                String genre = resultSet.getString("genre");
                int durasi = resultSet.getInt("durasi");
                double harga = resultSet.getDouble("price");

                films.add(new Film(filmID, namaFilm, genre, durasi, harga));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
}
