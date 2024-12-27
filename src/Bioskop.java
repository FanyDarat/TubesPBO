import java.util.List;

public class Bioskop {
    private int bioskopID;
    private String nama;
    private int wilayahID;
    private List<Jadwal> listJadwal;
    private List<Film> listFilm;
    private List<Tiket> listTiket;

    public Bioskop(int bioskopID, String nama, int wilayahID) {
        this.bioskopID = bioskopID;
        this.nama = nama;
        this.wilayahID = wilayahID;
    }

    public int getBioskop() {
        return bioskopID;
    }

    public List<Jadwal> findByWilayah(int wilayahID) {
        // Placeholder for finding schedules by wilayahID
        return listJadwal;
    }

    public void updateBioskop(String nama) {
        this.nama = nama;
    }

    public List<Jadwal> listJadwalByBioskop() {
        return listJadwal;
    }

    public List<Film> listFilmByBioskop() {
        return listFilm;
    }

    public List<Tiket> listTiketByBioskop() {
        return listTiket;
    }
}
