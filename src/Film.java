import java.util.ArrayList;
import java.util.List;

public class Film {
    private int filmID;
    private String namaFilm;
    private String genre;
    private int durasi;
    private double price;

    public Film(int filmID, String namaFilm, String genre, int durasi, double price) {
        this.filmID = filmID;
        this.namaFilm = namaFilm;
        this.genre = genre;
        this.durasi = durasi;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getFilmDetails() {
        return "Film: " + namaFilm + ", Genre: " + genre + ", Durasi: " + durasi + " minutes, Price: $" + price;
    }

    public List<Film> listAllFilms() {
        // Placeholder for listing all films
        return new ArrayList<>();
    }
}
