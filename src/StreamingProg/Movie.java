package StreamingProg;

import java.util.List;

public class Movie extends Media {


    public Movie(String title, int releaseYear, String genre, float rating) {
        super(title, releaseYear, genre, rating);
    }

    public void playMovie() {
        System.out.println(getTitle() + " afspilles nu...");
    }
}


