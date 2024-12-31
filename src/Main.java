import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        User loggedInUser = null;

        while (!exit) {
            if (loggedInUser == null) {
                // Menu sebelum login
                System.out.println("\nWelcome");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Pilih Opsi di Atas: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Register
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
                            System.out.println("Registration Gagal. Please try again.");
                        }
                        break;

                    case 2:
                        // Login
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
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Tidak ada Pilihan");
                        break;
                }
            } else {
                // Menu setelah login
                System.out.println("\nWelcome " + loggedInUser.getUsername());
                System.out.println("1. Logout");
                System.out.println("2. Lihat Semua Wilayah");

                if (loggedInUser.getRole() == 1) {
                    // Admin menu options
                    System.out.println("3. Setting Wilayah");
                    System.out.println("4. Tambah Bioskop");
                    System.out.println("5. Lihat Bioskop Berdasarkan Wilayah");
                } else {
                    // Regular user menu options
                    System.out.println("3. Lihat Bioskop Berdasarkan Wilayah");
                }

                System.out.print("Pilih Opsi di Atas: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Logout
                        System.out.println("Logout Berhasil!");
                        loggedInUser = null;
                        break;

                    case 2:
                        // Lihat Semua Wilayah
                        List<String> semuaWilayah = Wilayah.getSemuaNamaWilayah();
                        System.out.println("Daftar Wilayah:");
                        if (semuaWilayah.isEmpty()) {
                            System.out.println("Tidak ada wilayah yang terdaftar.");
                        } else {
                            for (String wilayah : semuaWilayah) {
                                System.out.println("- " + wilayah);
                            }
                        }
                        break;

                    case 3:
                        if (loggedInUser.getRole() == 1) {
                            // Admin: Setting Wilayah
                            System.out.println("\nSetting Wilayah");
                            System.out.println("1. Tambah Wilayah");
                            System.out.println("2. Update Wilayah");
                            System.out.println("3. Hapus Wilayah");
                            System.out.print("Pilih Opsi: ");
                            int settingChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (settingChoice) {
                                case 1:
                                    // Tambah Wilayah
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
                                    // Update Wilayah
                                    System.out.print("Masukkan Nama Wilayah yang ingin diupdate: ");
                                    String oldNamaWilayah = scanner.nextLine();
                                    System.out.print("Masukkan Nama Wilayah Baru: ");
                                    String newNamaWilayah = scanner.nextLine();

                                    boolean updateBerhasil = Wilayah.updateWilayah(oldNamaWilayah, newNamaWilayah);

                                    if (updateBerhasil) {
                                        System.out.println("Wilayah berhasil diupdate!");
                                    } else {
                                        System.out.println("Gagal mengupdate wilayah. Silakan coba lagi.");
                                    }
                                    break;

                                case 3:
                                    // Hapus Wilayah
                                    System.out.print("Masukkan Nama Wilayah yang ingin dihapus: ");
                                    String namaWilayahHapus = scanner.nextLine();

                                    boolean deleteBerhasil = Wilayah.deleteWilayah(namaWilayahHapus);

                                    if (deleteBerhasil) {
                                        System.out.println("Wilayah berhasil dihapus!");
                                    } else {
                                        System.out.println("Gagal menghapus wilayah. Pastikan wilayah ada dan coba lagi.");
                                    }
                                    break;

                                default:
                                    System.out.println("Pilihan tidak valid.");
                                    break;
                            }
                        }
                        break;

                    case 4:
                        if (loggedInUser.getRole() == 1) {
                            // Admin: Tambah Bioskop
                            System.out.print("Masukkan Nama Bioskop: ");
                            String namaBioskop = scanner.nextLine();
                            System.out.print("Masukkan Nama Wilayah: ");
                            String namaWilayah = scanner.nextLine();

                            boolean bioskopBerhasil = Bioskop.tambahBioskop(namaBioskop, namaWilayah);

                            if (bioskopBerhasil) {
                                System.out.println("Bioskop berhasil ditambahkan!");
                            } else {
                                System.out.println("Gagal menambahkan bioskop. Silakan coba lagi.");
                            }
                        } else {
                            System.out.println("Akses ditolak. Anda tidak memiliki izin.");
                        }
                        break;

                    case 5:
                        if (loggedInUser.getRole() == 1) {
                            // Admin: Lihat Bioskop Berdasarkan Wilayah
                            System.out.print("Masukkan Nama Wilayah: ");
                            String wilayahNama = scanner.nextLine();
                    
                            // Mendapatkan daftar bioskop berdasarkan nama wilayah
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
                        break;

                    default:
                        System.out.println("Tidak ada Pilihan");
                        break;
                }
            }
        }
    }
}
