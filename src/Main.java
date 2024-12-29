import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
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

                    User newUser = new User(0, username, email, password, telepon);
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

                    User loggedInUser = User.loginUser(loginUsername, loginPassword);

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
        }
    }
}
