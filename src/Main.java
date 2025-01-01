import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User loggedInUser = null;

    public static boolean isAdmin() {
        return loggedInUser.getRole() == 1;
    }
    
    public static void register() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Telepon: ");
        String telepon = scanner.nextLine();

        User newUser = new User(0, username, email, password, telepon, 0);
        boolean success = newUser.registerUser(username, email, password, telepon);

        if (success) {
            System.out.println("Registration Berhasil!");
        } else {
            System.out.println("Registration Gagal. Silakan coba lagi.");
        }
    }
    public static void login() {
        System.out.print("Enter Username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPassword = scanner.nextLine();

        loggedInUser = User.loginUser(loginUsername, loginPassword);

        if (loggedInUser != null) {
            System.out.println("Login Berhasil!");
            System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
        } else {
            System.out.println("Login Gagal. Username atau Password Salah.");
        }
    }
    public static void topUpSaldo() {

    }
    public static void beliTiket() {

    }
    public static void lihatAllWilayah() {
        List<String> semuaWilayah = Wilayah.getSemuaNamaWilayah();
        System.out.println("Daftar Wilayah:");
        if (semuaWilayah.isEmpty()) {
            System.out.println("Tidak ada wilayah yang terdaftar.");
        } else {
            for (String wilayah : semuaWilayah) {
                System.out.println("- " + wilayah);
            }
        }
    }
    public static void lihatBioskop() {
        System.out.print("Masukkan Nama Wilayah: ");
                    String wilayahNama = scanner.nextLine();
                    List<Bioskop> listBioskop = Bioskop.listBioskopByWilayah(wilayahNama);

                    System.out.println("Daftar Bioskop di Wilayah " + wilayahNama + ":");
                    if (listBioskop.isEmpty()) {
                        System.out.println("Tidak ada bioskop yang terdaftar di wilayah ini.");
                    } else {
                        for (Bioskop bioskop : listBioskop) {
                            System.out.println("- " + bioskop.getNama());
                        }
                    }
    }
    public static void lihatFilm() {
        System.out.print("Masukkan Nama Wilayah: ");
                    String wilayah = scanner.nextLine();
                    System.out.print("Masukkan Nama Bioskop: ");
                    String bioskopNama = scanner.nextLine();

                    // Fetch films based on bioskop and wilayah
                    List<Film> films = Film.getFilm(bioskopNama, wilayah);

                    System.out.println("Daftar Film di Bioskop " + bioskopNama + " di Wilayah " + wilayah + ":");
                    if (films.isEmpty()) {
                        System.out.println("Tidak ada film yang terdaftar.");
                    } else {
                        // Menampilkan daftar film yang ditemukan
                        for (Film film : films) {
                            System.out.println("Nama Film: " + film.getNamaFilm());
                            System.out.println("Genre: " + film.getGenre());
                            System.out.println("Durasi: " + film.getDurasi() + " menit");
                            System.out.println("Harga: Rp " + film.getHarga());
                            System.out.println();
                        }
                    }
    }
    public static void lihatJadwal(){
        
    }
    public static void lihatTiket(){
    }
    public static void settingWilayah() {
        if (isAdmin()) {
            System.out.println("\n===== Setting Wilayah =====");
            System.out.println("1. Tambah Wilayah");
            System.out.println("2. Update Wilayah");
            System.out.println("3. Hapus Wilayah");
            System.out.print("Pilih Opsi: ");
            int wilayahChoice = scanner.nextInt();
            scanner.nextLine();

            switch (wilayahChoice) {
                case 1:
                    System.out.print("Masukkan Nama Wilayah: ");
                    String namaWilayah = scanner.nextLine();

                    boolean wilayahBerhasil = Wilayah.tambahWilayah(namaWilayah);

                    if (wilayahBerhasil) {
                        System.out.println("Wilayah berhasil ditambahkan!");
                    } else {
                        System.out.println("Gagal menambahkan wilayah. Silakan coba lagi.");
                    }
                    break;

                case 2:
                    System.out.print("Masukkan Nama Wilayah yang ingin diupdate: ");
                    String oldNamaWilayah = scanner.nextLine();
                    System.out.print("Masukkan Nama Wilayah Baru: ");
                    String newNamaWilayah = scanner.nextLine();

                    boolean updateBerhasil = Wilayah.updateWilayah(oldNamaWilayah, newNamaWilayah);

                    if (updateBerhasil) {
                        System.out.println("Wilayah berhasil diupdate!");
                    } else {
                        System.out.println("Gagal mengupdate wilayah.");
                    }
                    break;

                case 3:
                    System.out.print("Masukkan Nama Wilayah yang ingin dihapus: ");
                    String namaWilayahHapus = scanner.nextLine();

                    boolean deleteBerhasil = Wilayah.deleteWilayah(namaWilayahHapus);

                    if (deleteBerhasil) {
                        System.out.println("Wilayah berhasil dihapus!");
                    } else {
                        System.out.println("Gagal menghapus wilayah.");
                    }
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

        }
    }
    public static void settingBioskop() {
        if (isAdmin()) {
            System.out.println("\n===== Setting Bioskop =====");
            System.out.println("1. Tambah Bioskop");
            System.out.println("2. Update Bioskop");
            System.out.println("3. Delete Bioskop");
            System.out.print("Pilih Opsi: ");
            int bioskopChoice = scanner.nextInt();
            scanner.nextLine();

            switch (bioskopChoice) {
                case 1:
                    System.out.print("Masukkan Nama Bioskop: ");
                    String namaBioskop = scanner.nextLine();
                    System.out.print("Masukkan Nama Wilayah: ");
                    String namaWilayah = scanner.nextLine();

                    boolean bioskopBerhasil = Bioskop.tambahBioskop(namaBioskop, namaWilayah);

                    if (bioskopBerhasil) {
                        System.out.println("Bioskop berhasil ditambahkan!");
                    } else {
                        System.out.println("Gagal menambahkan bioskop.");
                    }
                    break;

                case 2:
                    System.out.print("Masukkan Nama Wilayah: ");
                    String wilayahUpdate = scanner.nextLine();
                    System.out.print("Masukkan Nama Bioskop yang ingin diupdate: ");
                    String namaBioskopLama = scanner.nextLine();
                    System.out.print("Masukkan Nama Baru Bioskop: ");
                    String namaBioskopBaru = scanner.nextLine();

                    boolean updateBioskop = Bioskop.updateBioskop(namaBioskopLama, namaBioskopBaru,
                            wilayahUpdate);
                    if (updateBioskop) {
                        System.out.println("Bioskop berhasil diupdate!");
                    } else {
                        System.out.println("Gagal mengupdate bioskop.");
                    }
                    break;

                case 3:
                    System.out.print("Masukkan Nama Wilayah: ");
                    String wilayahDelete = scanner.nextLine();
                    System.out.print("Masukkan Nama Bioskop yang ingin dihapus: ");
                    String namaBioskopHapus = scanner.nextLine();

                    boolean deleteBioskop = Bioskop.deleteBioskop(namaBioskopHapus, wilayahDelete);
                    if (deleteBioskop) {
                        System.out.println("Bioskop berhasil dihapus!");
                    } else {
                        System.out.println("Gagal menghapus bioskop.");
                    }
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } else {
            System.out.println("Akses ditolak. Anda tidak memiliki izin.");
        }
    }
    public static void settingFilm() {
        if (isAdmin()) {
            System.out.println("\n===== Setting Film =====");
            System.out.println("1. Tambah Film");
            System.out.println("2. Update Film");
            System.out.println("3. Hapus Film");
            System.out.print("Pilih Opsi: ");
            int filmChoice = scanner.nextInt();
            scanner.nextLine();

            switch (filmChoice) {
                case 1:
                    System.out.print("Masukkan Nama Bioskop: ");
                    String namaBioskop = scanner.nextLine();
                    System.out.print("Masukkan Nama Wilayah: ");
                    String namaWilayah = scanner.nextLine();
                    System.out.print("Masukkan Nama Film: ");
                    String namaFilm = scanner.nextLine();
                    System.out.print("Masukkan Genre: ");
                    String genreFilm = scanner.nextLine();
                    System.out.print("Masukkan Durasi: ");
                    int durasiFilm = scanner.nextInt();
                    System.out.print("Masukkan Harga: ");
                    double hargaFilm = scanner.nextDouble();

                    // Memanggil method addFilm dengan memasukkan nama bioskop dan wilayah
                    boolean filmBerhasil = Film.addFilm(namaBioskop, namaWilayah, namaFilm, genreFilm,
                            durasiFilm, hargaFilm);

                    if (filmBerhasil) {
                        System.out.println("Film berhasil ditambahkan!");
                    } else {
                        System.out.println("Gagal menambahkan film.");
                    }
                    break;

                case 2:
                    System.out.print("Masukkan Nama Film yang ingin diupdate: ");
                    String namaFilmLama = scanner.nextLine();
                    System.out.print("Masukkan Nama Bioskop: ");
                    String namaBioskopLama = scanner.nextLine();
                    System.out.print("Masukkan Nama Wilayah: ");
                    String namaWilayahLama = scanner.nextLine();
                    System.out.print("Masukkan Nama Film Baru: ");
                    String namaFilmBaru = scanner.nextLine();
                    System.out.print("Masukkan Genre Film Baru: ");
                    String genreFilmBaru = scanner.nextLine();
                    System.out.print("Masukkan Durasi Film Baru: ");
                    int durasiFilmBaru = scanner.nextInt();
                    System.out.print("Masukkan Harga Film Baru: ");
                    double hargaFilmBaru = scanner.nextDouble();

                    boolean updateFilm = Film.updateFilm(namaFilmLama, namaWilayahLama, namaBioskopLama,
                            namaFilmBaru, genreFilmBaru, durasiFilmBaru, hargaFilmBaru);
                    if (updateFilm) {
                        System.out.println("Film berhasil diupdate!");
                    } else {
                        System.out.println("Gagal mengupdate film.");
                    }
                    break;

                case 3:
                    System.out.print("Masukkan Nama Film yang ingin dihapus: ");
                    String namaFilmDelete = scanner.nextLine();
                    String namaBioskopDelete = scanner.nextLine();
                    String namaWilayahDelete = scanner.nextLine();

                    boolean deleteFilm = Film.deleteFilm(namaFilmDelete, namaBioskopDelete,
                            namaWilayahDelete);
                    if (deleteFilm) {
                        System.out.println("Film berhasil dihapus!");
                    } else {
                        System.out.println("Gagal menghapus film.");
                    }
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } else {
            System.out.println("Akses ditolak. Anda tidak memiliki izin.");
        }
    }
    public static void settingJadwal() {

    }
    public static void printMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Logout");
        System.out.println("2. Top Up Saldo");
        System.out.println("3. Beli Tiket");
        System.out.println("4. Lihat Semua Wilayah");
        System.out.println("5. Lihat Bioskop Berdasarkan Wilayah");
        System.out.println("6. Lihat Film Berdasarkan Bioskop dan Wilayah");
        

        if (isAdmin()) {
            System.out.println("90. Setting Wilayah");
            System.out.println("91. Setting Bioskop");
            System.out.println("92. Setting Film");
            System.out.println("93. Setting Jadwal");
        }
    }

    public static void start() {

        if (loggedInUser == null) {
            System.out.println("\n===== Welcome =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Pilih Opsi di Atas: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } else {
            printMenu();

            System.out.print("Pilih Opsi di Atas: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Logout Berhasil!");
                    loggedInUser = null;
                    break;
                case 2:
                    topUpSaldo();
                    break;
                case 3:
                    beliTiket();
                    break;
                case 4:
                    lihatAllWilayah();
                    break;
                case 5:
                    lihatBioskop();
                    break;
                case 6:
                    lihatFilm();
                    break;
                case 7:
                    lihatJadwal();
                    break;
                case 8:
                    lihatTiket();
                    break;
                case 90:
                    settingWilayah();
                    break;
                case 91:
                    settingBioskop();
                    break;
                case 92:
                    settingFilm();
                    break;
                case 93:
                    settingJadwal();
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
        start();
    }

    public static void main(String[] args) {
        start();
        scanner.close();
    }
}
