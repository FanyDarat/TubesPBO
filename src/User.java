import java.util.List;

public class User {
        private int userID;
        private String nama;
        private String email;
        private String password;
        private String telepon;
        private List<Tiket> listTiket;

        public User(int userID, String nama, String email, String password, String telepon) {
            this.userID = userID;
            this.nama = nama;
            this.email = email;
            this.password = password;
            this.telepon = telepon;
        }

        public void register(String nama, String email, String password, String telepon) {
            this.nama = nama;
            this.email = email;
            this.password = password;
            this.telepon = telepon;
        }

        public void login(String email, String password) {
            if (this.email.equals(email) && this.password.equals(password)) {
                System.out.println("Login successful for user: " + nama);
            } else {
                System.out.println("Login failed. Please check your credentials.");
            }
        }

        public void updateProfile(String nama, String telepon) {
            this.nama = nama;
            this.telepon = telepon;
        }

        public void deleteAccount() {
            this.userID = 0;
            this.nama = null;
            this.email = null;
            this.password = null;
            this.telepon = null;
        }

        public List<Tiket> getTiketByUser() {
            return listTiket;
        }
    }
