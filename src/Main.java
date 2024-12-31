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
                    System.out.println("3. Tambah Wilayah");
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
                            // Tambah Wilayah
                            System.out.print("Masukkan Nama Wilayah: ");
                            String namaWilayah = scanner.nextLine();

                            boolean wilayahBerhasil = Wilayah.tambahWilayah(namaWilayah);

                            if (wilayahBerhasil) {
                                System.out.println("Wilayah berhasil ditambahkan!");
                            } else {
                                System.out.println("Gagal menambahkan wilayah. Silakan coba lagi.");
                            }
                        } else {
                            System.out.println("Akses ditolak. Anda tidak memiliki izin.");
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
