public class Wilayah {
    private int wilayahID;
    private String nama;

    public Wilayah(int wilayahID, String nama) {
        this.wilayahID = wilayahID;
        this.nama = nama;
    }

    public int getWilayah() {
        return wilayahID;
    }

    public void updateWilayah(String nama) {
        this.nama = nama;
    }
}
