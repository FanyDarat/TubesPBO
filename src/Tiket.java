public class Tiket implements Pembayaran {
    private int tiketID;
    private int userID;
    private int jadwalID;
    private String nomorKursi;
    private int jumlah;
    private double totalHarga;

    public Tiket(int tiketID, int userID, int jadwalID, String nomorKursi, int jumlah, double totalHarga) {
        this.tiketID = tiketID;
        this.userID = userID;
        this.jadwalID = jadwalID;
        this.nomorKursi = nomorKursi;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
    }

    @Override
    public void prosesPembayaran(Film film) {
        this.totalHarga = jumlah * film.getPrice();
        System.out.println("Proses pembayaran untuk tiket dengan ID: " + tiketID + ". Total harga: $" + totalHarga);
    }

    public int getTiketDetails() {
        return tiketID;
    }

    public void updateTiket(String nomorKursi, int jumlah, double totalHarga) {
        this.nomorKursi = nomorKursi;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
    }
}
