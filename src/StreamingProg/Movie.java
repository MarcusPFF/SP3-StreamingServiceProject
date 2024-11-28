package StreamingProg;

public class Movie extends Media {
    //Constructor
    public Movie(String title, String genre, String releaseYear, String rating) {
        super(title, genre, releaseYear, rating);
    }

    //This method plays and displays the current title
    @Override
    public void play() {
        System.out.println("Spiller film: " + getTitle());
    }
}
