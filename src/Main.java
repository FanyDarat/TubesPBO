import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        User loggedInUser = null;

        while (!exit) {
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
                        break;

                    case 2:
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
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            } else {
                System.out.println("\n===== Menu =====");
                System.out.println("1. Logout");
                System.out.println("2. Lihat Semua Wilayah");
                System.out.println("3. Lihat Bioskop Berdasarkan Wilayah");
                if (loggedInUser.getRole() == 1) {
                    System.out.println("4. Setting Wilayah");
                    System.out.println("5. Setting Bioskop");
                }

                System.out.print("Pilih Opsi di Atas: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Logout Berhasil!");
                        loggedInUser = null;
                        break;

                    case 2:
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
                        break;

                    case 4:
                        if (loggedInUser.getRole() == 1) {
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
                        break;

                    case 5:
                        if (loggedInUser.getRole() == 1) {
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

                                    boolean updateBioskop = Bioskop.updateBioskop(namaBioskopLama, namaBioskopBaru, wilayahUpdate);
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
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            }
        }
    }
}
