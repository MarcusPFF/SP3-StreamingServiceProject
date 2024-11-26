package StreamingProg;

import java.util.ArrayList;

public class Movie extends Media {
    String filePathMovies = "data/entertainmentData/movies.txt";
    private FileIO io;
    private TextUI ui;

    public Movie(String title, String releaseYear, String genre, float rating) {
        super(title, releaseYear, genre, rating);
    }

    public void playMovie() {
        System.out.println(getTitle() + " afspilles nu...");
    }
}


