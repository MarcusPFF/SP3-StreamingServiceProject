package StreamingProg;

public class Movie extends Media {
    public Movie(String title, String releaseYear, String genre, String rating) {
        super(title, releaseYear, genre, rating);
    }

    @Override
    public void play() {
        System.out.println("Spiller film: " + getTitle());
    }
}
