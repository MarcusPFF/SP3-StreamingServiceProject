package StreamingProg;

import java.util.List;

public abstract class Media extends Menu {
    protected String title;
    protected String genre;
    protected int releaseYear;
    protected float rating;

    public Media(String username, List<String> mediaList, String title, String genre, int releaseYear, float rating) {
        super(username, mediaList); //Her skal rettes, den finder ikke mediaList
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }
}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}