package StreamingProg;

import java.util.List;

public class Movie extends Media {
    private int duration;

    public Movie(String username, List<String> mediaList, String title, String genre, int releaseYear, float rating, int duration) {
        super(username, mediaList, title, genre, releaseYear, rating);
        this.duration = duration;
    }

    public void playMovie() {

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
