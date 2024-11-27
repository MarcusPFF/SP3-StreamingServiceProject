package StreamingProg;

import java.util.List;

public abstract class Media {
    protected String title;
    protected String genre;
    protected String releaseYear;
    protected String rating;

    public Media(String title, String releaseYear, String genre, String rating) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Getters and Setters
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // Overriding toString for better readability
    @Override
    public String toString() {
        return "Titel: " + title + ", Genre: " + genre + ", Udgivelse: " + releaseYear + ", Rating: " + rating;
    }

    // Abstract method to be implemented by subclasses
    public abstract void play();
}
