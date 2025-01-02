public class Tiket extends DatabaseUtil {
    private int tiketID;
    private int userID;
    private int jadwalID;
    private String nomorKursi;
    private String orderID;
    private String namaFilm;
    private String jadwalTayang;

    public Tiket(int tiketID, int userID, int jadwalID, String nomorKursi, String orderID, String namaFilm, String jadwalTayang) {
        this.tiketID = tiketID;
        this.userID = userID;
        this.jadwalID = jadwalID;
        this.nomorKursi = nomorKursi;
        this.orderID = orderID;
        this.namaFilm = namaFilm;
        this.jadwalTayang = jadwalTayang;
    }

    public Tiket(int tiketID, int userID, int jadwalID, String nomorKursi) {
        this.tiketID = tiketID;
        this.userID = userID;
        this.jadwalID = jadwalID;
        this.nomorKursi = nomorKursi;
    }

    public String getJadwalTayang() {
        return jadwalTayang;
    }

    public String getNamaFilm() {
        return namaFilm;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getTiketDetails() {
        return tiketID;
    }

    public int getTiketID() {
        return tiketID;
    }

    public int getUserID() {
        return userID;
    }

    public int getJadwalID() {
        return jadwalID;
    }

    public String getNomorKursi() {
        return nomorKursi;
    }

    
}
