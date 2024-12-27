import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Jadwal {
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
    }

    public int getJadwal() {
        return jadwalID;
    }

    public List<Jadwal> filterByBioskop(int bioskopID) {
        // Placeholder for filtering schedules by bioskopID
        return new ArrayList<>();
    }

    public List<Jadwal> filterByFilm(int filmID) {
        // Placeholder for filtering schedules by filmID
        return new ArrayList<>();
    }

    public void updateJadwal(LocalDateTime waktuTayang, double harga) {
        this.waktuTayang = waktuTayang;
        this.harga = harga;
    }

    public List<Tiket> listTiket() {
        return listTiket;
    }
}
