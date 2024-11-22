package StreamingProg;

import java.util.List;

public class Movie extends Media {
    private int duration;

    public Movie(String title, String genre, int releaseYear, float rating, int duration) {
        super(title, genre, releaseYear, rating);
        this.duration = duration;
    }

    public void playMovie(){
        System.out.println(getTitle() + " afspilles nu...");

    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}


