public class Tiket extends DatabaseUtil{
    private int tiketID;
    private int userID;
    private int jadwalID;
    private String nomorKursi;

    public Tiket(int tiketID, int userID, int jadwalID, String nomorKursi) {
        this.tiketID = tiketID;
        this.userID = userID;
        this.jadwalID = jadwalID;
        this.nomorKursi = nomorKursi;
    }

    public int getTiketDetails() {
        return tiketID;
    }

    public void updateTiket(String nomorKursi) {
        this.nomorKursi = nomorKursi;
    }
    
}